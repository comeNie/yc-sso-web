package com.ai.opt.data.dao.mapper.bo;

import java.util.ArrayList;
import java.util.List;

public class UcMembersCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public UcMembersCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimitStart(Integer limitStart) {
        this.limitStart=limitStart;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(Integer limitEnd) {
        this.limitEnd=limitEnd;
    }

    public Integer getLimitEnd() {
        return limitEnd;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Integer value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Integer value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Integer value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Integer value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Integer value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Integer> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Integer> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Integer value1, Integer value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Integer value1, Integer value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailcheckIsNull() {
            addCriterion("emailcheck is null");
            return (Criteria) this;
        }

        public Criteria andEmailcheckIsNotNull() {
            addCriterion("emailcheck is not null");
            return (Criteria) this;
        }

        public Criteria andEmailcheckEqualTo(Integer value) {
            addCriterion("emailcheck =", value, "emailcheck");
            return (Criteria) this;
        }

        public Criteria andEmailcheckNotEqualTo(Integer value) {
            addCriterion("emailcheck <>", value, "emailcheck");
            return (Criteria) this;
        }

        public Criteria andEmailcheckGreaterThan(Integer value) {
            addCriterion("emailcheck >", value, "emailcheck");
            return (Criteria) this;
        }

        public Criteria andEmailcheckGreaterThanOrEqualTo(Integer value) {
            addCriterion("emailcheck >=", value, "emailcheck");
            return (Criteria) this;
        }

        public Criteria andEmailcheckLessThan(Integer value) {
            addCriterion("emailcheck <", value, "emailcheck");
            return (Criteria) this;
        }

        public Criteria andEmailcheckLessThanOrEqualTo(Integer value) {
            addCriterion("emailcheck <=", value, "emailcheck");
            return (Criteria) this;
        }

        public Criteria andEmailcheckIn(List<Integer> values) {
            addCriterion("emailcheck in", values, "emailcheck");
            return (Criteria) this;
        }

        public Criteria andEmailcheckNotIn(List<Integer> values) {
            addCriterion("emailcheck not in", values, "emailcheck");
            return (Criteria) this;
        }

        public Criteria andEmailcheckBetween(Integer value1, Integer value2) {
            addCriterion("emailcheck between", value1, value2, "emailcheck");
            return (Criteria) this;
        }

        public Criteria andEmailcheckNotBetween(Integer value1, Integer value2) {
            addCriterion("emailcheck not between", value1, value2, "emailcheck");
            return (Criteria) this;
        }

        public Criteria andMyidIsNull() {
            addCriterion("myid is null");
            return (Criteria) this;
        }

        public Criteria andMyidIsNotNull() {
            addCriterion("myid is not null");
            return (Criteria) this;
        }

        public Criteria andMyidEqualTo(String value) {
            addCriterion("myid =", value, "myid");
            return (Criteria) this;
        }

        public Criteria andMyidNotEqualTo(String value) {
            addCriterion("myid <>", value, "myid");
            return (Criteria) this;
        }

        public Criteria andMyidGreaterThan(String value) {
            addCriterion("myid >", value, "myid");
            return (Criteria) this;
        }

        public Criteria andMyidGreaterThanOrEqualTo(String value) {
            addCriterion("myid >=", value, "myid");
            return (Criteria) this;
        }

        public Criteria andMyidLessThan(String value) {
            addCriterion("myid <", value, "myid");
            return (Criteria) this;
        }

        public Criteria andMyidLessThanOrEqualTo(String value) {
            addCriterion("myid <=", value, "myid");
            return (Criteria) this;
        }

        public Criteria andMyidLike(String value) {
            addCriterion("myid like", value, "myid");
            return (Criteria) this;
        }

        public Criteria andMyidNotLike(String value) {
            addCriterion("myid not like", value, "myid");
            return (Criteria) this;
        }

        public Criteria andMyidIn(List<String> values) {
            addCriterion("myid in", values, "myid");
            return (Criteria) this;
        }

        public Criteria andMyidNotIn(List<String> values) {
            addCriterion("myid not in", values, "myid");
            return (Criteria) this;
        }

        public Criteria andMyidBetween(String value1, String value2) {
            addCriterion("myid between", value1, value2, "myid");
            return (Criteria) this;
        }

        public Criteria andMyidNotBetween(String value1, String value2) {
            addCriterion("myid not between", value1, value2, "myid");
            return (Criteria) this;
        }

        public Criteria andMyidkeyIsNull() {
            addCriterion("myidkey is null");
            return (Criteria) this;
        }

        public Criteria andMyidkeyIsNotNull() {
            addCriterion("myidkey is not null");
            return (Criteria) this;
        }

        public Criteria andMyidkeyEqualTo(String value) {
            addCriterion("myidkey =", value, "myidkey");
            return (Criteria) this;
        }

        public Criteria andMyidkeyNotEqualTo(String value) {
            addCriterion("myidkey <>", value, "myidkey");
            return (Criteria) this;
        }

        public Criteria andMyidkeyGreaterThan(String value) {
            addCriterion("myidkey >", value, "myidkey");
            return (Criteria) this;
        }

        public Criteria andMyidkeyGreaterThanOrEqualTo(String value) {
            addCriterion("myidkey >=", value, "myidkey");
            return (Criteria) this;
        }

        public Criteria andMyidkeyLessThan(String value) {
            addCriterion("myidkey <", value, "myidkey");
            return (Criteria) this;
        }

        public Criteria andMyidkeyLessThanOrEqualTo(String value) {
            addCriterion("myidkey <=", value, "myidkey");
            return (Criteria) this;
        }

        public Criteria andMyidkeyLike(String value) {
            addCriterion("myidkey like", value, "myidkey");
            return (Criteria) this;
        }

        public Criteria andMyidkeyNotLike(String value) {
            addCriterion("myidkey not like", value, "myidkey");
            return (Criteria) this;
        }

        public Criteria andMyidkeyIn(List<String> values) {
            addCriterion("myidkey in", values, "myidkey");
            return (Criteria) this;
        }

        public Criteria andMyidkeyNotIn(List<String> values) {
            addCriterion("myidkey not in", values, "myidkey");
            return (Criteria) this;
        }

        public Criteria andMyidkeyBetween(String value1, String value2) {
            addCriterion("myidkey between", value1, value2, "myidkey");
            return (Criteria) this;
        }

        public Criteria andMyidkeyNotBetween(String value1, String value2) {
            addCriterion("myidkey not between", value1, value2, "myidkey");
            return (Criteria) this;
        }

        public Criteria andRegipIsNull() {
            addCriterion("regip is null");
            return (Criteria) this;
        }

        public Criteria andRegipIsNotNull() {
            addCriterion("regip is not null");
            return (Criteria) this;
        }

        public Criteria andRegipEqualTo(String value) {
            addCriterion("regip =", value, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipNotEqualTo(String value) {
            addCriterion("regip <>", value, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipGreaterThan(String value) {
            addCriterion("regip >", value, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipGreaterThanOrEqualTo(String value) {
            addCriterion("regip >=", value, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipLessThan(String value) {
            addCriterion("regip <", value, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipLessThanOrEqualTo(String value) {
            addCriterion("regip <=", value, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipLike(String value) {
            addCriterion("regip like", value, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipNotLike(String value) {
            addCriterion("regip not like", value, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipIn(List<String> values) {
            addCriterion("regip in", values, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipNotIn(List<String> values) {
            addCriterion("regip not in", values, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipBetween(String value1, String value2) {
            addCriterion("regip between", value1, value2, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipNotBetween(String value1, String value2) {
            addCriterion("regip not between", value1, value2, "regip");
            return (Criteria) this;
        }

        public Criteria andRegdateIsNull() {
            addCriterion("regdate is null");
            return (Criteria) this;
        }

        public Criteria andRegdateIsNotNull() {
            addCriterion("regdate is not null");
            return (Criteria) this;
        }

        public Criteria andRegdateEqualTo(Integer value) {
            addCriterion("regdate =", value, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateNotEqualTo(Integer value) {
            addCriterion("regdate <>", value, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateGreaterThan(Integer value) {
            addCriterion("regdate >", value, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateGreaterThanOrEqualTo(Integer value) {
            addCriterion("regdate >=", value, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateLessThan(Integer value) {
            addCriterion("regdate <", value, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateLessThanOrEqualTo(Integer value) {
            addCriterion("regdate <=", value, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateIn(List<Integer> values) {
            addCriterion("regdate in", values, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateNotIn(List<Integer> values) {
            addCriterion("regdate not in", values, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateBetween(Integer value1, Integer value2) {
            addCriterion("regdate between", value1, value2, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateNotBetween(Integer value1, Integer value2) {
            addCriterion("regdate not between", value1, value2, "regdate");
            return (Criteria) this;
        }

        public Criteria andLastloginipIsNull() {
            addCriterion("lastloginip is null");
            return (Criteria) this;
        }

        public Criteria andLastloginipIsNotNull() {
            addCriterion("lastloginip is not null");
            return (Criteria) this;
        }

        public Criteria andLastloginipEqualTo(String value) {
            addCriterion("lastloginip =", value, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastloginipNotEqualTo(String value) {
            addCriterion("lastloginip <>", value, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastloginipGreaterThan(String value) {
            addCriterion("lastloginip >", value, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastloginipGreaterThanOrEqualTo(String value) {
            addCriterion("lastloginip >=", value, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastloginipLessThan(String value) {
            addCriterion("lastloginip <", value, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastloginipLessThanOrEqualTo(String value) {
            addCriterion("lastloginip <=", value, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastloginipLike(String value) {
            addCriterion("lastloginip like", value, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastloginipNotLike(String value) {
            addCriterion("lastloginip not like", value, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastloginipIn(List<String> values) {
            addCriterion("lastloginip in", values, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastloginipNotIn(List<String> values) {
            addCriterion("lastloginip not in", values, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastloginipBetween(String value1, String value2) {
            addCriterion("lastloginip between", value1, value2, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastloginipNotBetween(String value1, String value2) {
            addCriterion("lastloginip not between", value1, value2, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeIsNull() {
            addCriterion("lastlogintime is null");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeIsNotNull() {
            addCriterion("lastlogintime is not null");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeEqualTo(Integer value) {
            addCriterion("lastlogintime =", value, "lastlogintime");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeNotEqualTo(Integer value) {
            addCriterion("lastlogintime <>", value, "lastlogintime");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeGreaterThan(Integer value) {
            addCriterion("lastlogintime >", value, "lastlogintime");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("lastlogintime >=", value, "lastlogintime");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeLessThan(Integer value) {
            addCriterion("lastlogintime <", value, "lastlogintime");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeLessThanOrEqualTo(Integer value) {
            addCriterion("lastlogintime <=", value, "lastlogintime");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeIn(List<Integer> values) {
            addCriterion("lastlogintime in", values, "lastlogintime");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeNotIn(List<Integer> values) {
            addCriterion("lastlogintime not in", values, "lastlogintime");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeBetween(Integer value1, Integer value2) {
            addCriterion("lastlogintime between", value1, value2, "lastlogintime");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeNotBetween(Integer value1, Integer value2) {
            addCriterion("lastlogintime not between", value1, value2, "lastlogintime");
            return (Criteria) this;
        }

        public Criteria andSaltIsNull() {
            addCriterion("salt is null");
            return (Criteria) this;
        }

        public Criteria andSaltIsNotNull() {
            addCriterion("salt is not null");
            return (Criteria) this;
        }

        public Criteria andSaltEqualTo(String value) {
            addCriterion("salt =", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotEqualTo(String value) {
            addCriterion("salt <>", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltGreaterThan(String value) {
            addCriterion("salt >", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltGreaterThanOrEqualTo(String value) {
            addCriterion("salt >=", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLessThan(String value) {
            addCriterion("salt <", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLessThanOrEqualTo(String value) {
            addCriterion("salt <=", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLike(String value) {
            addCriterion("salt like", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotLike(String value) {
            addCriterion("salt not like", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltIn(List<String> values) {
            addCriterion("salt in", values, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotIn(List<String> values) {
            addCriterion("salt not in", values, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltBetween(String value1, String value2) {
            addCriterion("salt between", value1, value2, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotBetween(String value1, String value2) {
            addCriterion("salt not between", value1, value2, "salt");
            return (Criteria) this;
        }

        public Criteria andSecquesIsNull() {
            addCriterion("secques is null");
            return (Criteria) this;
        }

        public Criteria andSecquesIsNotNull() {
            addCriterion("secques is not null");
            return (Criteria) this;
        }

        public Criteria andSecquesEqualTo(String value) {
            addCriterion("secques =", value, "secques");
            return (Criteria) this;
        }

        public Criteria andSecquesNotEqualTo(String value) {
            addCriterion("secques <>", value, "secques");
            return (Criteria) this;
        }

        public Criteria andSecquesGreaterThan(String value) {
            addCriterion("secques >", value, "secques");
            return (Criteria) this;
        }

        public Criteria andSecquesGreaterThanOrEqualTo(String value) {
            addCriterion("secques >=", value, "secques");
            return (Criteria) this;
        }

        public Criteria andSecquesLessThan(String value) {
            addCriterion("secques <", value, "secques");
            return (Criteria) this;
        }

        public Criteria andSecquesLessThanOrEqualTo(String value) {
            addCriterion("secques <=", value, "secques");
            return (Criteria) this;
        }

        public Criteria andSecquesLike(String value) {
            addCriterion("secques like", value, "secques");
            return (Criteria) this;
        }

        public Criteria andSecquesNotLike(String value) {
            addCriterion("secques not like", value, "secques");
            return (Criteria) this;
        }

        public Criteria andSecquesIn(List<String> values) {
            addCriterion("secques in", values, "secques");
            return (Criteria) this;
        }

        public Criteria andSecquesNotIn(List<String> values) {
            addCriterion("secques not in", values, "secques");
            return (Criteria) this;
        }

        public Criteria andSecquesBetween(String value1, String value2) {
            addCriterion("secques between", value1, value2, "secques");
            return (Criteria) this;
        }

        public Criteria andSecquesNotBetween(String value1, String value2) {
            addCriterion("secques not between", value1, value2, "secques");
            return (Criteria) this;
        }

        public Criteria andMobilephoneIsNull() {
            addCriterion("mobilephone is null");
            return (Criteria) this;
        }

        public Criteria andMobilephoneIsNotNull() {
            addCriterion("mobilephone is not null");
            return (Criteria) this;
        }

        public Criteria andMobilephoneEqualTo(String value) {
            addCriterion("mobilephone =", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneNotEqualTo(String value) {
            addCriterion("mobilephone <>", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneGreaterThan(String value) {
            addCriterion("mobilephone >", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneGreaterThanOrEqualTo(String value) {
            addCriterion("mobilephone >=", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneLessThan(String value) {
            addCriterion("mobilephone <", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneLessThanOrEqualTo(String value) {
            addCriterion("mobilephone <=", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneLike(String value) {
            addCriterion("mobilephone like", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneNotLike(String value) {
            addCriterion("mobilephone not like", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneIn(List<String> values) {
            addCriterion("mobilephone in", values, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneNotIn(List<String> values) {
            addCriterion("mobilephone not in", values, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneBetween(String value1, String value2) {
            addCriterion("mobilephone between", value1, value2, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneNotBetween(String value1, String value2) {
            addCriterion("mobilephone not between", value1, value2, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andSystemsourceIsNull() {
            addCriterion("systemsource is null");
            return (Criteria) this;
        }

        public Criteria andSystemsourceIsNotNull() {
            addCriterion("systemsource is not null");
            return (Criteria) this;
        }

        public Criteria andSystemsourceEqualTo(String value) {
            addCriterion("systemsource =", value, "systemsource");
            return (Criteria) this;
        }

        public Criteria andSystemsourceNotEqualTo(String value) {
            addCriterion("systemsource <>", value, "systemsource");
            return (Criteria) this;
        }

        public Criteria andSystemsourceGreaterThan(String value) {
            addCriterion("systemsource >", value, "systemsource");
            return (Criteria) this;
        }

        public Criteria andSystemsourceGreaterThanOrEqualTo(String value) {
            addCriterion("systemsource >=", value, "systemsource");
            return (Criteria) this;
        }

        public Criteria andSystemsourceLessThan(String value) {
            addCriterion("systemsource <", value, "systemsource");
            return (Criteria) this;
        }

        public Criteria andSystemsourceLessThanOrEqualTo(String value) {
            addCriterion("systemsource <=", value, "systemsource");
            return (Criteria) this;
        }

        public Criteria andSystemsourceLike(String value) {
            addCriterion("systemsource like", value, "systemsource");
            return (Criteria) this;
        }

        public Criteria andSystemsourceNotLike(String value) {
            addCriterion("systemsource not like", value, "systemsource");
            return (Criteria) this;
        }

        public Criteria andSystemsourceIn(List<String> values) {
            addCriterion("systemsource in", values, "systemsource");
            return (Criteria) this;
        }

        public Criteria andSystemsourceNotIn(List<String> values) {
            addCriterion("systemsource not in", values, "systemsource");
            return (Criteria) this;
        }

        public Criteria andSystemsourceBetween(String value1, String value2) {
            addCriterion("systemsource between", value1, value2, "systemsource");
            return (Criteria) this;
        }

        public Criteria andSystemsourceNotBetween(String value1, String value2) {
            addCriterion("systemsource not between", value1, value2, "systemsource");
            return (Criteria) this;
        }

        public Criteria andUsersourceIsNull() {
            addCriterion("usersource is null");
            return (Criteria) this;
        }

        public Criteria andUsersourceIsNotNull() {
            addCriterion("usersource is not null");
            return (Criteria) this;
        }

        public Criteria andUsersourceEqualTo(String value) {
            addCriterion("usersource =", value, "usersource");
            return (Criteria) this;
        }

        public Criteria andUsersourceNotEqualTo(String value) {
            addCriterion("usersource <>", value, "usersource");
            return (Criteria) this;
        }

        public Criteria andUsersourceGreaterThan(String value) {
            addCriterion("usersource >", value, "usersource");
            return (Criteria) this;
        }

        public Criteria andUsersourceGreaterThanOrEqualTo(String value) {
            addCriterion("usersource >=", value, "usersource");
            return (Criteria) this;
        }

        public Criteria andUsersourceLessThan(String value) {
            addCriterion("usersource <", value, "usersource");
            return (Criteria) this;
        }

        public Criteria andUsersourceLessThanOrEqualTo(String value) {
            addCriterion("usersource <=", value, "usersource");
            return (Criteria) this;
        }

        public Criteria andUsersourceLike(String value) {
            addCriterion("usersource like", value, "usersource");
            return (Criteria) this;
        }

        public Criteria andUsersourceNotLike(String value) {
            addCriterion("usersource not like", value, "usersource");
            return (Criteria) this;
        }

        public Criteria andUsersourceIn(List<String> values) {
            addCriterion("usersource in", values, "usersource");
            return (Criteria) this;
        }

        public Criteria andUsersourceNotIn(List<String> values) {
            addCriterion("usersource not in", values, "usersource");
            return (Criteria) this;
        }

        public Criteria andUsersourceBetween(String value1, String value2) {
            addCriterion("usersource between", value1, value2, "usersource");
            return (Criteria) this;
        }

        public Criteria andUsersourceNotBetween(String value1, String value2) {
            addCriterion("usersource not between", value1, value2, "usersource");
            return (Criteria) this;
        }

        public Criteria andThirduidIsNull() {
            addCriterion("thirduid is null");
            return (Criteria) this;
        }

        public Criteria andThirduidIsNotNull() {
            addCriterion("thirduid is not null");
            return (Criteria) this;
        }

        public Criteria andThirduidEqualTo(String value) {
            addCriterion("thirduid =", value, "thirduid");
            return (Criteria) this;
        }

        public Criteria andThirduidNotEqualTo(String value) {
            addCriterion("thirduid <>", value, "thirduid");
            return (Criteria) this;
        }

        public Criteria andThirduidGreaterThan(String value) {
            addCriterion("thirduid >", value, "thirduid");
            return (Criteria) this;
        }

        public Criteria andThirduidGreaterThanOrEqualTo(String value) {
            addCriterion("thirduid >=", value, "thirduid");
            return (Criteria) this;
        }

        public Criteria andThirduidLessThan(String value) {
            addCriterion("thirduid <", value, "thirduid");
            return (Criteria) this;
        }

        public Criteria andThirduidLessThanOrEqualTo(String value) {
            addCriterion("thirduid <=", value, "thirduid");
            return (Criteria) this;
        }

        public Criteria andThirduidLike(String value) {
            addCriterion("thirduid like", value, "thirduid");
            return (Criteria) this;
        }

        public Criteria andThirduidNotLike(String value) {
            addCriterion("thirduid not like", value, "thirduid");
            return (Criteria) this;
        }

        public Criteria andThirduidIn(List<String> values) {
            addCriterion("thirduid in", values, "thirduid");
            return (Criteria) this;
        }

        public Criteria andThirduidNotIn(List<String> values) {
            addCriterion("thirduid not in", values, "thirduid");
            return (Criteria) this;
        }

        public Criteria andThirduidBetween(String value1, String value2) {
            addCriterion("thirduid between", value1, value2, "thirduid");
            return (Criteria) this;
        }

        public Criteria andThirduidNotBetween(String value1, String value2) {
            addCriterion("thirduid not between", value1, value2, "thirduid");
            return (Criteria) this;
        }

        public Criteria andLoginmodeIsNull() {
            addCriterion("loginmode is null");
            return (Criteria) this;
        }

        public Criteria andLoginmodeIsNotNull() {
            addCriterion("loginmode is not null");
            return (Criteria) this;
        }

        public Criteria andLoginmodeEqualTo(String value) {
            addCriterion("loginmode =", value, "loginmode");
            return (Criteria) this;
        }

        public Criteria andLoginmodeNotEqualTo(String value) {
            addCriterion("loginmode <>", value, "loginmode");
            return (Criteria) this;
        }

        public Criteria andLoginmodeGreaterThan(String value) {
            addCriterion("loginmode >", value, "loginmode");
            return (Criteria) this;
        }

        public Criteria andLoginmodeGreaterThanOrEqualTo(String value) {
            addCriterion("loginmode >=", value, "loginmode");
            return (Criteria) this;
        }

        public Criteria andLoginmodeLessThan(String value) {
            addCriterion("loginmode <", value, "loginmode");
            return (Criteria) this;
        }

        public Criteria andLoginmodeLessThanOrEqualTo(String value) {
            addCriterion("loginmode <=", value, "loginmode");
            return (Criteria) this;
        }

        public Criteria andLoginmodeLike(String value) {
            addCriterion("loginmode like", value, "loginmode");
            return (Criteria) this;
        }

        public Criteria andLoginmodeNotLike(String value) {
            addCriterion("loginmode not like", value, "loginmode");
            return (Criteria) this;
        }

        public Criteria andLoginmodeIn(List<String> values) {
            addCriterion("loginmode in", values, "loginmode");
            return (Criteria) this;
        }

        public Criteria andLoginmodeNotIn(List<String> values) {
            addCriterion("loginmode not in", values, "loginmode");
            return (Criteria) this;
        }

        public Criteria andLoginmodeBetween(String value1, String value2) {
            addCriterion("loginmode between", value1, value2, "loginmode");
            return (Criteria) this;
        }

        public Criteria andLoginmodeNotBetween(String value1, String value2) {
            addCriterion("loginmode not between", value1, value2, "loginmode");
            return (Criteria) this;
        }

        public Criteria andLoginwayIsNull() {
            addCriterion("loginway is null");
            return (Criteria) this;
        }

        public Criteria andLoginwayIsNotNull() {
            addCriterion("loginway is not null");
            return (Criteria) this;
        }

        public Criteria andLoginwayEqualTo(String value) {
            addCriterion("loginway =", value, "loginway");
            return (Criteria) this;
        }

        public Criteria andLoginwayNotEqualTo(String value) {
            addCriterion("loginway <>", value, "loginway");
            return (Criteria) this;
        }

        public Criteria andLoginwayGreaterThan(String value) {
            addCriterion("loginway >", value, "loginway");
            return (Criteria) this;
        }

        public Criteria andLoginwayGreaterThanOrEqualTo(String value) {
            addCriterion("loginway >=", value, "loginway");
            return (Criteria) this;
        }

        public Criteria andLoginwayLessThan(String value) {
            addCriterion("loginway <", value, "loginway");
            return (Criteria) this;
        }

        public Criteria andLoginwayLessThanOrEqualTo(String value) {
            addCriterion("loginway <=", value, "loginway");
            return (Criteria) this;
        }

        public Criteria andLoginwayLike(String value) {
            addCriterion("loginway like", value, "loginway");
            return (Criteria) this;
        }

        public Criteria andLoginwayNotLike(String value) {
            addCriterion("loginway not like", value, "loginway");
            return (Criteria) this;
        }

        public Criteria andLoginwayIn(List<String> values) {
            addCriterion("loginway in", values, "loginway");
            return (Criteria) this;
        }

        public Criteria andLoginwayNotIn(List<String> values) {
            addCriterion("loginway not in", values, "loginway");
            return (Criteria) this;
        }

        public Criteria andLoginwayBetween(String value1, String value2) {
            addCriterion("loginway between", value1, value2, "loginway");
            return (Criteria) this;
        }

        public Criteria andLoginwayNotBetween(String value1, String value2) {
            addCriterion("loginway not between", value1, value2, "loginway");
            return (Criteria) this;
        }

        public Criteria andEnablestatusIsNull() {
            addCriterion("enablestatus is null");
            return (Criteria) this;
        }

        public Criteria andEnablestatusIsNotNull() {
            addCriterion("enablestatus is not null");
            return (Criteria) this;
        }

        public Criteria andEnablestatusEqualTo(String value) {
            addCriterion("enablestatus =", value, "enablestatus");
            return (Criteria) this;
        }

        public Criteria andEnablestatusNotEqualTo(String value) {
            addCriterion("enablestatus <>", value, "enablestatus");
            return (Criteria) this;
        }

        public Criteria andEnablestatusGreaterThan(String value) {
            addCriterion("enablestatus >", value, "enablestatus");
            return (Criteria) this;
        }

        public Criteria andEnablestatusGreaterThanOrEqualTo(String value) {
            addCriterion("enablestatus >=", value, "enablestatus");
            return (Criteria) this;
        }

        public Criteria andEnablestatusLessThan(String value) {
            addCriterion("enablestatus <", value, "enablestatus");
            return (Criteria) this;
        }

        public Criteria andEnablestatusLessThanOrEqualTo(String value) {
            addCriterion("enablestatus <=", value, "enablestatus");
            return (Criteria) this;
        }

        public Criteria andEnablestatusLike(String value) {
            addCriterion("enablestatus like", value, "enablestatus");
            return (Criteria) this;
        }

        public Criteria andEnablestatusNotLike(String value) {
            addCriterion("enablestatus not like", value, "enablestatus");
            return (Criteria) this;
        }

        public Criteria andEnablestatusIn(List<String> values) {
            addCriterion("enablestatus in", values, "enablestatus");
            return (Criteria) this;
        }

        public Criteria andEnablestatusNotIn(List<String> values) {
            addCriterion("enablestatus not in", values, "enablestatus");
            return (Criteria) this;
        }

        public Criteria andEnablestatusBetween(String value1, String value2) {
            addCriterion("enablestatus between", value1, value2, "enablestatus");
            return (Criteria) this;
        }

        public Criteria andEnablestatusNotBetween(String value1, String value2) {
            addCriterion("enablestatus not between", value1, value2, "enablestatus");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(String value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(String value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(String value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(String value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(String value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(String value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLike(String value) {
            addCriterion("createtime like", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotLike(String value) {
            addCriterion("createtime not like", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<String> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<String> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(String value1, String value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(String value1, String value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andDomainNameIsNull() {
            addCriterion("domain_name is null");
            return (Criteria) this;
        }

        public Criteria andDomainNameIsNotNull() {
            addCriterion("domain_name is not null");
            return (Criteria) this;
        }

        public Criteria andDomainNameEqualTo(String value) {
            addCriterion("domain_name =", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameNotEqualTo(String value) {
            addCriterion("domain_name <>", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameGreaterThan(String value) {
            addCriterion("domain_name >", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameGreaterThanOrEqualTo(String value) {
            addCriterion("domain_name >=", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameLessThan(String value) {
            addCriterion("domain_name <", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameLessThanOrEqualTo(String value) {
            addCriterion("domain_name <=", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameLike(String value) {
            addCriterion("domain_name like", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameNotLike(String value) {
            addCriterion("domain_name not like", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameIn(List<String> values) {
            addCriterion("domain_name in", values, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameNotIn(List<String> values) {
            addCriterion("domain_name not in", values, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameBetween(String value1, String value2) {
            addCriterion("domain_name between", value1, value2, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameNotBetween(String value1, String value2) {
            addCriterion("domain_name not between", value1, value2, "domainName");
            return (Criteria) this;
        }

        public Criteria andModifydateIsNull() {
            addCriterion("modifydate is null");
            return (Criteria) this;
        }

        public Criteria andModifydateIsNotNull() {
            addCriterion("modifydate is not null");
            return (Criteria) this;
        }

        public Criteria andModifydateEqualTo(Integer value) {
            addCriterion("modifydate =", value, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateNotEqualTo(Integer value) {
            addCriterion("modifydate <>", value, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateGreaterThan(Integer value) {
            addCriterion("modifydate >", value, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateGreaterThanOrEqualTo(Integer value) {
            addCriterion("modifydate >=", value, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateLessThan(Integer value) {
            addCriterion("modifydate <", value, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateLessThanOrEqualTo(Integer value) {
            addCriterion("modifydate <=", value, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateIn(List<Integer> values) {
            addCriterion("modifydate in", values, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateNotIn(List<Integer> values) {
            addCriterion("modifydate not in", values, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateBetween(Integer value1, Integer value2) {
            addCriterion("modifydate between", value1, value2, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateNotBetween(Integer value1, Integer value2) {
            addCriterion("modifydate not between", value1, value2, "modifydate");
            return (Criteria) this;
        }

        public Criteria andLogincountIsNull() {
            addCriterion("logincount is null");
            return (Criteria) this;
        }

        public Criteria andLogincountIsNotNull() {
            addCriterion("logincount is not null");
            return (Criteria) this;
        }

        public Criteria andLogincountEqualTo(Integer value) {
            addCriterion("logincount =", value, "logincount");
            return (Criteria) this;
        }

        public Criteria andLogincountNotEqualTo(Integer value) {
            addCriterion("logincount <>", value, "logincount");
            return (Criteria) this;
        }

        public Criteria andLogincountGreaterThan(Integer value) {
            addCriterion("logincount >", value, "logincount");
            return (Criteria) this;
        }

        public Criteria andLogincountGreaterThanOrEqualTo(Integer value) {
            addCriterion("logincount >=", value, "logincount");
            return (Criteria) this;
        }

        public Criteria andLogincountLessThan(Integer value) {
            addCriterion("logincount <", value, "logincount");
            return (Criteria) this;
        }

        public Criteria andLogincountLessThanOrEqualTo(Integer value) {
            addCriterion("logincount <=", value, "logincount");
            return (Criteria) this;
        }

        public Criteria andLogincountIn(List<Integer> values) {
            addCriterion("logincount in", values, "logincount");
            return (Criteria) this;
        }

        public Criteria andLogincountNotIn(List<Integer> values) {
            addCriterion("logincount not in", values, "logincount");
            return (Criteria) this;
        }

        public Criteria andLogincountBetween(Integer value1, Integer value2) {
            addCriterion("logincount between", value1, value2, "logincount");
            return (Criteria) this;
        }

        public Criteria andLogincountNotBetween(Integer value1, Integer value2) {
            addCriterion("logincount not between", value1, value2, "logincount");
            return (Criteria) this;
        }

        public Criteria andLoginsystemIsNull() {
            addCriterion("loginsystem is null");
            return (Criteria) this;
        }

        public Criteria andLoginsystemIsNotNull() {
            addCriterion("loginsystem is not null");
            return (Criteria) this;
        }

        public Criteria andLoginsystemEqualTo(String value) {
            addCriterion("loginsystem =", value, "loginsystem");
            return (Criteria) this;
        }

        public Criteria andLoginsystemNotEqualTo(String value) {
            addCriterion("loginsystem <>", value, "loginsystem");
            return (Criteria) this;
        }

        public Criteria andLoginsystemGreaterThan(String value) {
            addCriterion("loginsystem >", value, "loginsystem");
            return (Criteria) this;
        }

        public Criteria andLoginsystemGreaterThanOrEqualTo(String value) {
            addCriterion("loginsystem >=", value, "loginsystem");
            return (Criteria) this;
        }

        public Criteria andLoginsystemLessThan(String value) {
            addCriterion("loginsystem <", value, "loginsystem");
            return (Criteria) this;
        }

        public Criteria andLoginsystemLessThanOrEqualTo(String value) {
            addCriterion("loginsystem <=", value, "loginsystem");
            return (Criteria) this;
        }

        public Criteria andLoginsystemLike(String value) {
            addCriterion("loginsystem like", value, "loginsystem");
            return (Criteria) this;
        }

        public Criteria andLoginsystemNotLike(String value) {
            addCriterion("loginsystem not like", value, "loginsystem");
            return (Criteria) this;
        }

        public Criteria andLoginsystemIn(List<String> values) {
            addCriterion("loginsystem in", values, "loginsystem");
            return (Criteria) this;
        }

        public Criteria andLoginsystemNotIn(List<String> values) {
            addCriterion("loginsystem not in", values, "loginsystem");
            return (Criteria) this;
        }

        public Criteria andLoginsystemBetween(String value1, String value2) {
            addCriterion("loginsystem between", value1, value2, "loginsystem");
            return (Criteria) this;
        }

        public Criteria andLoginsystemNotBetween(String value1, String value2) {
            addCriterion("loginsystem not between", value1, value2, "loginsystem");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}