package com.livinglibrary.mapper;

import com.livinglibrary.po.Carousel;
import com.livinglibrary.po.CarouselExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CarouselMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table carousel
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	long countByExample(CarouselExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table carousel
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	int deleteByExample(CarouselExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table carousel
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table carousel
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	int insert(Carousel record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table carousel
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	int insertSelective(Carousel record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table carousel
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	List<Carousel> selectByExample(CarouselExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table carousel
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	Carousel selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table carousel
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	int updateByExampleSelective(@Param("record") Carousel record, @Param("example") CarouselExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table carousel
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	int updateByExample(@Param("record") Carousel record, @Param("example") CarouselExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table carousel
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	int updateByPrimaryKeySelective(Carousel record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table carousel
	 * @mbg.generated  Thu May 16 08:51:21 CST 2019
	 */
	int updateByPrimaryKey(Carousel record);
}