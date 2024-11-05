package com.graduation.mapper;

import com.graduation.model.YansouTeacher;
import com.graduation.model.YansouTeacherExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface YansouTeacherMapper {
    long countByExample(YansouTeacherExample example);

    int deleteByExample(YansouTeacherExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(YansouTeacher record);

    int insertSelective(YansouTeacher record);

    List<YansouTeacher> selectByExample(YansouTeacherExample example);

    YansouTeacher selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") YansouTeacher record, @Param("example") YansouTeacherExample example);

    int updateByExample(@Param("record") YansouTeacher record, @Param("example") YansouTeacherExample example);

    int updateByPrimaryKeySelective(YansouTeacher record);

    int updateByPrimaryKey(YansouTeacher record);
    
    YansouTeacher selectTeacherInfoByYansouTeamId(Integer yansouTeamId);
    
    YansouTeacher selectYansouTeacherByTeacherId(String teacherId);
}