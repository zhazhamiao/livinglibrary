package com.livinglibrary.po;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Forumuser {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column forumuser.id
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column forumuser.stuid
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	private String stuid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column forumuser.nickname
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	private String nickname;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column forumuser.level
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	private Byte level;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column forumuser.registerdate
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	@JSONField(format = "yyyy-MM-dd")
	private Date registerdate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column forumuser.status
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	private Byte status;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column forumuser.integral
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	private Integer integral;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column forumuser.remarks
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	private String remarks;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column forumuser.id
	 * @return  the value of forumuser.id
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column forumuser.id
	 * @param id  the value for forumuser.id
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column forumuser.stuid
	 * @return  the value of forumuser.stuid
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	public String getStuid() {
		return stuid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column forumuser.stuid
	 * @param stuid  the value for forumuser.stuid
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	public void setStuid(String stuid) {
		this.stuid = stuid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column forumuser.nickname
	 * @return  the value of forumuser.nickname
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column forumuser.nickname
	 * @param nickname  the value for forumuser.nickname
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column forumuser.level
	 * @return  the value of forumuser.level
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	public Byte getLevel() {
		return level;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column forumuser.level
	 * @param level  the value for forumuser.level
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	public void setLevel(Byte level) {
		this.level = level;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column forumuser.registerdate
	 * @return  the value of forumuser.registerdate
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	public Date getRegisterdate() {
		return registerdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column forumuser.registerdate
	 * @param registerdate  the value for forumuser.registerdate
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	public void setRegisterdate(Date registerdate) {
		this.registerdate = registerdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column forumuser.status
	 * @return  the value of forumuser.status
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	public Byte getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column forumuser.status
	 * @param status  the value for forumuser.status
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	public void setStatus(Byte status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column forumuser.integral
	 * @return  the value of forumuser.integral
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	public Integer getIntegral() {
		return integral;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column forumuser.integral
	 * @param integral  the value for forumuser.integral
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column forumuser.remarks
	 * @return  the value of forumuser.remarks
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column forumuser.remarks
	 * @param remarks  the value for forumuser.remarks
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}