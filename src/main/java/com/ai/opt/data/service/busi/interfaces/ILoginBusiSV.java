package com.ai.opt.data.service.busi.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.data.dao.mapper.bo.UcMembers;

public interface ILoginBusiSV {
	UcMembers queryByUserName(UcMembers user) throws BusinessException;
	UcMembers queryByUserNamePhoneEmail(String loginname) throws BusinessException;
	
	/**
	 * 插入第三方登录的用户信息
	 * @param ucMembers
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gucl
	 */
	String saveThirdUser(UcMembers ucMembers) throws BusinessException,SystemException;
	/**
	 * 查询第三方登录的用户信息（依据usersource和thirduid查询）
	 * @param usersource 用户来源.<br>
	 *  0	内部用户（默认）<br>
	 *	1	金山账号<br>
	 *	2	百度账号<br>
	 *	3	qq账号<br>
	 *	4	预留<br>
	 *	5	预留<br>
	 *	6	新浪账号<br>
	 *	7	微信账号<br>

	 *  
	 * @param thirduid  第三方系统用户ID
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gucl
	 */
	UcMembers queryThirdUser(String usersource,String thirduid) throws BusinessException,SystemException;
	
    
}
