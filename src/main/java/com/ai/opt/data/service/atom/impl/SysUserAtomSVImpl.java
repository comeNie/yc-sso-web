package com.ai.opt.data.service.atom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.SystemException;
import com.ai.opt.data.dao.mapper.bo.UcMembers;

import com.ai.opt.data.dao.mapper.interfaces.UcMembersMapper;
import com.ai.opt.data.service.atom.interfaces.ISysUserAtomSV;
@Component
public class SysUserAtomSVImpl implements ISysUserAtomSV {
	@Autowired
	private UcMembersMapper ucMembersMapper;
	@Override
	public UcMembers queryByUserId(Integer userId) throws SystemException {

		return ucMembersMapper.selectByPrimaryKey(userId);
	}

}
