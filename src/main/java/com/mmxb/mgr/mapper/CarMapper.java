package com.mmxb.mgr.mapper;

import com.mmxb.mgr.pojo.Car;
import com.mmxb.mgr.pojo.CarExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CarMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car
     *
     * @mbggenerated
     */
    int countByExample(CarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car
     *
     * @mbggenerated
     */
    int deleteByExample(CarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car
     *
     * @mbggenerated
     */
    int insert(Car record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car
     *
     * @mbggenerated
     */
    int insertSelective(Car record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car
     *
     * @mbggenerated
     */
    List<Car> selectByExample(CarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car
     *
     * @mbggenerated
     */
    Car selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Car record, @Param("example") CarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Car record, @Param("example") CarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Car record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Car record);
}