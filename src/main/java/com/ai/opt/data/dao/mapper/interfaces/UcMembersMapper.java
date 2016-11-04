package com.ai.opt.data.dao.mapper.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ai.opt.data.dao.mapper.bo.UcMembers;
import com.ai.opt.data.dao.mapper.bo.UcMembersCriteria;

public interface UcMembersMapper {
    int countByExample(UcMembersCriteria example);

    int deleteByExample(UcMembersCriteria example);

    int deleteByPrimaryKey(Integer uid);

    int insert(UcMembers record);

    int insertSelective(UcMembers record);

    List<UcMembers> selectByExample(UcMembersCriteria example);

    UcMembers selectByPrimaryKey(Integer uid);

    UcMembers selectSalt(UcMembers record);
    
    int updateByExampleSelective(@Param("record") UcMembers record, @Param("example") UcMembersCriteria example);

    int updateByExample(@Param("record") UcMembers record, @Param("example") UcMembersCriteria example);

    int updateByPrimaryKeySelective(UcMembers record);

    int updateByPrimaryKey(UcMembers record);
}