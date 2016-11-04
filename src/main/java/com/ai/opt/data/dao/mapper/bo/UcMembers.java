package com.ai.opt.data.dao.mapper.bo;

import java.io.Serializable;

public class UcMembers implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer uid;

    private String username;

    private String password;

    private String email;

    private Integer emailcheck;

    private String myid;

    private String myidkey;

    private String regip;

    private Integer regdate;

    private String lastloginip;

    private Integer lastlogintime;

    private String salt;

    private String secques;

    private String mobilephone;

    private String systemsource;

    private String usersource;

    private String thirduid;

    private String loginmode;

    private String loginway;

    private String enablestatus;

    private String createtime;

    private String domainName;

    private Integer modifydate;

    private Integer logincount;

    private String loginsystem;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getEmailcheck() {
        return emailcheck;
    }

    public void setEmailcheck(Integer emailcheck) {
        this.emailcheck = emailcheck;
    }

    public String getMyid() {
        return myid;
    }

    public void setMyid(String myid) {
        this.myid = myid == null ? null : myid.trim();
    }

    public String getMyidkey() {
        return myidkey;
    }

    public void setMyidkey(String myidkey) {
        this.myidkey = myidkey == null ? null : myidkey.trim();
    }

    public String getRegip() {
        return regip;
    }

    public void setRegip(String regip) {
        this.regip = regip == null ? null : regip.trim();
    }

    public Integer getRegdate() {
        return regdate;
    }

    public void setRegdate(Integer regdate) {
        this.regdate = regdate;
    }

    public String getLastloginip() {
        return lastloginip;
    }

    public void setLastloginip(String lastloginip) {
        this.lastloginip = lastloginip == null ? null : lastloginip.trim();
    }

    public Integer getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Integer lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getSecques() {
        return secques;
    }

    public void setSecques(String secques) {
        this.secques = secques == null ? null : secques.trim();
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone == null ? null : mobilephone.trim();
    }

    public String getSystemsource() {
        return systemsource;
    }

    public void setSystemsource(String systemsource) {
        this.systemsource = systemsource == null ? null : systemsource.trim();
    }

    public String getUsersource() {
        return usersource;
    }

    public void setUsersource(String usersource) {
        this.usersource = usersource == null ? null : usersource.trim();
    }

    public String getThirduid() {
        return thirduid;
    }

    public void setThirduid(String thirduid) {
        this.thirduid = thirduid == null ? null : thirduid.trim();
    }

    public String getLoginmode() {
        return loginmode;
    }

    public void setLoginmode(String loginmode) {
        this.loginmode = loginmode == null ? null : loginmode.trim();
    }

    public String getLoginway() {
        return loginway;
    }

    public void setLoginway(String loginway) {
        this.loginway = loginway == null ? null : loginway.trim();
    }

    public String getEnablestatus() {
        return enablestatus;
    }

    public void setEnablestatus(String enablestatus) {
        this.enablestatus = enablestatus == null ? null : enablestatus.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName == null ? null : domainName.trim();
    }

    public Integer getModifydate() {
        return modifydate;
    }

    public void setModifydate(Integer modifydate) {
        this.modifydate = modifydate;
    }

    public Integer getLogincount() {
        return logincount;
    }

    public void setLogincount(Integer logincount) {
        this.logincount = logincount;
    }

    public String getLoginsystem() {
        return loginsystem;
    }

    public void setLoginsystem(String loginsystem) {
        this.loginsystem = loginsystem == null ? null : loginsystem.trim();
    }
}