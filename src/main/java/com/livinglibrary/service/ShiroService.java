package com.livinglibrary.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.livinglibrary.mapper.MyselfMapper;
import com.livinglibrary.mapper.UserMapper;
import com.livinglibrary.po.Permission;
import com.livinglibrary.po.Role;
import com.livinglibrary.po.User;
import com.livinglibrary.po.UserExample;

@Service
public class ShiroService {
	
	@Resource
	UserMapper userMapper;
	@Resource
	MyselfMapper myselfMapper;
	
	public User findUserBystuid(String stuid) {
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andStuidEqualTo(stuid);
		List<User> list = userMapper.selectByExample(example);
		if(list.size()==0) {
			return null;
		}
		return list.get(0);
	}
	
	public List<Role> findRolesByStuid(String stuid){
		return myselfMapper.getRoleListBystuid(stuid);
	}
	public List<Permission> findPermissionsByRoleid(Integer roleid){
		return myselfMapper.getPermissionsByroleid(roleid);
	}
	
}
