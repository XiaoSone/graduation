package com.graduation.realms;

import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {
//    创建安全管理器

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("loginRealm") LoginRealm loginRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(loginRealm);

        //securityManager.setSessionManager(sessionManager(redisSessionDAO(redisConnectionFactory())));

        return securityManager;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        //shiroFilterFactoryBean.setUnauthorizedUrl("/login2.html");

        // 配置过滤链,使用LinkedHashMap保证顺序
        Map<String, String> map = new LinkedHashMap(){{
            //匿名
            put("/userController/login", "anon");
            put("/captcha", "anon");
            put("/informController/*", "anon");
            put("/workTimeController/getWorkTime", "anon");
            put("/downController/*", "anon");
            put("/css/**", "anon");
            put("/js/**", "anon");
            put("/images/**", "anon");
            // 认证
            put("/userController/logout", "authc");
            put("/updateInfo", "authc");
            put("/updatePwd", "authc");
            put("/projectController/*", "authc");
            put("/**", "authc");
        }};

        map.forEach((k, v) -> System.out.println("Path: " + k + " -> Filter: " + v));

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }






    //    @Bean
    //    public RedisManager redisManager(RedisConnectionFactory redisConnectionFactory) {
    //        RedisManager redisManager = new RedisManager();
    //        redisManager.setHost("127.0.0.1:6379"); // 配置 Redis 连接信息
    //        redisManager.setTimeout(2000); // 可选：设置超时时间
    //        return redisManager;
    //    }
    //    @Bean
    //    public RedisSessionDAO redisSessionDAO(RedisManager redisManager) {
    //        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
    //        redisSessionDAO.setRedisManager(redisManager);
    //        redisSessionDAO.setExpire(1800); // 可选：设置会话过期时间，单位为秒
    //        return redisSessionDAO;
    //    }
    //
    //    @Bean
    //    public DefaultWebSessionManager sessionManager(RedisSessionDAO redisSessionDAO) {
    //        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
    //        sessionManager.setSessionDAO(redisSessionDAO);
    //        sessionManager.setGlobalSessionTimeout(1800000); // 会话超时时间，单位为毫秒
    //        sessionManager.setDeleteInvalidSessions(true);
    //        sessionManager.setSessionIdUrlRewritingEnabled(false);
    //        return sessionManager;
    //    }

    //@Bean(name = "securityManager")
    //public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("loginRealm") LoginRealm loginRealm,
    //                                                              @Qualifier("sessionManager") DefaultWebSessionManager sessionManager) {
    //    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    //    securityManager.setRealm(loginRealm);
    //    securityManager.setSessionManager(sessionManager);
    //    return securityManager;
    //}

}
