package com.livinglibrary.mapper;

import java.util.List;
import java.util.Map;

import com.livinglibrary.po.AdminList;
import com.livinglibrary.po.Boardmessage;
import com.livinglibrary.po.FcommentsList;
import com.livinglibrary.po.FpostList;
import com.livinglibrary.po.Permission;
import com.livinglibrary.po.Role;
import com.livinglibrary.po.StuYuyue;
import com.livinglibrary.po.Vedio;
import com.livinglibrary.po.YuyueState;

public interface MyselfMapper {
	//获取帖子列表
	List<FpostList> getPostList(Map<String, Object> map);
	//获取评论列表
	List<FcommentsList> getCommentList(Map<String, Object> map);
	//根据stuid获取RoleList用于Shiro
	List<Role> getRoleListBystuid(String stuid);
	//根据roleid获取PermissionList用于Shiro
	List<Permission> getPermissionsByroleid(Integer roleid);
	//根据roleid获取PermissionList用于Shiro
	List<YuyueState> getYuyue(Integer guestid);
	//获取学生预约记录
	List<StuYuyue> getStuYuyue(String stuid);
	//获取广播消息
	List<Boardmessage> loadBoardMessage();
	
	//获取管理员列表
	List<AdminList> getAdminList(Integer type);
	List<AdminList> searchAdminList(Map<String, Object> map);
	List<AdminList> searchAdminListWithoutTime(String username);
	List<Vedio> getVedioByTypeidlimit(Integer typeid);
}
