package com.graduation.realms;

import com.graduation.model.User;
import com.graduation.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginRealm extends AuthenticatingRealm{
	
	@Autowired
	private UserService userService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        System.out.println("用户名:"+username);
        User user = userService.isUser(username);
        System.out.println("密码："+user.getUserPassword());
        System.out.println("++++："+getName());

        if (user == null){
            throw new UnknownAccountException();
        }else {
            return new SimpleAuthenticationInfo(username, user.getUserPassword(), getName());
        }
	}

}
