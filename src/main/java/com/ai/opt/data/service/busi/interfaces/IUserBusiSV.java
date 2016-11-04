package com.ai.opt.data.service.busi.interfaces;

import com.ai.opt.base.exception.SystemException;
import com.ai.opt.data.dao.mapper.bo.UcMembers;


public interface IUserBusiSV {
	
	UcMembers queryByUserId(Integer userId) throws SystemException;
	
}
