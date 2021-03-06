package com.livinglibrary.mapper;

import com.livinglibrary.po.ManageUserInfo;
import com.livinglibrary.po.ManageUserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageUserInfoMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table manage_user_info
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	long countByExample(ManageUserInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table manage_user_info
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	int deleteByExample(ManageUserInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table manage_user_info
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table manage_user_info
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	int insert(ManageUserInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table manage_user_info
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	int insertSelective(ManageUserInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table manage_user_info
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	List<ManageUserInfo> selectByExample(ManageUserInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table manage_user_info
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	ManageUserInfo selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table manage_user_info
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	int updateByExampleSelective(@Param("record") ManageUserInfo record,
			@Param("example") ManageUserInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table manage_user_info
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	int updateByExample(@Param("record") ManageUserInfo record, @Param("example") ManageUserInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table manage_user_info
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	int updateByPrimaryKeySelective(ManageUserInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table manage_user_info
	 * @mbg.generated  Sat Jan 12 10:57:32 CST 2019
	 */
	int updateByPrimaryKey(ManageUserInfo record);
}