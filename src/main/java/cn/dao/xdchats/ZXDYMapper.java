package cn.dao.xdchats;

import cn.entity.xdchats.ZXDY;
import cn.entity.xdchats.ZXDYExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZXDYMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_zxdy
     *
     * @mbggenerated Mon Sep 12 14:54:57 CST 2016
     */
    int countByExample(ZXDYExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_zxdy
     *
     * @mbggenerated Mon Sep 12 14:54:57 CST 2016
     */
    int deleteByExample(ZXDYExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_zxdy
     *
     * @mbggenerated Mon Sep 12 14:54:57 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_zxdy
     *
     * @mbggenerated Mon Sep 12 14:54:57 CST 2016
     */
    int insert(ZXDY record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_zxdy
     *
     * @mbggenerated Mon Sep 12 14:54:57 CST 2016
     */
    int insertSelective(ZXDY record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_zxdy
     *
     * @mbggenerated Mon Sep 12 14:54:57 CST 2016
     */
    List<ZXDY> selectByExample(ZXDYExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_zxdy
     *
     * @mbggenerated Mon Sep 12 14:54:57 CST 2016
     */
    ZXDY selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_zxdy
     *
     * @mbggenerated Mon Sep 12 14:54:57 CST 2016
     */
    int updateByExampleSelective(@Param("record") ZXDY record, @Param("example") ZXDYExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_zxdy
     *
     * @mbggenerated Mon Sep 12 14:54:57 CST 2016
     */
    int updateByExample(@Param("record") ZXDY record, @Param("example") ZXDYExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_zxdy
     *
     * @mbggenerated Mon Sep 12 14:54:57 CST 2016
     */
    int updateByPrimaryKeySelective(ZXDY record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_zxdy
     *
     * @mbggenerated Mon Sep 12 14:54:57 CST 2016
     */
    int updateByPrimaryKey(ZXDY record);
}