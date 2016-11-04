package com.ai.opt.data.service.busi.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.opt.base.exception.SystemException;
import com.ai.opt.data.dao.mapper.bo.UcMembers;
import com.ai.opt.data.service.atom.interfaces.ISysUserAtomSV;
import com.ai.opt.data.service.busi.interfaces.IUserBusiSV;

@Service
@Transactional
public class UserBusiSVImpl implements IUserBusiSV {

    @Autowired
    ISysUserAtomSV iSysUserAtomSV;

    @Override
    public UcMembers queryByUserId(Integer userId) throws SystemException {
        return iSysUserAtomSV.queryByUserId(userId);
    }

    

}
