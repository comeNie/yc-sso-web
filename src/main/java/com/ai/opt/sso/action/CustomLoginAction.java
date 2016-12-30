/*
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.ai.opt.sso.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.jasig.cas.CentralAuthenticationService;
import org.jasig.cas.Message;
import org.jasig.cas.authentication.AuthenticationException;
import org.jasig.cas.authentication.Credential;
import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.principal.Service;
import org.jasig.cas.ticket.TicketCreationException;
import org.jasig.cas.ticket.TicketException;
import org.jasig.cas.ticket.TicketGrantingTicket;
import org.jasig.cas.ticket.registry.TicketRegistry;
import org.jasig.cas.web.bind.CredentialsBinder;
import org.jasig.cas.web.support.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.util.StringUtils;
import org.springframework.web.util.CookieGenerator;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import com.ai.opt.sdk.components.ccs.CCSClientFactory;
import com.ai.opt.sdk.components.mcs.MCSClientFactory;
import com.ai.opt.sso.handler.CustomLoginFlowUrlHandler;
import com.ai.opt.sso.principal.BssCredentials;
import com.ai.opt.sso.util.RequestHelper;
import com.ai.paas.ipaas.ccs.constants.ConfigException;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;

/**
 * Action to authenticate credential and retrieve a TicketGrantingTicket for
 * those credential. If there is a request for renew, then it also generates
 * the Service Ticket required.
 *
 * @author Scott Battaglia
 * @since 3.0.4
 */
public class CustomLoginAction  {

    /** Authentication success result. */
    public static final String SUCCESS = "success";
    
    private String code = "captchaCode";

    /** Authentication succeeded with warnings from authn subsystem that should be displayed to user. */
    public static final String SUCCESS_WITH_WARNINGS = "successWithWarnings";

    /** Authentication success with "warn" enabled. */
    public static final String WARN = "warn";

 
    
    /** Authentication failure result. */
    public static final String AUTHENTICATION_FAILURE = "authenticationFailure";

    /** Error result. */
    public static final String ERROR = "error";

    /** Flag indicating whether message context contains warning messages. */  
    private boolean hasWarningMessages;  
    /**
     * Binder that allows additional binding of form object beyond Spring
     * defaults.
     */
    private CredentialsBinder credentialsBinder;

    /** Core we delegate to for handling all ticket related tasks. */
    @NotNull
    private CentralAuthenticationService centralAuthenticationService;

    /** Ticket registry used to retrieve tickets by ID. */
    @NotNull
    private TicketRegistry ticketRegistry;

    @NotNull
    private CookieGenerator warnCookieGenerator;

    private static ICacheClient jedis = MCSClientFactory.getCacheClient("com.ai.opt.uac.cache.logincount.cache");

    /** Logger instance. **/
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public final void doBind(final RequestContext context, final Credential credential) throws Exception {
        final HttpServletRequest request = WebUtils.getHttpServletRequest(context);
      //获取请求IP  
        String requestIp = RequestHelper.getRemoteHost(request);  
        request.getSession().removeAttribute("errorMsg");  
		
		jedis.set(CustomLoginFlowUrlHandler.CAS_REDIS_PREFIX+credential.getId(), requestIp);  

        if (this.credentialsBinder != null && this.credentialsBinder.supports(credential.getClass())) {
            this.credentialsBinder.bind(request, credential);
        }
    }

