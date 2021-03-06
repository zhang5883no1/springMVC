package cn.dao.xinxidu;

import cn.entity.xinxidu.Freecms_Info;
import cn.entity.xinxidu.Freecms_InfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Freecms_InfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table freecms_info
     *
     * @mbggenerated Wed Sep 07 13:32:49 CST 2016
     */
    int countByExample(Freecms_InfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table freecms_info
     *
     * @mbggenerated Wed Sep 07 13:32:49 CST 2016
     */
    int deleteByExample(Freecms_InfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table freecms_info
     *
     * @mbggenerated Wed Sep 07 13:32:49 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table freecms_info
     *
     * @mbggenerated Wed Sep 07 13:32:49 CST 2016
     */
    int insert(Freecms_Info record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table freecms_info
     *
     * @mbggenerated Wed Sep 07 13:32:49 CST 2016
     */
    int insertSelective(Freecms_Info record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table freecms_info
     *
     * @mbggenerated Wed Sep 07 13:32:49 CST 2016
     */
    List<Freecms_Info> selectByExampleWithBLOBs(Freecms_InfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table freecms_info
     *
     * @mbggenerated Wed Sep 07 13:32:49 CST 2016
     */
    List<Freecms_Info> selectByExample(Freecms_InfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table freecms_info
     *
     * @mbggenerated Wed Sep 07 13:32:49 CST 2016
     */
    Freecms_Info selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table freecms_info
     *
     * @mbggenerated Wed Sep 07 13:32:49 CST 2016
     */
    int updateByExampleSelective(@Param("record") Freecms_Info record, @Param("example") Freecms_InfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table freecms_info
     *
     * @mbggenerated Wed Sep 07 13:32:49 CST 2016
     */
    int updateByExampleWithBLOBs(@Param("record") Freecms_Info record, @Param("example") Freecms_InfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table freecms_info
     *
     * @mbggenerated Wed Sep 07 13:32:49 CST 2016
     */
    int updateByExample(@Param("record") Freecms_Info record, @Param("example") Freecms_InfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table freecms_info
     *
     * @mbggenerated Wed Sep 07 13:32:49 CST 2016
     */
    int updateByPrimaryKeySelective(Freecms_Info record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table freecms_info
     *
     * @mbggenerated Wed Sep 07 13:32:49 CST 2016
     */
    int updateByPrimaryKeyWithBLOBs(Freecms_Info record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table freecms_info
     *
     * @mbggenerated Wed Sep 07 13:32:49 CST 2016
     */
    int updateByPrimaryKey(Freecms_Info record);
}