package com.ai.opt.sso.handler;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.webflow.context.servlet.DefaultFlowUrlHandler;
import org.springframework.webflow.core.collection.AttributeMap;

import com.ai.opt.sdk.components.mcs.MCSClientFactory;
import com.ai.opt.sso.util.RequestHelper;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;

public class CustomLoginFlowUrlHandler extends DefaultFlowUrlHandler {  
   
       
    private static final Logger logger = LoggerFactory.getLogger(CustomLoginFlowUrlHandler.class);  

       
    /** 缓存key前缀 */ 
    public static final String CAS_REDIS_PREFIX = "cas_redis:";   
    public static final String DEFAULT_FLOW_EXECUTION_KEY_PARAMETER = "execution";  
    private String flowExecutionKeyParameter = "execution";  
   
    public void setFlowExecutionKeyParameter(String parameterName) {  
        this.flowExecutionKeyParameter = parameterName;  
    }  
   
    public String getFlowExecutionKey(HttpServletRequest request) {  
        System.out.println(request.getQueryString());  
        return request.getParameter(this.flowExecutionKeyParameter);  
    }  
   
    public int getErrorNum(HttpServletRequest request){  
        if(logger.isDebugEnabled()){  
            logger.debug("CAS: login flow to get errorNum from redis-------------------start");  
        }  
        String requestIp = RequestHelper.getRemoteHost(request);  
		ICacheClient jedis = MCSClientFactory.getCacheClient("com.ai.opt.uac.cache.logincount.cache");
	    
        String errorNumStr = jedis.get(CAS_REDIS_PREFIX+requestIp);  
        if(logger.isDebugEnabled()){  
            logger.debug("CAS: login flow to get errorNum from redis-------------------end");  
        }  
        return  Integer.parseInt(errorNumStr==null?"0":errorNumStr);  
    }  
       
    /**  
     * execution的值和session属性对象的字段conversationIdSequence（int类型）相关，如果conversationIdSequence值为1，  
     * 那execution的值即为e2--（conversationIdSequence自增1）。生成Execution的同时，正常情况session内容也相应变化，webflowConversationContainer属性中新增了id为2的conversation，session写入到redis中。  
        但是，按照tomcat-redis-session-manager默认的session策略，webflowConversationContainer属性，key没有变，value地址没有变，认为session没有更新，新的session内容没有写入到redis中。  
        导致下次请求，取到的webflowConversationContainer属性，没有id为2的conversation，于是cas server端认定execution 为无效。导致刷新页面，而非正常的流程：表单验证。  
     * Description   
     * @param flowId  
     * @param flowExecutionKey  
     * @param request  
     * @return   
     * @see org.springframework.webflow.context.servlet.DefaultFlowUrlHandler#createFlowExecutionUrl(java.lang.String, java.lang.String, javax.servlet.http.HttpServletRequest)  
     */ 
    public String createFlowExecutionUrl(String flowId,  
            String flowExecutionKey, HttpServletRequest request) {  
        logger.debug("set errorNum to session");  
        //设置session更新属性  给出系统当前时间   保证 execution 值更新    
        request.getSession().setAttribute("session_flag", System.currentTimeMillis());  
        request.getSession().setAttribute("errorNum", getErrorNum(request));  
        StringBuffer builder = new StringBuffer();  
        builder.append(request.getRequestURI());  
        builder.append("?");  
        Map<String, String> flowParams = new LinkedHashMap<String, String>((Map)request.getParameterMap());  
        flowParams.put(this.flowExecutionKeyParameter, flowExecutionKey);  
        appendQueryParameters(builder, flowParams, getEncodingScheme(request));  
        return builder.toString();  
    }  
   
    public String createFlowDefinitionUrl(String flowId, AttributeMap input,  
            HttpServletRequest request) {  
        return new StringBuilder()  
                .append(request.getRequestURI())  
                .append(request.getQueryString() != null ? new StringBuilder()  
                        .append("?").append(request.getQueryString())  
                        .toString() : "").toString();  
    }  
   
 
   
}