package com.graduation.mapper;

import com.graduation.model.Lunwen;
import com.graduation.model.LunwenExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface LunwenMapper {
    long countByExample(LunwenExample example);

    int deleteByExample(LunwenExample example);

    int deleteByPrimaryKey(Integer lunwenId);

    int insert(Lunwen record);

    int insertSelective(Lunwen record);

    List<Lunwen> selectByExample(LunwenExample example);

    Lunwen selectByPrimaryKey(Integer lunwenId);

    int updateByExampleSelective(@Param("record") Lunwen record, @Param("example") LunwenExample example);

    int updateByExample(@Param("record") Lunwen record, @Param("example") LunwenExample example);

    int updateByPrimaryKeySelective(Lunwen record);

    int updateByPrimaryKey(Lunwen record);

	Lunwen getLunwenBySid(String studentId);

}