package com.graduation.mapper;

import com.graduation.model.Down;
import com.graduation.model.DownExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface DownMapper {
    long countByExample(DownExample example);

    int deleteByExample(DownExample example);

    int deleteByPrimaryKey(Integer downId);

    int insert(Down record);

    int insertSelective(Down record);

    List<Down> selectByExample(DownExample example);

    Down selectByPrimaryKey(Integer downId);

    int updateByExampleSelective(@Param("record") Down record, @Param("example") DownExample example);

    int updateByExample(@Param("record") Down record, @Param("example") DownExample example);

    int updateByPrimaryKeySelective(Down record);

    int updateByPrimaryKey(Down record);
}