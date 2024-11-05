package com.graduation.mapper;

import com.graduation.model.Inform;
import com.graduation.model.InformExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface InformMapper {
    long countByExample(InformExample example);

    int deleteByExample(InformExample example);

    int deleteByPrimaryKey(Integer informId);

    int insert(Inform record);

    int insertSelective(Inform record);

    List<Inform> selectByExample(InformExample example);
    
    List<Inform> selectTitleAndId();

    Inform selectByPrimaryKey(Integer informId);

    int updateByExampleSelective(@Param("record") Inform record, @Param("example") InformExample example);

    int updateByExample(@Param("record") Inform record, @Param("example") InformExample example);

    int updateByPrimaryKeySelective(Inform record);

    int updateByPrimaryKey(Inform record);
}