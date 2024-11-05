package com.graduation.mapper;

import com.graduation.model.WorkTime;
import com.graduation.model.WorkTimeExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface WorkTimeMapper {
    long countByExample(WorkTimeExample example);

    int deleteByExample(WorkTimeExample example);

    int deleteByPrimaryKey(Integer workTimeId);

    int insert(WorkTime record);

    int insertSelective(WorkTime record);

    List<WorkTime> selectByExample(WorkTimeExample example);

    WorkTime selectByPrimaryKey(Integer workTimeId);

    int updateByExampleSelective(@Param("record") WorkTime record, @Param("example") WorkTimeExample example);

    int updateByExample(@Param("record") WorkTime record, @Param("example") WorkTimeExample example);

    int updateByPrimaryKeySelective(WorkTime record);

    int updateByPrimaryKey(WorkTime record);

    WorkTime getWorkTime(String thisDate);
}