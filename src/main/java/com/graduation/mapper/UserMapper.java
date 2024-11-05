package com.graduation.mapper;

import com.graduation.model.User;
import com.graduation.model.UserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //@Select("SELECT * FROM user WHERE user_account = #{account}")
    User isUser(String account);

    //@Select("select * from user where user_account = #{account} and user_password=#{password}")
    User login(@Param("account")String account,@Param("password")String password);

    //User ispassword(String password);
}
