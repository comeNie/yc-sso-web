package com.ai.opt.data.api.user.param;

import java.io.Serializable;

public class ThirdUserQueryRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 用户来源:<br>
	 *  0	内部用户（默认）<br>
	 *	1	金山账号<br>
	 *	2	百度账号<br>
	 *	3	qq账号<br>
	 *	4	预留<br>
	 *	5	预留<br>
	 *	6	新浪账号<br>
	 *	7	微信账号<br>
	 * 
	 */
	private String usersource;

	/**
	 * 第三方登录用户ID
	 */
    private String thirduid;

	public String getUsersource() {
		return usersource;
	}

	public void setUsersource(String usersource) {
		this.usersource = usersource;
	}

	public String getThirduid() {
		return thirduid;
	}

	public void setThirduid(String thirduid) {
		this.thirduid = thirduid;
	}  
	
}
