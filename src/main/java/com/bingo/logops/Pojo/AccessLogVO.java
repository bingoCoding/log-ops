/**
 * 
 */
package com.bingo.logops.Pojo;

/**
 * 访问日志 VO
 */
public class AccessLogVO extends CommonVO {

	/** 用户ID */
	private Integer uid;
	
	/** 客户端IP */
	private String ip;
	
	/** URL参数 */
	private String param;

	/**
	 * @return the uid
	 */
	public Integer getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the param
	 */
	public String getParam() {
		return param;
	}

	/**
	 * @param param the param to set
	 */
	public void setParam(String param) {
		this.param = param;
	}
}
