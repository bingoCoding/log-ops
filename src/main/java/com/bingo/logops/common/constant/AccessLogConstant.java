/**
 * 
 */
package com.bingo.logops.common.constant;

/**
 * 访问日志常量
 */
public class AccessLogConstant {

	/** 集合名称 */
	public static final String COLLECTION_NAME = "access_log";
	
	/** 对应DB中的字段 */
	public static final String ID = "_id";
	public static final String APP_NAME = "a";
	public static final String UID = "b";
	public static final String LOCAL_IP = "c";
	public static final String LOCAL_PORT = "d";
	public static final String ACCESS_TIME = "e";
	public static final String ACCESS_DATE = "f";
	public static final String PROCESS_TIME = "g";
	public static final String VER = "h";
	public static final String SYS = "i";
	public static final String URL = "j";
	public static final String QUERY = "k";
	public static final String POST = "l";
	public static final String PROTOCOL = "m";
	public static final String STATUS = "n";
	public static final String METHOD = "o";
	
	/** 客户端IP，一般只有 REOMOTE_IP 有值 */
	public static final String REAL_IP = "p";
	public static final String REOMOTE_IP = "q";
	public static final String FORWARDED_FOR = "r";
	
	/** 请求来源 */
	public static final String REFERER = "s";
	
	/** 客户端信息 */
	public static final String AUTO_AGENT = "t";
}
