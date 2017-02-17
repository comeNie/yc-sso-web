package com.ai.opt.data.service.busi.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.data.dao.mapper.bo.UcMembers;
import com.ai.opt.data.service.atom.interfaces.ILoginAtomSV;
import com.ai.opt.data.service.busi.interfaces.ILoginBusiSV;

@Service
@Transactional
public class LoginBusiSVImpl implements ILoginBusiSV {
    @Autowired
    ILoginAtomSV iLoginAtomSV;

    @Override
    public UcMembers queryByUserName(UcMembers user) throws BusinessException {

        return iLoginAtomSV.queryByUserName(user);

    }

	@Override
	public UcMembers queryByUserNamePhoneEmail(String loginname) throws BusinessException {
		return iLoginAtomSV.queryByUserNamePhoneEmail(loginname);
	}

	@Override
	public String saveThirdUser(UcMembers ucMembers) throws BusinessException, SystemException {
		UcMembers ucDb=iLoginAtomSV.queryThirdUser(ucMembers.getUsersource(), ucMembers.getThirduid());
		if(ucDb!=null){
			return ucDb.getUid().toString();
		}
		return iLoginAtomSV.insertThirdUser(ucMembers);
	}

	@Override
	public UcMembers queryThirdUser(String usersource, String thirduid) throws BusinessException, SystemException {
		return iLoginAtomSV.queryThirdUser(usersource, thirduid);
	}

}