    public final Event submit(final RequestContext context, final Credential credential,
                              final MessageContext messageContext) throws Exception {
    	 //Validate code  
        if (credential instanceof BssCredentials) {  
            // 这个类也是我们自己搞的，里面能取到验证码  
        	BssCredentials rmupc = (BssCredentials) credential;  
            // 从session中取出生成验证码的时候就保存在session中的验证码  
            String sessionCode = (String) WebUtils.getHttpServletRequest(context).getSession().getAttribute(code);  
            String errorNumView = (String) WebUtils.getHttpServletRequest(context).getParameter("errorNum");
            String errorNumCSS = (String) WebUtils.getHttpServletRequest(context).getParameter("errorNumCCS");
            
            String errorNumTimeOutCCS = (String) WebUtils.getHttpServletRequest(context).getParameter("errorNumTimeOutCCS");
            rmupc.setErrorNum(errorNumView);
            rmupc.setErrorNumCCS(errorNumCSS);
            rmupc.setErrorNumTimeOutCCS(errorNumTimeOutCCS);
        	String errorNumConfig="";
    		try {
    			errorNumConfig = CCSClientFactory.getDefaultConfigClient().get("/errorNum");
    		} catch (ConfigException e) {
    			logger.error("从配置中心获取登录失败次数失败");
    		}
    		Integer errorNumber = null;
    		if(StringUtils.hasText(errorNumConfig)){
    			errorNumber = Integer.valueOf(errorNumConfig);
    		}	
            Integer errorNum = getErrorNum(credential);  
            //判断验证码错误次数 大于等于5才需要验证  
            if(errorNum>=errorNumber){  
                 // 如果验证码为null  
                if (rmupc.getCaptchaCode() == null) {  
                    // 写入日志  
                    logger.error( "验证码为空" );  
                    // 错误信息，会在配置文件（messages_zh_CN.properties）里面先定义好  
                    final String code = "required.captcha" ;  
                    // 发送错误信息到前台  
                    messageContext.addMessage( new MessageBuilder().error().code(code).arg("" ).defaultText(code).build());  
                    return newEvent(ERROR);  
                }  
/*                // 如果验证码不正确  
                if (!rmupc.getCaptchaCode().toUpperCase().equals(sessionCode.toUpperCase())) {  
                    logger.warn( "验证码检验有误" );  
                    final String code = "error.authentication.code.bad" ;  
                    messageContext.addMessage( new MessageBuilder().error().code(code).arg("" ).defaultText(code).build());  
                    return newEvent(ERROR);  
                }  */
            }        
        }  	
        // Validate login ticket
        final String authoritativeLoginTicket = WebUtils.getLoginTicketFromFlowScope(context);
        final String providedLoginTicket = WebUtils.getLoginTicketFromRequest(context);
        if (!authoritativeLoginTicket.equals(providedLoginTicket)) {
            logger.warn("Invalid login ticket {}", providedLoginTicket);
            messageContext.addMessage(new MessageBuilder().error().code("error.invalid.loginticket").build());
            return newEvent(ERROR);
        }

        final String ticketGrantingTicketId = WebUtils.getTicketGrantingTicketId(context);
        final Service service = WebUtils.getService(context);
        if (StringUtils.hasText(context.getRequestParameters().get("renew")) && ticketGrantingTicketId != null
                && service != null) {

            try {
                final String serviceTicketId = this.centralAuthenticationService.grantServiceTicket(
                        ticketGrantingTicketId, service, credential);
                WebUtils.putServiceTicketInRequestScope(context, serviceTicketId);
                putWarnCookieIfRequestParameterPresent(context);
                return newEvent(WARN);
            } catch (final AuthenticationException e) {
                return newEvent(AUTHENTICATION_FAILURE, e);
            } catch (final TicketCreationException e) {
                logger.warn(
                        "Invalid attempt to access service using renew=true with different credential. "
                                + "Ending SSO session.");
                this.centralAuthenticationService.destroyTicketGrantingTicket(ticketGrantingTicketId);
            } catch (final TicketException e) {
                return newEvent(ERROR, e);
            }
        }
    
        try {
            final String tgtId = this.centralAuthenticationService.createTicketGrantingTicket(credential);
            WebUtils.putTicketGrantingTicketInFlowScope(context, tgtId);
            putWarnCookieIfRequestParameterPresent(context);
            final TicketGrantingTicket tgt = (TicketGrantingTicket) this.ticketRegistry.getTicket(tgtId);
            for (final Map.Entry<String, HandlerResult> entry : tgt.getAuthentication().getSuccesses().entrySet()) {  
                for (final Message message : entry.getValue().getWarnings()) {  
                    addWarningToContext(messageContext, message);  
                }  
            } 
            if (addWarningMessagesToMessageContextIfNeeded(tgt, messageContext)) {
                return newEvent(SUCCESS_WITH_WARNINGS);
            }
            return newEvent(SUCCESS);
        } catch (final AuthenticationException e) {
            return newEvent(AUTHENTICATION_FAILURE, e);
        } catch (final Exception e) {
            return newEvent(ERROR, e);
        }
    }

