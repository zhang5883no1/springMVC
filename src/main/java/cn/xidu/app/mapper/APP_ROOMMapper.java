package cn.xidu.app.mapper;

import cn.xidu.app.entity.APP_ROOM;
import cn.xidu.app.entity.APP_ROOMExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface APP_ROOMMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_info_rooms
     *
     * @mbggenerated Wed Sep 14 10:50:09 CST 2016
     */
    int countByExample(APP_ROOMExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_info_rooms
     *
     * @mbggenerated Wed Sep 14 10:50:09 CST 2016
     */
    int deleteByExample(APP_ROOMExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_info_rooms
     *
     * @mbggenerated Wed Sep 14 10:50:09 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_info_rooms
     *
     * @mbggenerated Wed Sep 14 10:50:09 CST 2016
     */
    int insert(APP_ROOM record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_info_rooms
     *
     * @mbggenerated Wed Sep 14 10:50:09 CST 2016
     */
    int insertSelective(APP_ROOM record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_info_rooms
     *
     * @mbggenerated Wed Sep 14 10:50:09 CST 2016
     */
    List<APP_ROOM> selectByExample(APP_ROOMExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_info_rooms
     *
     * @mbggenerated Wed Sep 14 10:50:09 CST 2016
     */
    APP_ROOM selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_info_rooms
     *
     * @mbggenerated Wed Sep 14 10:50:09 CST 2016
     */
    int updateByExampleSelective(@Param("record") APP_ROOM record, @Param("example") APP_ROOMExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_info_rooms
     *
     * @mbggenerated Wed Sep 14 10:50:09 CST 2016
     */
    int updateByExample(@Param("record") APP_ROOM record, @Param("example") APP_ROOMExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_info_rooms
     *
     * @mbggenerated Wed Sep 14 10:50:09 CST 2016
     */
    int updateByPrimaryKeySelective(APP_ROOM record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_info_rooms
     *
     * @mbggenerated Wed Sep 14 10:50:09 CST 2016
     */
    int updateByPrimaryKey(APP_ROOM record);
}