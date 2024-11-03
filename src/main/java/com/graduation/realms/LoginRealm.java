package com.graduation.realms;

import com.graduation.model.User;
import com.graduation.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class LoginRealm extends AuthenticatingRealm{
	
	@Autowired
	private UserService userService;

	@Override
    //@Bean
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("doGetAuthenticationInfo1:"+token.hashCode());
        //1.将AuthenticationToken强转为UsernamePasswordToken
        UsernamePasswordToken uspaToken=(UsernamePasswordToken)token;
        //2.获得用户名,密码
        String userName=uspaToken.getUsername();
        String password=new String(uspaToken.getPassword());
        //3.查询数据库获得真实的用户名或密码(这里模拟)
        //若用户不存在,抛出UnknownAccountException
        User user = userService.login(userName, password);
        if(user==null) {
        	throw new UnknownAccountException("用户不存在");
        }
        if(user.getUserRoles()==null||user.getUserRoles()==3){
            throw new LockedAccountException("用户被锁定");
        }
        //4.根据用户信息构建AuthenticationInfo，我们常用其子类：
        //1).principal 用户实体信息  可以是userName，也可以是数据表对应的实体类信息
        Object principal=userName;
        //2).credentials 密码
        Object credentials=password;
        //3).realmName  使用当前realName即可
        String realmName=this.getName();
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(principal, credentials, realmName);
        return info;
	}

}
