package com.livinglibrary.realm;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.livinglibrary.po.Permission;
import com.livinglibrary.po.Role;
import com.livinglibrary.po.User;
import com.livinglibrary.service.ShiroService;

public class MyShiroRealm extends AuthorizingRealm{

	@Resource
	ShiroService shiroService;
	
	
	@Override	
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		 //获取登录用户名
        String stuid= (String) principals.getPrimaryPrincipal();
        //查询用户名称
        //User user = shiroService.findUserBystuid(stuid);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        
        List<Role> roles = shiroService.findRolesByStuid(stuid);
        
        for (Role role:roles) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRole());
            List<Permission> permissions = shiroService.findPermissionsByRoleid(role.getId());
            for (Permission permission:permissions) {
                //添加权限
                simpleAuthorizationInfo.addStringPermission(permission.getPermission());
            }
        }
        return simpleAuthorizationInfo;
		
		/*System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		UserInfo userInfo  = (UserInfo)principals.getPrimaryPrincipal();
		for(SysRole role:userInfo.getRoleList()){
		    authorizationInfo.addRole(role.getRole());
		    for(SysPermission p:role.getPermissions()){
		        authorizationInfo.addStringPermission(p.getPermission());
		    }
		}
		return authorizationInfo;*/
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
		
		
		
		//加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (token.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        String stuid = token.getPrincipal().toString();
        User user =  shiroService.findUserBystuid(stuid);
        if (user == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(stuid, user.getPassword().toString(), getName());
            return simpleAuthenticationInfo;
        }
		
	}

}
