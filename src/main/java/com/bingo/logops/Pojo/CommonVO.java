/**
 * 
 */
package com.bingo.logops.Pojo;

import java.util.Date;

/**
 * 通用 VO
 */
public class CommonVO extends PaginationVO {

	/** 应用名称 */
	private String appName;
	
	/** 访问的URL */
	private String url;
	
	/** 日期，用于单个日期查询 */
	private String date;
	
	/** 开始日期（字符串格式） */
	private String beginDate;
	
	/** 结束日期（字符串格式） */
	private String endDate;
	
	/** 开始日期（Date格式） */
	private Date dateBegin;

	/** 结束日期（Date格式） */
	private Date dateEnd;

	/**
	 * @return the appName
	 */
	public String getAppName() {
		return appName;
	}

	/**
	 * @param appName the appName to set
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the beginDate
	 */
	public String getBeginDate() {
		return beginDate;
	}

	/**
	 * @param beginDate the beginDate to set
	 */
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the dateBegin
	 */
	public Date getDateBegin() {
		return dateBegin;
	}

	/**
	 * @param dateBegin the dateBegin to set
	 */
	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}

	/**
	 * @return the dateEnd
	 */
	public Date getDateEnd() {
		return dateEnd;
	}

	/**
	 * @param dateEnd the dateEnd to set
	 */
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
}
