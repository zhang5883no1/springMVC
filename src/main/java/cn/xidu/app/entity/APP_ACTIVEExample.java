package cn.xidu.app.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class APP_ACTIVEExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table app_info_active
	 * @mbggenerated  Mon Sep 19 15:51:15 CST 2016
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table app_info_active
	 * @mbggenerated  Mon Sep 19 15:51:15 CST 2016
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table app_info_active
	 * @mbggenerated  Mon Sep 19 15:51:15 CST 2016
	 */
	protected List<Criteria> oredCriteria;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table app_info_active
	 * @mbggenerated  Mon Sep 19 15:51:15 CST 2016
	 */
	protected Integer limitStart;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table app_info_active
	 * @mbggenerated  Mon Sep 19 15:51:15 CST 2016
	 */
	protected Integer limitEnd;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_info_active
	 * @mbggenerated  Mon Sep 19 15:51:15 CST 2016
	 */
	public APP_ACTIVEExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_info_active
	 * @mbggenerated  Mon Sep 19 15:51:15 CST 2016
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_info_active
	 * @mbggenerated  Mon Sep 19 15:51:15 CST 2016
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_info_active
	 * @mbggenerated  Mon Sep 19 15:51:15 CST 2016
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_info_active
	 * @mbggenerated  Mon Sep 19 15:51:15 CST 2016
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_info_active
	 * @mbggenerated  Mon Sep 19 15:51:15 CST 2016
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_info_active
	 * @mbggenerated  Mon Sep 19 15:51:15 CST 2016
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_info_active
	 * @mbggenerated  Mon Sep 19 15:51:15 CST 2016
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_info_active
	 * @mbggenerated  Mon Sep 19 15:51:15 CST 2016
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_info_active
	 * @mbggenerated  Mon Sep 19 15:51:15 CST 2016
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_info_active
	 * @mbggenerated  Mon Sep 19 15:51:15 CST 2016
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_info_active
	 * @mbggenerated  Mon Sep 19 15:51:15 CST 2016
	 */
	public void setLimitStart(Integer limitStart) {
		this.limitStart = limitStart;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_info_active
	 * @mbggenerated  Mon Sep 19 15:51:15 CST 2016
	 */
	public Integer getLimitStart() {
		return limitStart;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_info_active
	 * @mbggenerated  Mon Sep 19 15:51:15 CST 2016
	 */
	public void setLimitEnd(Integer limitEnd) {
		this.limitEnd = limitEnd;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_info_active
	 * @mbggenerated  Mon Sep 19 15:51:15 CST 2016
	 */
	public Integer getLimitEnd() {
		return limitEnd;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table app_info_active
	 * @mbggenerated  Mon Sep 19 15:51:15 CST 2016
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

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andIdIsNull() {
			addCriterion("ID is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("ID is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Long value) {
			addCriterion("ID =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Long value) {
			addCriterion("ID <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Long value) {
			addCriterion("ID >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Long value) {
			addCriterion("ID >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Long value) {
			addCriterion("ID <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Long value) {
			addCriterion("ID <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Long> values) {
			addCriterion("ID in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Long> values) {
			addCriterion("ID not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Long value1, Long value2) {
			addCriterion("ID between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Long value1, Long value2) {
			addCriterion("ID not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andTitleIsNull() {
			addCriterion("TITLE is null");
			return (Criteria) this;
		}

		public Criteria andTitleIsNotNull() {
			addCriterion("TITLE is not null");
			return (Criteria) this;
		}

		public Criteria andTitleEqualTo(String value) {
			addCriterion("TITLE =", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotEqualTo(String value) {
			addCriterion("TITLE <>", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleGreaterThan(String value) {
			addCriterion("TITLE >", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleGreaterThanOrEqualTo(String value) {
			addCriterion("TITLE >=", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLessThan(String value) {
			addCriterion("TITLE <", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLessThanOrEqualTo(String value) {
			addCriterion("TITLE <=", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLike(String value) {
			addCriterion("TITLE like", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotLike(String value) {
			addCriterion("TITLE not like", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleIn(List<String> values) {
			addCriterion("TITLE in", values, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotIn(List<String> values) {
			addCriterion("TITLE not in", values, "title");
			return (Criteria) this;
		}

		public Criteria andTitleBetween(String value1, String value2) {
			addCriterion("TITLE between", value1, value2, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotBetween(String value1, String value2) {
			addCriterion("TITLE not between", value1, value2, "title");
			return (Criteria) this;
		}

		public Criteria andCreateDateIsNull() {
			addCriterion("CREATE_DATE is null");
			return (Criteria) this;
		}

		public Criteria andCreateDateIsNotNull() {
			addCriterion("CREATE_DATE is not null");
			return (Criteria) this;
		}

		public Criteria andCreateDateEqualTo(Date value) {
			addCriterion("CREATE_DATE =", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateNotEqualTo(Date value) {
			addCriterion("CREATE_DATE <>", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateGreaterThan(Date value) {
			addCriterion("CREATE_DATE >", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
			addCriterion("CREATE_DATE >=", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateLessThan(Date value) {
			addCriterion("CREATE_DATE <", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateLessThanOrEqualTo(Date value) {
			addCriterion("CREATE_DATE <=", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateIn(List<Date> values) {
			addCriterion("CREATE_DATE in", values, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateNotIn(List<Date> values) {
			addCriterion("CREATE_DATE not in", values, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateBetween(Date value1, Date value2) {
			addCriterion("CREATE_DATE between", value1, value2, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateNotBetween(Date value1, Date value2) {
			addCriterion("CREATE_DATE not between", value1, value2,
					"createDate");
			return (Criteria) this;
		}

		public Criteria andStatusIsNull() {
			addCriterion("STATUS is null");
			return (Criteria) this;
		}

		public Criteria andStatusIsNotNull() {
			addCriterion("STATUS is not null");
			return (Criteria) this;
		}

		public Criteria andStatusEqualTo(Integer value) {
			addCriterion("STATUS =", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotEqualTo(Integer value) {
			addCriterion("STATUS <>", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThan(Integer value) {
			addCriterion("STATUS >", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
			addCriterion("STATUS >=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThan(Integer value) {
			addCriterion("STATUS <", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThanOrEqualTo(Integer value) {
			addCriterion("STATUS <=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusIn(List<Integer> values) {
			addCriterion("STATUS in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotIn(List<Integer> values) {
			addCriterion("STATUS not in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusBetween(Integer value1, Integer value2) {
			addCriterion("STATUS between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotBetween(Integer value1, Integer value2) {
			addCriterion("STATUS not between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andActiveTimeIsNull() {
			addCriterion("ACTIVE_TIME is null");
			return (Criteria) this;
		}

		public Criteria andActiveTimeIsNotNull() {
			addCriterion("ACTIVE_TIME is not null");
			return (Criteria) this;
		}

		public Criteria andActiveTimeEqualTo(Date value) {
			addCriterion("ACTIVE_TIME =", value, "activeTime");
			return (Criteria) this;
		}

		public Criteria andActiveTimeNotEqualTo(Date value) {
			addCriterion("ACTIVE_TIME <>", value, "activeTime");
			return (Criteria) this;
		}

		public Criteria andActiveTimeGreaterThan(Date value) {
			addCriterion("ACTIVE_TIME >", value, "activeTime");
			return (Criteria) this;
		}

		public Criteria andActiveTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("ACTIVE_TIME >=", value, "activeTime");
			return (Criteria) this;
		}

		public Criteria andActiveTimeLessThan(Date value) {
			addCriterion("ACTIVE_TIME <", value, "activeTime");
			return (Criteria) this;
		}

		public Criteria andActiveTimeLessThanOrEqualTo(Date value) {
			addCriterion("ACTIVE_TIME <=", value, "activeTime");
			return (Criteria) this;
		}

		public Criteria andActiveTimeIn(List<Date> values) {
			addCriterion("ACTIVE_TIME in", values, "activeTime");
			return (Criteria) this;
		}

		public Criteria andActiveTimeNotIn(List<Date> values) {
			addCriterion("ACTIVE_TIME not in", values, "activeTime");
			return (Criteria) this;
		}

		public Criteria andActiveTimeBetween(Date value1, Date value2) {
			addCriterion("ACTIVE_TIME between", value1, value2, "activeTime");
			return (Criteria) this;
		}

		public Criteria andActiveTimeNotBetween(Date value1, Date value2) {
			addCriterion("ACTIVE_TIME not between", value1, value2,
					"activeTime");
			return (Criteria) this;
		}

		public Criteria andSortNoIsNull() {
			addCriterion("SORT_NO is null");
			return (Criteria) this;
		}

		public Criteria andSortNoIsNotNull() {
			addCriterion("SORT_NO is not null");
			return (Criteria) this;
		}

		public Criteria andSortNoEqualTo(Integer value) {
			addCriterion("SORT_NO =", value, "sortNo");
			return (Criteria) this;
		}

		public Criteria andSortNoNotEqualTo(Integer value) {
			addCriterion("SORT_NO <>", value, "sortNo");
			return (Criteria) this;
		}

		public Criteria andSortNoGreaterThan(Integer value) {
			addCriterion("SORT_NO >", value, "sortNo");
			return (Criteria) this;
		}

		public Criteria andSortNoGreaterThanOrEqualTo(Integer value) {
			addCriterion("SORT_NO >=", value, "sortNo");
			return (Criteria) this;
		}

		public Criteria andSortNoLessThan(Integer value) {
			addCriterion("SORT_NO <", value, "sortNo");
			return (Criteria) this;
		}

		public Criteria andSortNoLessThanOrEqualTo(Integer value) {
			addCriterion("SORT_NO <=", value, "sortNo");
			return (Criteria) this;
		}

		public Criteria andSortNoIn(List<Integer> values) {
			addCriterion("SORT_NO in", values, "sortNo");
			return (Criteria) this;
		}

		public Criteria andSortNoNotIn(List<Integer> values) {
			addCriterion("SORT_NO not in", values, "sortNo");
			return (Criteria) this;
		}

		public Criteria andSortNoBetween(Integer value1, Integer value2) {
			addCriterion("SORT_NO between", value1, value2, "sortNo");
			return (Criteria) this;
		}

		public Criteria andSortNoNotBetween(Integer value1, Integer value2) {
			addCriterion("SORT_NO not between", value1, value2, "sortNo");
			return (Criteria) this;
		}

		public Criteria andTypeIsNull() {
			addCriterion("TYPE is null");
			return (Criteria) this;
		}

		public Criteria andTypeIsNotNull() {
			addCriterion("TYPE is not null");
			return (Criteria) this;
		}

		public Criteria andTypeEqualTo(Integer value) {
			addCriterion("TYPE =", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotEqualTo(Integer value) {
			addCriterion("TYPE <>", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThan(Integer value) {
			addCriterion("TYPE >", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
			addCriterion("TYPE >=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThan(Integer value) {
			addCriterion("TYPE <", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThanOrEqualTo(Integer value) {
			addCriterion("TYPE <=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeIn(List<Integer> values) {
			addCriterion("TYPE in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotIn(List<Integer> values) {
			addCriterion("TYPE not in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeBetween(Integer value1, Integer value2) {
			addCriterion("TYPE between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotBetween(Integer value1, Integer value2) {
			addCriterion("TYPE not between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andDeleteFlagIsNull() {
			addCriterion("DELETE_FLAG is null");
			return (Criteria) this;
		}

		public Criteria andDeleteFlagIsNotNull() {
			addCriterion("DELETE_FLAG is not null");
			return (Criteria) this;
		}

		public Criteria andDeleteFlagEqualTo(Integer value) {
			addCriterion("DELETE_FLAG =", value, "deleteFlag");
			return (Criteria) this;
		}

		public Criteria andDeleteFlagNotEqualTo(Integer value) {
			addCriterion("DELETE_FLAG <>", value, "deleteFlag");
			return (Criteria) this;
		}

		public Criteria andDeleteFlagGreaterThan(Integer value) {
			addCriterion("DELETE_FLAG >", value, "deleteFlag");
			return (Criteria) this;
		}

		public Criteria andDeleteFlagGreaterThanOrEqualTo(Integer value) {
			addCriterion("DELETE_FLAG >=", value, "deleteFlag");
			return (Criteria) this;
		}

		public Criteria andDeleteFlagLessThan(Integer value) {
			addCriterion("DELETE_FLAG <", value, "deleteFlag");
			return (Criteria) this;
		}

		public Criteria andDeleteFlagLessThanOrEqualTo(Integer value) {
			addCriterion("DELETE_FLAG <=", value, "deleteFlag");
			return (Criteria) this;
		}

		public Criteria andDeleteFlagIn(List<Integer> values) {
			addCriterion("DELETE_FLAG in", values, "deleteFlag");
			return (Criteria) this;
		}

		public Criteria andDeleteFlagNotIn(List<Integer> values) {
			addCriterion("DELETE_FLAG not in", values, "deleteFlag");
			return (Criteria) this;
		}

		public Criteria andDeleteFlagBetween(Integer value1, Integer value2) {
			addCriterion("DELETE_FLAG between", value1, value2, "deleteFlag");
			return (Criteria) this;
		}

		public Criteria andDeleteFlagNotBetween(Integer value1, Integer value2) {
			addCriterion("DELETE_FLAG not between", value1, value2,
					"deleteFlag");
			return (Criteria) this;
		}

		public Criteria andPicUrlIsNull() {
			addCriterion("PIC_URL is null");
			return (Criteria) this;
		}

		public Criteria andPicUrlIsNotNull() {
			addCriterion("PIC_URL is not null");
			return (Criteria) this;
		}

		public Criteria andPicUrlEqualTo(String value) {
			addCriterion("PIC_URL =", value, "picUrl");
			return (Criteria) this;
		}

		public Criteria andPicUrlNotEqualTo(String value) {
			addCriterion("PIC_URL <>", value, "picUrl");
			return (Criteria) this;
		}

		public Criteria andPicUrlGreaterThan(String value) {
			addCriterion("PIC_URL >", value, "picUrl");
			return (Criteria) this;
		}

		public Criteria andPicUrlGreaterThanOrEqualTo(String value) {
			addCriterion("PIC_URL >=", value, "picUrl");
			return (Criteria) this;
		}

		public Criteria andPicUrlLessThan(String value) {
			addCriterion("PIC_URL <", value, "picUrl");
			return (Criteria) this;
		}

		public Criteria andPicUrlLessThanOrEqualTo(String value) {
			addCriterion("PIC_URL <=", value, "picUrl");
			return (Criteria) this;
		}

		public Criteria andPicUrlLike(String value) {
			addCriterion("PIC_URL like", value, "picUrl");
			return (Criteria) this;
		}

		public Criteria andPicUrlNotLike(String value) {
			addCriterion("PIC_URL not like", value, "picUrl");
			return (Criteria) this;
		}

		public Criteria andPicUrlIn(List<String> values) {
			addCriterion("PIC_URL in", values, "picUrl");
			return (Criteria) this;
		}

		public Criteria andPicUrlNotIn(List<String> values) {
			addCriterion("PIC_URL not in", values, "picUrl");
			return (Criteria) this;
		}

		public Criteria andPicUrlBetween(String value1, String value2) {
			addCriterion("PIC_URL between", value1, value2, "picUrl");
			return (Criteria) this;
		}

		public Criteria andPicUrlNotBetween(String value1, String value2) {
			addCriterion("PIC_URL not between", value1, value2, "picUrl");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table app_info_active
	 * @mbggenerated  Mon Sep 19 15:51:15 CST 2016
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

		protected Criterion(String condition, Object value, Object secondValue,
				String typeHandler) {
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

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table app_info_active
     *
     * @mbggenerated do_not_delete_during_merge Wed Sep 14 10:50:09 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}