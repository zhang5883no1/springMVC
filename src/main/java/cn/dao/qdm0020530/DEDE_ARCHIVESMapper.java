package cn.dao.qdm0020530;

import cn.entity.qdm0020530.DEDE_ARCHIVES;
import cn.entity.qdm0020530.DEDE_ARCHIVESExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DEDE_ARCHIVESMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dede_archives
     *
     * @mbggenerated Wed Sep 07 14:18:52 CST 2016
     */
    int countByExample(DEDE_ARCHIVESExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dede_archives
     *
     * @mbggenerated Wed Sep 07 14:18:52 CST 2016
     */
    int deleteByExample(DEDE_ARCHIVESExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dede_archives
     *
     * @mbggenerated Wed Sep 07 14:18:52 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dede_archives
     *
     * @mbggenerated Wed Sep 07 14:18:52 CST 2016
     */
    int insert(DEDE_ARCHIVES record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dede_archives
     *
     * @mbggenerated Wed Sep 07 14:18:52 CST 2016
     */
    int insertSelective(DEDE_ARCHIVES record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dede_archives
     *
     * @mbggenerated Wed Sep 07 14:18:52 CST 2016
     */
    List<DEDE_ARCHIVES> selectByExample(DEDE_ARCHIVESExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dede_archives
     *
     * @mbggenerated Wed Sep 07 14:18:52 CST 2016
     */
    DEDE_ARCHIVES selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dede_archives
     *
     * @mbggenerated Wed Sep 07 14:18:52 CST 2016
     */
    int updateByExampleSelective(@Param("record") DEDE_ARCHIVES record, @Param("example") DEDE_ARCHIVESExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dede_archives
     *
     * @mbggenerated Wed Sep 07 14:18:52 CST 2016
     */
    int updateByExample(@Param("record") DEDE_ARCHIVES record, @Param("example") DEDE_ARCHIVESExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dede_archives
     *
     * @mbggenerated Wed Sep 07 14:18:52 CST 2016
     */
    int updateByPrimaryKeySelective(DEDE_ARCHIVES record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dede_archives
     *
     * @mbggenerated Wed Sep 07 14:18:52 CST 2016
     */
    int updateByPrimaryKey(DEDE_ARCHIVES record);
}