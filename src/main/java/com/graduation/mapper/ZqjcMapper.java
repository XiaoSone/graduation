package com.graduation.mapper;

import com.graduation.model.Zqjc;
import com.graduation.model.ZqjcExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface ZqjcMapper {
    long countByExample(ZqjcExample example);

    int deleteByExample(ZqjcExample example);

    int deleteByPrimaryKey(Integer zqjcId);

    int insert(Zqjc record);

    int insertSelective(Zqjc record);

    List<Zqjc> selectByExample(ZqjcExample example);

    Zqjc selectByPrimaryKey(Integer zqjcId);

    int updateByExampleSelective(@Param("record") Zqjc record, @Param("example") ZqjcExample example);

    int updateByExample(@Param("record") Zqjc record, @Param("example") ZqjcExample example);

    int updateByPrimaryKeySelective(Zqjc record);

    int updateByPrimaryKey(Zqjc record);

	Zqjc getzqjcByStudentId(String studentId);
}