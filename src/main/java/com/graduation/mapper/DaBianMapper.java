package com.graduation.mapper;


import com.graduation.model.DaBian;
import com.graduation.model.DaBianExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface DaBianMapper {
    long countByExample(DaBianExample example);

    int deleteByExample(DaBianExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DaBian record);

    int insertSelective(DaBian record);

    List<DaBian> selectByExample(DaBianExample example);

    DaBian selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DaBian record, @Param("example") DaBianExample example);

    int updateByExample(@Param("record") DaBian record, @Param("example") DaBianExample example);

    int updateByPrimaryKeySelective(DaBian record);

    int updateByPrimaryKey(DaBian record);

	DaBian getThisStudentScore(String studentId);
}