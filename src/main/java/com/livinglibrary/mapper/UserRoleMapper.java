package com.livinglibrary.mapper;

import com.livinglibrary.po.UserRole;
import com.livinglibrary.po.UserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.livinglibrary.po.UserRoleKey;

public interface UserRoleMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_role
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	long countByExample(UserRoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_role
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	int deleteByExample(UserRoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_role
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	int deleteByPrimaryKey(UserRoleKey key);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_role
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	int insert(UserRole record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_role
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	int insertSelective(UserRole record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_role
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	List<UserRole> selectByExample(UserRoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_role
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	UserRole selectByPrimaryKey(UserRoleKey key);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_role
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	int updateByExampleSelective(@Param("record") UserRole record, @Param("example") UserRoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_role
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	int updateByExample(@Param("record") UserRole record, @Param("example") UserRoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_role
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	int updateByPrimaryKeySelective(UserRole record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_role
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	int updateByPrimaryKey(UserRole record);
}