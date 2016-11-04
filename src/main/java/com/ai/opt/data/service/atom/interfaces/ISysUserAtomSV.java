package com.ai.opt.data.service.atom.interfaces;

import com.ai.opt.base.exception.SystemException;
import com.ai.opt.data.dao.mapper.bo.UcMembers;

public interface ISysUserAtomSV {
	
	UcMembers queryByUserId(Integer userId) throws SystemException;
	
}
