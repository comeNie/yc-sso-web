package com.ai.opt.data.service.atom.interfaces;

import com.ai.opt.base.exception.SystemException;
import com.ai.opt.data.dao.mapper.bo.UcMembers;

public interface ILoginAtomSV {
	UcMembers queryByUserName(UcMembers ucMembers) throws SystemException;
	UcMembers queryByUserNamePhoneEmail(String loginname) throws SystemException;
}
