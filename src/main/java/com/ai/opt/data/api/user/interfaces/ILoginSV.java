package com.ai.opt.data.api.user.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.data.api.user.param.ThirdUserQueryRequest;
import com.ai.opt.data.api.user.param.UserLoginResponse;
import com.ai.opt.data.dao.mapper.bo.UcMembers;

/**
 * 登录服务<br>
 * Date: 2016年3月16日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author gucl
 */
@Path("/userlogin")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface ILoginSV {
    /**
     * 根据用户名查询
     * 
     * @param username
     * @return
     * @throws BusinessException,SystemException
     * @author gucl
     * @ApiCode SSO_0001
     * @RestRelativeURL userlogin/queryUserByUserName
     */
	@POST
	@Path("/queryUserByUserName")
    UserLoginResponse queryUserByUserName(String username) throws BusinessException,SystemException;


	/**
	 * 插入第三方登录的用户信息
	 * @param ucMembers
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gucl
	 */
	@POST
	@Path("/bindThirdUser")
	String bindThirdUser(UcMembers ucMembers) throws BusinessException,SystemException;
	/**
	 * 查询第三方登录的用户信息（依据usersource和thirduid查询）
	 *  
	 * @param thirdUser  第三方系统用户的usersource和thirduid
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gucl
	 */
	@POST
	@Path("/queryThirdUser")
	UcMembers queryThirdUser(ThirdUserQueryRequest thirdUser) throws BusinessException,SystemException;
	
}
