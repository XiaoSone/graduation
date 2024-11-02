package com.graduation.mapper;

import com.graduation.model.ProjectFrom;
import com.graduation.model.ProjectFromExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectFromMapper {
    long countByExample(ProjectFromExample example);

    int deleteByExample(ProjectFromExample example);

    int deleteByPrimaryKey(Integer projectFromId);

    int insert(ProjectFrom record);

    int insertSelective(ProjectFrom record);

    List<ProjectFrom> selectByExample(ProjectFromExample example);

    ProjectFrom selectByPrimaryKey(Integer projectFromId);

    int updateByExampleSelective(@Param("record") ProjectFrom record, @Param("example") ProjectFromExample example);

    int updateByExample(@Param("record") ProjectFrom record, @Param("example") ProjectFromExample example);

    int updateByPrimaryKeySelective(ProjectFrom record);

    int updateByPrimaryKey(ProjectFrom record);
}