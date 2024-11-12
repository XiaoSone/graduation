package com.graduation.realms;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {
//    创建安全管理器
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("loginRealm") LoginRealm loginRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //securityManager.setCacheManager(cacheManager());
        //System.out.println("LoginRealm: " + (loginRealm != null ? "Injected" : "Not Injected"));
        securityManager.setRealm(loginRealm);
        return securityManager;
    }
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

   /* @Bean
    public CacheManager cacheManager() {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(1));
        return (CacheManager) RedisCacheManager.builder(redisConnectionFactory())
                .cacheDefaults(config)
                .build();
    }*/

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("login");
        //shiroFilterFactoryBean.setUnauthorizedUrl("/login2.html");

        // 配置过滤链
        Map<String, String> map = new HashMap(){{
            //匿名
            put("/userController/login", "anon");
            put("/captcha", "anon");
            put("/informController/*", "anon");
            put("/downController/getSubDown", "anon");
            put("/css/**", "anon");
            put("/js/**", "anon");
            put("/images/**", "anon");
            // 认证
            put("/userController/logout", "authc");
            put("/updateInfo", "authc");
            put("/updatePwd", "authc");
            put("/**", "authc");
        }};

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

}
