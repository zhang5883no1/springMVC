package cn.xidu.app.dto.active;

import java.util.Date;

public class APP_ACTIVE_Dto {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column app_info_active.ID
	 * @mbggenerated  Mon Sep 19 15:51:15 CST 2016
	 */
	private Long id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column app_info_active.TITLE
	 * @mbggenerated  Mon Sep 19 15:51:15 CST 2016
	 */
	private String title;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column app_info_active.CREATE_DATE
	 * @mbggenerated  Mon Sep 19 15:51:15 CST 2016
	 */
	private Integer status;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column app_info_active.ACTIVE_TIME
	 * @mbggenerated  Mon Sep 19 15:51:15 CST 2016
	 */
	private String activeTimeString;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column app_info_active.SORT_NO
	 * @mbggenerated  Mon Sep 19 15:51:15 CST 2016
	 */
	private Integer sortNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column app_info_active.TYPE
	 * @mbggenerated  Mon Sep 19 15:51:15 CST 2016
	 */
	private Integer type;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column app_info_active.PIC_URL
	 * @mbggenerated  Mon Sep 19 15:51:15 CST 2016
	 */
	private String picUrl;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column app_info_active.CONTENT
	 * @mbggenerated  Mon Sep 19 15:51:15 CST 2016
	 */
	private String content;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getActiveTimeString() {
		return activeTimeString;
	}
	public void setActiveTimeString(String activeTimeString) {
		this.activeTimeString = activeTimeString;
	}
	public Integer getSortNo() {
		return sortNo;
	}
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column app_info_active.ID
	 * @return  the value of app_info_active.ID
	 * @mbggenerated  Mon Sep 19 15:51:15 CST 2016
	 */
}