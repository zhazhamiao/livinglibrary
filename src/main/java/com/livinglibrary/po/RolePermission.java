package com.livinglibrary.po;

public class RolePermission {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column role_permission.role_id
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	private Integer roleId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column role_permission.permission_id
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	private Integer permissionId;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column role_permission.role_id
	 * @return  the value of role_permission.role_id
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column role_permission.role_id
	 * @param roleId  the value for role_permission.role_id
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column role_permission.permission_id
	 * @return  the value of role_permission.permission_id
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	public Integer getPermissionId() {
		return permissionId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column role_permission.permission_id
	 * @param permissionId  the value for role_permission.permission_id
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
}