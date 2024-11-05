package com.graduation.mapper;

import com.graduation.model.YansouTeam;
import com.graduation.model.YansouTeamExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface YansouTeamMapper {
    long countByExample(YansouTeamExample example);

    int deleteByExample(YansouTeamExample example);

    int deleteByPrimaryKey(Integer yansouTeamId);

    int insert(YansouTeam record);

    int insertSelective(YansouTeam record);

    List<YansouTeam> selectByExample(YansouTeamExample example);

    YansouTeam selectByPrimaryKey(Integer yansouTeamId);
    
    YansouTeam selectYanSouInfoByid(Integer yansouTeamId);

    int updateByExampleSelective(@Param("record") YansouTeam record, @Param("example") YansouTeamExample example);

    int updateByExample(@Param("record") YansouTeam record, @Param("example") YansouTeamExample example);

    int updateByPrimaryKeySelective(YansouTeam record);

    int updateByPrimaryKey(YansouTeam record);
}