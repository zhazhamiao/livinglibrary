package com.livinglibrary.po;

import java.util.Date;

public class ManageUserInfo {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column manage_user_info.id
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column manage_user_info.manage_name
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	private String manageName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column manage_user_info.password
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	private String password;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column manage_user_info.last_time
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	private Date lastTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column manage_user_info.id
	 * @return  the value of manage_user_info.id
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column manage_user_info.id
	 * @param id  the value for manage_user_info.id
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column manage_user_info.manage_name
	 * @return  the value of manage_user_info.manage_name
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	public String getManageName() {
		return manageName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column manage_user_info.manage_name
	 * @param manageName  the value for manage_user_info.manage_name
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	public void setManageName(String manageName) {
		this.manageName = manageName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column manage_user_info.password
	 * @return  the value of manage_user_info.password
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column manage_user_info.password
	 * @param password  the value for manage_user_info.password
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column manage_user_info.last_time
	 * @return  the value of manage_user_info.last_time
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	public Date getLastTime() {
		return lastTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column manage_user_info.last_time
	 * @param lastTime  the value for manage_user_info.last_time
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
}