    public Integer getErrorNum(Credential credential){  
        String requestIp = jedis.get(CustomLoginFlowUrlHandler.CAS_REDIS_PREFIX+credential.getId());  
        String errorNumStr = jedis.get(CustomLoginFlowUrlHandler.CAS_REDIS_PREFIX+requestIp);  
        return Integer.parseInt(errorNumStr==null?"0":errorNumStr);  
   }  

	/**
     * Add warning messages to message context if needed.
     *
     * @param tgtId          the tgt id
     * @param messageContext the message context
     * @return true if warnings were found and added, false otherwise.
     * @since 4.0.3
     */
    private boolean addWarningMessagesToMessageContextIfNeeded(final TicketGrantingTicket tgtId,
                                                               final MessageContext messageContext) {
        boolean foundAndAddedWarnings = false;
        for (final Map.Entry<String, HandlerResult> entry : tgtId.getAuthentication().getSuccesses().entrySet()) {
            for (final Message message : entry.getValue().getWarnings()) {
                addWarningToContext(messageContext, message);
                foundAndAddedWarnings = true;
            }
        }
        return foundAndAddedWarnings;

    }

    private void putWarnCookieIfRequestParameterPresent(final RequestContext context) {
        final HttpServletResponse response = WebUtils.getHttpServletResponse(context);

        if (StringUtils.hasText(context.getExternalContext().getRequestParameterMap().get("warn"))) {
            this.warnCookieGenerator.addCookie(response, "true");
        } else {
            this.warnCookieGenerator.removeCookie(response);
        }
    }

    private AuthenticationException getAuthenticationExceptionAsCause(final TicketException e) {
        return (AuthenticationException) e.getCause();
    }

    private Event newEvent(final String id) {
        return new Event(this, id);
    }

    private Event newEvent(final String id, final Exception error) {
        return new Event(this, id, new LocalAttributeMap("error", error));
    }

    public final void setCentralAuthenticationService(final CentralAuthenticationService centralAuthenticationService) {
        this.centralAuthenticationService = centralAuthenticationService;
    }

    public void setTicketRegistry(final TicketRegistry ticketRegistry) {
        this.ticketRegistry = ticketRegistry;
    }

    /**
     * Set a CredentialsBinder for additional binding of the HttpServletRequest
     * to the Credential instance, beyond our default binding of the
     * Credential as a Form Object in Spring WebMVC parlance. By the time we
     * invoke this CredentialsBinder, we have already engaged in default binding
     * such that for each HttpServletRequest parameter, if there was a JavaBean
     * property of the Credential implementation of the same name, we have set
     * that property to be the value of the corresponding request parameter.
     * This CredentialsBinder plugin point exists to allow consideration of
     * things other than HttpServletRequest parameters in populating the
     * Credential (or more sophisticated consideration of the
     * HttpServletRequest parameters).
     *
     * @param credentialsBinder the credential binder to set.
     */
    public final void setCredentialsBinder(final CredentialsBinder credentialsBinder) {
        this.credentialsBinder = credentialsBinder;
    }

    public final void setWarnCookieGenerator(final CookieGenerator warnCookieGenerator) {
        this.warnCookieGenerator = warnCookieGenerator;
    }

    /**
     * Adds a warning message to the message context.
     *
     * @param context Message context.
     * @param warning Warning message.
     */
    private void addWarningToContext(final MessageContext context, final Message warning) {
        final MessageBuilder builder = new MessageBuilder()
                .warning()
                .code(warning.getCode())
                .defaultText(warning.getDefaultMessage())
                .args(warning.getParams());
        context.addMessage(builder.build());
        this.hasWarningMessages = true;  
    }

}