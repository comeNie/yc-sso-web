package com.ai.opt.uac.web.test.user;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.opt.base.exception.RPCSystemException;
import com.ai.opt.data.api.user.interfaces.ILoginSV;
import com.ai.opt.data.api.user.param.ThirdUserQueryRequest;
import com.ai.opt.data.api.user.param.UserLoginResponse;
import com.ai.opt.data.constants.ThirdUserConstants;
import com.ai.opt.data.dao.mapper.bo.UcMembers;
import com.ai.opt.sdk.util.Md5Encoder;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
//@ContextConfiguration({ "/dubbo/provider/dubbo-provider.xml" })
public class TestLoginSV {

	@Autowired
	ILoginSV iLoginSV;
	
	@Ignore
	@Test
    public void testSSO() throws RPCSystemException{
        String name = "thinkgem";
        UserLoginResponse info = iLoginSV.queryUserByUserName(name);
        System.out.println("result="+JSON.toJSONString(info));
    }
	@Ignore
	@Test
    public void testSSOa() throws RPCSystemException{
        String name = "123456";
        System.out.println("result="+Md5Encoder.encodePassword(name));
    }
	
	
	@Test
    public void testBindThirdUser() throws RPCSystemException{
		UcMembers ucMembers=new UcMembers();
		ucMembers.setUsersource(ThirdUserConstants.UserSource.WEIXIN);
		ucMembers.setThirduid("2");
		ucMembers.setUsername("WEIXIN_"+2);
		String uid=iLoginSV.bindThirdUser(ucMembers);
        System.out.println("uid="+uid);
        
        ThirdUserQueryRequest thirdUser=new ThirdUserQueryRequest();
        thirdUser.setUsersource(ThirdUserConstants.UserSource.WEIXIN);
        thirdUser.setThirduid("2");
        UcMembers thirdUserDb=iLoginSV.queryThirdUser(thirdUser);
        System.out.println("thirdUserDb="+JSON.toJSONString(thirdUserDb));
    }
}
