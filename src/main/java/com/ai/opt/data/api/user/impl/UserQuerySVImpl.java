package com.ai.opt.data.api.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.data.api.user.interfaces.IUserQuerySV;
import com.ai.opt.data.api.user.param.UserQueryRequest;
import com.ai.opt.data.api.user.param.UserQueryResponse;
import com.ai.opt.data.constants.AccountConstants.ResultCode;
import com.ai.opt.data.dao.mapper.bo.UcMembers;
import com.ai.opt.data.service.busi.interfaces.IUserBusiSV;
import com.ai.opt.data.service.busi.interfaces.IVoValidateSV;
import com.ai.opt.sdk.util.BeanUtils;
import com.alibaba.dubbo.config.annotation.Service;

@Service
@Component
public class UserQuerySVImpl implements IUserQuerySV {

	@Autowired
	IUserBusiSV iUserBusiSV;
	@Autowired
	IVoValidateSV iVoValidateSV;

	@Override
	public UserQueryResponse queryBaseInfo(UserQueryRequest accountQueryRequest) throws BusinessException,SystemException {
		// 入参检查
		iVoValidateSV.validateQueryAccountBaseInfo(accountQueryRequest);
		// 查询数据
		String userId = accountQueryRequest.getUserId();
		UcMembers ucMembers = iUserBusiSV.queryByUserId(Integer.valueOf(userId));
		// 整理返回对象
		UserQueryResponse response = new UserQueryResponse();
		if (ucMembers != null) {
			BeanUtils.copyProperties(response, ucMembers);
			response.setUserId(String.valueOf(ucMembers.getUid()));
			response.setLoginName(ucMembers.getUsername());
			response.setEmail(ucMembers.getEmail());
			response.setMobile(ucMembers.getMobilephone());
			response.setDomainname(ucMembers.getDomainName());
		}
		ResponseHeader responseHeader = new ResponseHeader(true, ResultCode.SUCCESS_CODE, "数据查询成功");
		response.setResponseHeader(responseHeader);
		return response;
	}

	
}
