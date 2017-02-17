package com.ai.opt.data.service.atom.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.data.dao.mapper.bo.UcMembers;
import com.ai.opt.data.dao.mapper.bo.UcMembersCriteria;
import com.ai.opt.data.dao.mapper.bo.UcMembersCriteria.Criteria;
import com.ai.opt.data.dao.mapper.factory.MapperFactory;
import com.ai.opt.data.dao.mapper.interfaces.UcMembersMapper;
import com.ai.opt.data.service.atom.interfaces.ILoginAtomSV;
import com.ai.opt.data.util.UCDateUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;

@Component
public class LoginAtomSVImpl implements ILoginAtomSV {


    @Override
    public UcMembers queryByUserName(UcMembers ucMembers) {

    	UcMembersCriteria conditon = new UcMembersCriteria();
    	UcMembersCriteria.Criteria criteria = conditon.or();
        if (!StringUtil.isBlank(ucMembers.getMobilephone())) {
            criteria.andMobilephoneEqualTo(ucMembers.getMobilephone());
            criteria.andEnablestatusEqualTo("1");
        }
        if (!StringUtil.isBlank(ucMembers.getEmail())) {
            criteria.andEmailEqualTo(ucMembers.getEmail());
            criteria.andEnablestatusEqualTo("1");
        }
        if (!StringUtil.isBlank(ucMembers.getUsername())) {
            criteria.andUsernameEqualTo(ucMembers.getUsername());
            criteria.andEnablestatusEqualTo("1");
        }
//        // 登录标记为1
////        criteria.andLoginFlagEqualTo("1");
//        // 删除标记为0
//        
//        criteria.andDelFlagEqualTo("0");

        UcMembersMapper ucMembersMapper = MapperFactory.getUcMembersMapper();
        List<UcMembers> list = ucMembersMapper.selectByExample(conditon);
        if (!CollectionUtil.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

	@Override
	public UcMembers queryByUserNamePhoneEmail(String loginname) throws SystemException {
		UcMembersCriteria example = new UcMembersCriteria();
        if (!StringUtil.isBlank(loginname)) {
        	Criteria orUsername = example.or();
			orUsername.andUsernameEqualTo(loginname);
			orUsername.andEnablestatusEqualTo("1");
			Criteria orMobile = example.or();
			orMobile.andMobilephoneEqualTo(loginname);
			orMobile.andEnablestatusEqualTo("1");
			Criteria orEmail = example.or();
			orEmail.andEmailEqualTo(loginname);
			orEmail.andEnablestatusEqualTo("1");
        }

        UcMembersMapper ucMembersMapper = MapperFactory.getUcMembersMapper();
        List<UcMembers> list = ucMembersMapper.selectByExample(example);
        if (!CollectionUtil.isEmpty(list)) {
            return list.get(0);
        }
        return null;
	}

	@Override
	public String insertThirdUser(UcMembers ucMembers) throws BusinessException, SystemException {
		
		//必填
		ucMembers.setPassword("");
		if(StringUtil.isBlank(ucMembers.getEmail())){
			ucMembers.setEmail(""); 
		}
		ucMembers.setEmailcheck(0); 
		ucMembers.setMyid("");
		ucMembers.setMyidkey("");
		ucMembers.setRegip("0");
		Integer regdate =(int)UCDateUtils.getSystime() ;
		ucMembers.setRegdate(regdate);
		ucMembers.setLastloginip("0");
		ucMembers.setLastlogintime(regdate);
		ucMembers.setSalt("");
		ucMembers.setSecques("");
		if(StringUtil.isBlank(ucMembers.getMobilephone())){
			ucMembers.setMobilephone("");
		}
		ucMembers.setSystemsource("0");
		//ucMembers.setThirduid("");
		//ucMembers.setUsersource("");
		ucMembers.setLoginmode("0");
		ucMembers.setLoginway("0");
		//设置为已激活
		ucMembers.setEnablestatus("1");
		ucMembers.setCreatetime(regdate+"");
		if(StringUtil.isBlank(ucMembers.getDomainName())){
			ucMembers.setDomainName("CN");
		}
		ucMembers.setModifydate(0);
		ucMembers.setLogincount(0);
		ucMembers.setLoginsystem("0");
		
		
		int insertCount = MapperFactory.getUcMembersMapper().insert(ucMembers);
		if(insertCount>0){
			
			Integer newId = queryUidofThirdUser(ucMembers.getUsersource(),ucMembers.getThirduid());
		
			return String.valueOf(newId);
		}else{
			return null;
		}
	}

	@Override
	public UcMembers queryThirdUser(String usersource, String thirduid) throws BusinessException, SystemException {
		UcMembers result=null;
		UcMembersCriteria example=new UcMembersCriteria();
		Criteria c=example.or();
		c.andUsersourceEqualTo(usersource).andThirduidEqualTo(thirduid);
		List<UcMembers> list=MapperFactory.getUcMembersMapper().selectByExample(example);
		if(!CollectionUtil.isEmpty(list)){
			result=list.get(0);
		}
		return result;
	}
	private Integer queryUidofThirdUser(String usersource, String thirduid) throws BusinessException, SystemException {
		Integer result=null;
		UcMembersCriteria example=new UcMembersCriteria();
		Criteria c=example.or();
		c.andUsersourceEqualTo(usersource).andThirduidEqualTo(thirduid);
		List<UcMembers> list=MapperFactory.getUcMembersMapper().selectByExample(example);
		if(!CollectionUtil.isEmpty(list)){
			result=list.get(0).getUid();
		}
		return result;
	}


}
