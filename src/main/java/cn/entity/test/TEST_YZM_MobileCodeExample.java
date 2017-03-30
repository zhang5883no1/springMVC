package cn.entity.test;

import java.util.ArrayList;
import java.util.List;

public class TEST_YZM_MobileCodeExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table xd_yzm_mobilecode
     *
     * @mbggenerated Mon Sep 05 15:58:15 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table xd_yzm_mobilecode
     *
     * @mbggenerated Mon Sep 05 15:58:15 CST 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table xd_yzm_mobilecode
     *
     * @mbggenerated Mon Sep 05 15:58:15 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_yzm_mobilecode
     *
     * @mbggenerated Mon Sep 05 15:58:15 CST 2016
     */
    public TEST_YZM_MobileCodeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_yzm_mobilecode
     *
     * @mbggenerated Mon Sep 05 15:58:15 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_yzm_mobilecode
     *
     * @mbggenerated Mon Sep 05 15:58:15 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_yzm_mobilecode
     *
     * @mbggenerated Mon Sep 05 15:58:15 CST 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_yzm_mobilecode
     *
     * @mbggenerated Mon Sep 05 15:58:15 CST 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_yzm_mobilecode
     *
     * @mbggenerated Mon Sep 05 15:58:15 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_yzm_mobilecode
     *
     * @mbggenerated Mon Sep 05 15:58:15 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_yzm_mobilecode
     *
     * @mbggenerated Mon Sep 05 15:58:15 CST 2016
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_yzm_mobilecode
     *
     * @mbggenerated Mon Sep 05 15:58:15 CST 2016
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_yzm_mobilecode
     *
     * @mbggenerated Mon Sep 05 15:58:15 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xd_yzm_mobilecode
     *
     * @mbggenerated Mon Sep 05 15:58:15 CST 2016
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table xd_yzm_mobilecode
     *
     * @mbggenerated Mon Sep 05 15:58:15 CST 2016
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andDateIsNull() {
            addCriterion("date is null");
            return (Criteria) this;
        }

        public Criteria andDateIsNotNull() {
            addCriterion("date is not null");
            return (Criteria) this;
        }

        public Criteria andDateEqualTo(String value) {
            addCriterion("date =", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotEqualTo(String value) {
            addCriterion("date <>", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThan(String value) {
            addCriterion("date >", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThanOrEqualTo(String value) {
            addCriterion("date >=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThan(String value) {
            addCriterion("date <", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThanOrEqualTo(String value) {
            addCriterion("date <=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLike(String value) {
            addCriterion("date like", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotLike(String value) {
            addCriterion("date not like", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateIn(List<String> values) {
            addCriterion("date in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotIn(List<String> values) {
            addCriterion("date not in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateBetween(String value1, String value2) {
            addCriterion("date between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotBetween(String value1, String value2) {
            addCriterion("date not between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andIsregIsNull() {
            addCriterion("isReg is null");
            return (Criteria) this;
        }

        public Criteria andIsregIsNotNull() {
            addCriterion("isReg is not null");
            return (Criteria) this;
        }

        public Criteria andIsregEqualTo(String value) {
            addCriterion("isReg =", value, "isreg");
            return (Criteria) this;
        }

        public Criteria andIsregNotEqualTo(String value) {
            addCriterion("isReg <>", value, "isreg");
            return (Criteria) this;
        }

        public Criteria andIsregGreaterThan(String value) {
            addCriterion("isReg >", value, "isreg");
            return (Criteria) this;
        }

        public Criteria andIsregGreaterThanOrEqualTo(String value) {
            addCriterion("isReg >=", value, "isreg");
            return (Criteria) this;
        }

        public Criteria andIsregLessThan(String value) {
            addCriterion("isReg <", value, "isreg");
            return (Criteria) this;
        }

        public Criteria andIsregLessThanOrEqualTo(String value) {
            addCriterion("isReg <=", value, "isreg");
            return (Criteria) this;
        }

        public Criteria andIsregLike(String value) {
            addCriterion("isReg like", value, "isreg");
            return (Criteria) this;
        }

        public Criteria andIsregNotLike(String value) {
            addCriterion("isReg not like", value, "isreg");
            return (Criteria) this;
        }

        public Criteria andIsregIn(List<String> values) {
            addCriterion("isReg in", values, "isreg");
            return (Criteria) this;
        }

        public Criteria andIsregNotIn(List<String> values) {
            addCriterion("isReg not in", values, "isreg");
            return (Criteria) this;
        }

        public Criteria andIsregBetween(String value1, String value2) {
            addCriterion("isReg between", value1, value2, "isreg");
            return (Criteria) this;
        }

        public Criteria andIsregNotBetween(String value1, String value2) {
            addCriterion("isReg not between", value1, value2, "isreg");
            return (Criteria) this;
        }

        public Criteria andSendindexIsNull() {
            addCriterion("sendIndex is null");
            return (Criteria) this;
        }

        public Criteria andSendindexIsNotNull() {
            addCriterion("sendIndex is not null");
            return (Criteria) this;
        }

        public Criteria andSendindexEqualTo(String value) {
            addCriterion("sendIndex =", value, "sendindex");
            return (Criteria) this;
        }

        public Criteria andSendindexNotEqualTo(String value) {
            addCriterion("sendIndex <>", value, "sendindex");
            return (Criteria) this;
        }

        public Criteria andSendindexGreaterThan(String value) {
            addCriterion("sendIndex >", value, "sendindex");
            return (Criteria) this;
        }

        public Criteria andSendindexGreaterThanOrEqualTo(String value) {
            addCriterion("sendIndex >=", value, "sendindex");
            return (Criteria) this;
        }

        public Criteria andSendindexLessThan(String value) {
            addCriterion("sendIndex <", value, "sendindex");
            return (Criteria) this;
        }

        public Criteria andSendindexLessThanOrEqualTo(String value) {
            addCriterion("sendIndex <=", value, "sendindex");
            return (Criteria) this;
        }

        public Criteria andSendindexLike(String value) {
            addCriterion("sendIndex like", value, "sendindex");
            return (Criteria) this;
        }

        public Criteria andSendindexNotLike(String value) {
            addCriterion("sendIndex not like", value, "sendindex");
            return (Criteria) this;
        }

        public Criteria andSendindexIn(List<String> values) {
            addCriterion("sendIndex in", values, "sendindex");
            return (Criteria) this;
        }

        public Criteria andSendindexNotIn(List<String> values) {
            addCriterion("sendIndex not in", values, "sendindex");
            return (Criteria) this;
        }

        public Criteria andSendindexBetween(String value1, String value2) {
            addCriterion("sendIndex between", value1, value2, "sendindex");
            return (Criteria) this;
        }

        public Criteria andSendindexNotBetween(String value1, String value2) {
            addCriterion("sendIndex not between", value1, value2, "sendindex");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table xd_yzm_mobilecode
     *
     * @mbggenerated do_not_delete_during_merge Mon Sep 05 15:58:15 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table xd_yzm_mobilecode
     *
     * @mbggenerated Mon Sep 05 15:58:15 CST 2016
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}