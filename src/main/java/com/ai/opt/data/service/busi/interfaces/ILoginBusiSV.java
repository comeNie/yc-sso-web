package com.ai.opt.data.service.busi.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.data.dao.mapper.bo.UcMembers;

public interface ILoginBusiSV {
	UcMembers queryByUserName(UcMembers user) throws BusinessException;
	UcMembers queryByUserNamePhoneEmail(String loginname) throws BusinessException;
    
}
