package com.graduation.mapper;

import com.graduation.model.Mdb;
import com.graduation.model.MdbExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface MdbMapper {
    long countByExample(MdbExample example);

    int deleteByExample(MdbExample example);

    int deleteByPrimaryKey(Integer mdbId);

    int insert(Mdb record);

    int insertSelective(Mdb record);

    List<Mdb> selectByExample(MdbExample example);

    Mdb selectByPrimaryKey(Integer mdbId);

    int updateByExampleSelective(@Param("record") Mdb record, @Param("example") MdbExample example);

    int updateByExample(@Param("record") Mdb record, @Param("example") MdbExample example);

    int updateByPrimaryKeySelective(Mdb record);

    int updateByPrimaryKey(Mdb record);

	Mdb getMdbByStudentId(String studentId);
	
	int updateColumn(Mdb mdb);
}