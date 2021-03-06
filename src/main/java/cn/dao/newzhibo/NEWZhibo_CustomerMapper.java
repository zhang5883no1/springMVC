package cn.dao.newzhibo;

import cn.entity.newzhibo.NEWZhibo_Customer;
import cn.entity.newzhibo.NEWZhibo_CustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NEWZhibo_CustomerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_customer
     *
     * @mbggenerated Mon Sep 26 15:03:57 CST 2016
     */
    int countByExample(NEWZhibo_CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_customer
     *
     * @mbggenerated Mon Sep 26 15:03:57 CST 2016
     */
    int deleteByExample(NEWZhibo_CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_customer
     *
     * @mbggenerated Mon Sep 26 15:03:57 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_customer
     *
     * @mbggenerated Mon Sep 26 15:03:57 CST 2016
     */
    int insert(NEWZhibo_Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_customer
     *
     * @mbggenerated Mon Sep 26 15:03:57 CST 2016
     */
    int insertSelective(NEWZhibo_Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_customer
     *
     * @mbggenerated Mon Sep 26 15:03:57 CST 2016
     */
    List<NEWZhibo_Customer> selectByExample(NEWZhibo_CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_customer
     *
     * @mbggenerated Mon Sep 26 15:03:57 CST 2016
     */
    NEWZhibo_Customer selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_customer
     *
     * @mbggenerated Mon Sep 26 15:03:57 CST 2016
     */
    int updateByExampleSelective(@Param("record") NEWZhibo_Customer record, @Param("example") NEWZhibo_CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_customer
     *
     * @mbggenerated Mon Sep 26 15:03:57 CST 2016
     */
    int updateByExample(@Param("record") NEWZhibo_Customer record, @Param("example") NEWZhibo_CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_customer
     *
     * @mbggenerated Mon Sep 26 15:03:57 CST 2016
     */
    int updateByPrimaryKeySelective(NEWZhibo_Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_customer
     *
     * @mbggenerated Mon Sep 26 15:03:57 CST 2016
     */
    int updateByPrimaryKey(NEWZhibo_Customer record);
}