package com.graduation.mapper;

import com.graduation.model.Ktbg;
import com.graduation.model.KtbgExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface KtbgMapper {
    long countByExample(KtbgExample example);

    int deleteByExample(KtbgExample example);

    int deleteByPrimaryKey(Integer ktbgId);

    int insert(Ktbg record);

    int insertSelective(Ktbg record);

    List<Ktbg> selectByExample(KtbgExample example);

    Ktbg selectByPrimaryKey(Integer ktbgId);

    int updateByExampleSelective(@Param("record") Ktbg record, @Param("example") KtbgExample example);

    int updateByExample(@Param("record") Ktbg record, @Param("example") KtbgExample example);

    int updateByPrimaryKeySelective(Ktbg record);

    int updateByPrimaryKey(Ktbg record);
    
    Ktbg getKtbgByStudentId(String studentId);
    
    int updateColumn(Ktbg ktbg);
}