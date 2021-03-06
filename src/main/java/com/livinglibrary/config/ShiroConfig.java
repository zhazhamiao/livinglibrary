package com.livinglibrary.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.livinglibrary.realm.MyShiroRealm;

@Configuration
@AutoConfigureAfter(ShiroLifecycleBeanPostProcessorConfig.class)
public class ShiroConfig {
	
/*	@Bean
	public FilterRegistrationBean delegatingFilterProxy(){
	    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
	    DelegatingFilterProxy proxy = new DelegatingFilterProxy();
	    proxy.setTargetFilterLifecycle(true);
	    proxy.setTargetBeanName("shiroFilter");
	    filterRegistrationBean.setFilter(proxy);
	    return filterRegistrationBean;
	}

	*/
	@Bean("shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
	    System.out.println("ShiroConfiguration.shirFilter()");
	    
	    
	    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> map = new HashMap<String, String>();
        map.put("/css/**", "anon");
        map.put("/js/**", "anon");
        map.put("/images/**", "anon");
        map.put("/aindex", "anon");
        map.put("/druid/**", "anon");
        
        //登出
        //map.put("/logout","logout");
        //对所有用户认证
        map.put("/**","anon");
        //登录
        shiroFilterFactoryBean.setLoginUrl("/alogin");
        //首页
        shiroFilterFactoryBean.setSuccessUrl("/aindex");
        //错误页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/alogin");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
	    /*
	    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
	    shiroFilterFactoryBean.setSecurityManager(securityManager);*/
	    //拦截器.
	   /* Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
	    // 配置不会被拦截的链接 顺序判断
	    filterChainDefinitionMap.put("/static/**", "anon");
	    filterChainDefinitionMap.put("/loginfor", "anon");
	    //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
	    filterChainDefinitionMap.put("/logout", "logout");
	    //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
	    //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
	    filterChainDefinitionMap.put("/**", "authc");
	    // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
	    shiroFilterFactoryBean.setLoginUrl("/alogin");
	    // 登录成功后要跳转的链接
	    shiroFilterFactoryBean.setSuccessUrl("/aindex");
	
	    //未授权界面;
	    shiroFilterFactoryBean.setUnauthorizedUrl("/403");
	    shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
	    return shiroFilterFactoryBean;*/
	}
	@Bean(name = "myShiroRealm")
	public MyShiroRealm myShiroRealm(){
	    MyShiroRealm myShiroRealm = new MyShiroRealm();
	    return myShiroRealm;
	}

    @Bean(name = "securityManager")
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }
    
    //加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}