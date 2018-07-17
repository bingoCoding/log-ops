package com.bingo.logops.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 访问日志Model
 */
@Document(collection = "access_log")
@CompoundIndex(name = "accessTime_appName_index", def = "{'f' : -1, 'a' : 1}")
public class AccessLogModel {

    @Id
    @Field("_id")
    private String id;

    /** 应用名称（服务器名称） */
    @Field("a")
    private String appName;

    /** 用户id */
    @Field("b")
    private int uid;

    /** 本地IP地址 */
    @Field("c")
    private String localIp;

    /** 本地IP端口 */
    @Field("d")
    private String localPort;

    /** 访问时间 */
    @Field("e")
    private Date accessTime;

    /** 访问日期，便于查询 */
    @Field("f")
    private Date accessDate;

    /** 处理时间 */
    @Field("g")
    private Double processTime;

    /** APP版本 */
    @Field("h")
    private String ver;

    /** APP版本 */
    @Field("i")
    private String sys;

    /** 访问地址 */
    @Field("j")
    private String url;

    /** url 参数 */
    @Field("k")
    @Indexed(name = "query")
    private String query;

    /** post 参数 */
    @Field("l")
    private String post;

    /** 协议 */
    @Field("m")
    private String protocol;

    /** 状态 */
    @Field("n")
    private String status;

    /** 访问类型 */
    @Field("o")
    private String method;

    /** 真实IP */
    @Field("p")
    private String realIp;

    /** 远端IP，直接 */
    @Field("q")
    private String reomoteIp;

    /** 远端IP，转发 */
    @Field("r")
    private String forwardedFor;

    /** 来源 */
    @Field("s")
    private String referer;

    /** 客户信息 */
    @Field("t")
    private String autoAgent;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

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
     * @return the uid
     */
    public int getUid() {
        return uid;
    }

    /**
     * @param uid the uid to set
     */
    public void setUid(int uid) {
        this.uid = uid;
    }

    /**
     * @return the localIp
     */
    public String getLocalIp() {
        return localIp;
    }

    /**
     * @param localIp the localIp to set
     */
    public void setLocalIp(String localIp) {
        this.localIp = localIp;
    }

    /**
     * @return the localPort
     */
    public String getLocalPort() {
        return localPort;
    }

    /**
     * @param localPort the localPort to set
     */
    public void setLocalPort(String localPort) {
        this.localPort = localPort;
    }

    /**
     * @return the accessTime
     */
    public Date getAccessTime() {
        return accessTime;
    }

    /**
     * @param accessTime the accessTime to set
     */
    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    /**
     * @return the accessDate
     */
    public Date getAccessDate() {
        return accessDate;
    }

    /**
     * @param accessDate the accessDate to set
     */
    public void setAccessDate(Date accessDate) {
        this.accessDate = accessDate;
    }

    /**
     * @return the processTime
     */
    public Double getProcessTime() {
        return processTime;
    }

    /**
     * @param processTime the processTime to set
     */
    public void setProcessTime(Double processTime) {
        this.processTime = processTime;
    }

    /**
     * @return the ver
     */
    public String getVer() {
        return ver;
    }

    /**
     * @param ver the ver to set
     */
    public void setVer(String ver) {
        this.ver = ver;
    }

    /**
     * @return the sys
     */
    public String getSys() {
        return sys;
    }

    /**
     * @param sys the sys to set
     */
    public void setSys(String sys) {
        this.sys = sys;
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
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    /**
     * @param query the query to set
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * @return the post
     */
    public String getPost() {
        return post;
    }

    /**
     * @param post the post to set
     */
    public void setPost(String post) {
        this.post = post;
    }

    /**
     * @return the protocol
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * @param protocol the protocol to set
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the method
     */
    public String getMethod() {
        return method;
    }

    /**
     * @param method the method to set
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * @return the realIp
     */
    public String getRealIp() {
        return realIp;
    }

    /**
     * @param realIp the realIp to set
     */
    public void setRealIp(String realIp) {
        this.realIp = realIp;
    }

    /**
     * @return the reomoteIp
     */
    public String getReomoteIp() {
        return reomoteIp;
    }

    /**
     * @param reomoteIp the reomoteIp to set
     */
    public void setReomoteIp(String reomoteIp) {
        this.reomoteIp = reomoteIp;
    }

    /**
     * @return the forwardedFor
     */
    public String getForwardedFor() {
        return forwardedFor;
    }

    /**
     * @param forwardedFor the forwardedFor to set
     */
    public void setForwardedFor(String forwardedFor) {
        this.forwardedFor = forwardedFor;
    }

    /**
     * @return the referer
     */
    public String getReferer() {
        return referer;
    }

    /**
     * @param referer the referer to set
     */
    public void setReferer(String referer) {
        this.referer = referer;
    }

    /**
     * @return the autoAgent
     */
    public String getAutoAgent() {
        return autoAgent;
    }

    /**
     * @param autoAgent the autoAgent to set
     */
    public void setAutoAgent(String autoAgent) {
        this.autoAgent = autoAgent;
    }
}

