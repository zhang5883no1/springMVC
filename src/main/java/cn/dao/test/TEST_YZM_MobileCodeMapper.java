package cn.dao.test;

import cn.entity.test.TEST_YZM_MobileCode;
import cn.entity.test.TEST_YZM_MobileCodeExample;
import cn.entity.test.TEST_YZM_MobileCodeWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TEST_YZM_MobileCodeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_yzm_mobilecode
     *
     * @mbggenerated Mon Sep 05 15:58:15 CST 2016
     */
    int countByExample(TEST_YZM_MobileCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_yzm_mobilecode
     *
     * @mbggenerated Mon Sep 05 15:58:15 CST 2016
     */
    int deleteByExample(TEST_YZM_MobileCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_yzm_mobilecode
     *
     * @mbggenerated Mon Sep 05 15:58:15 CST 2016
     */
    int insert(TEST_YZM_MobileCodeWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_yzm_mobilecode
     *
     * @mbggenerated Mon Sep 05 15:58:15 CST 2016
     */
    int insertSelective(TEST_YZM_MobileCodeWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_yzm_mobilecode
     *
     * @mbggenerated Mon Sep 05 15:58:15 CST 2016
     */
    List<TEST_YZM_MobileCodeWithBLOBs> selectByExampleWithBLOBs(TEST_YZM_MobileCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_yzm_mobilecode
     *
     * @mbggenerated Mon Sep 05 15:58:15 CST 2016
     */
    List<TEST_YZM_MobileCode> selectByExample(TEST_YZM_MobileCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_yzm_mobilecode
     *
     * @mbggenerated Mon Sep 05 15:58:15 CST 2016
     */
    int updateByExampleSelective(@Param("record") TEST_YZM_MobileCodeWithBLOBs record, @Param("example") TEST_YZM_MobileCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_yzm_mobilecode
     *
     * @mbggenerated Mon Sep 05 15:58:15 CST 2016
     */
    int updateByExampleWithBLOBs(@Param("record") TEST_YZM_MobileCodeWithBLOBs record, @Param("example") TEST_YZM_MobileCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_yzm_mobilecode
     *
     * @mbggenerated Mon Sep 05 15:58:15 CST 2016
     */
    int updateByExample(@Param("record") TEST_YZM_MobileCode record, @Param("example") TEST_YZM_MobileCodeExample example);
}