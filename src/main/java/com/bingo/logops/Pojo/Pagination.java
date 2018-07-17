/**
 * 
 */
package com.bingo.logops.Pojo;

import java.util.List;

/**
 * MongodbBaseDao
 */
public class Pagination<T> {
	/** 当前页码 */
	private int pageNo;
	
	/** 一页数据默认10条 */
	private int pageSize = 10;
	

	/** 上一页 */
	private int upPage;

	/** 下一页 */
	private int nextPage;
	
	/** 一共有多少条数据 */
	private long totalCount;

	/** 一共有多少页 */
	private int totalPage;
	
	/** 数据集合 */
	private List<T> list;

	/** 分页的url */
	private String pageUrl;

	/**
	 * 获取第一条记录位置
	 */
	public int getFirstResult() {
		return (this.getPageNo() - 1) * this.getPageSize();
	}

	/**
	 * 获取最后记录位置
	 */
	public int getLastResult() {
		return this.getPageNo() * this.getPageSize();
	}

	/**
	 * 计算一共多少页
	 */
	public void setTotalPage() {
		this.totalPage = (int) ((this.totalCount % this.pageSize > 0) ? (this.totalCount / this.pageSize + 1)
				: this.totalCount / this.pageSize);
	}

	/**
	 * 设置 上一页
	 */
	public void setUpPage() {
		this.upPage = (this.pageNo > 1) ? this.pageNo - 1 : this.pageNo;
	}

	/**
	 * 设置下一页
	 */
	public void setNextPage() {
		this.nextPage = (this.pageNo == this.totalPage) ? this.pageNo : this.pageNo + 1;
	}

	/**
	 * 构造函数
	 * 
	 * @param pageNo			页码
	 * @param pageSize			每页大小
	 * @param totalCount		总记录数
	 */
	public Pagination(int pageNo, int pageSize, long totalCount) {
		this.setPageNo(pageNo);
		this.setPageSize(pageSize);
		this.setTotalCount(totalCount);
		this.init();
	}
	
	/**
	 * @return the pageNo
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * @param pageNo the pageNo to set
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the upPage
	 */
	public int getUpPage() {
		return upPage;
	}

	/**
	 * @param upPage the upPage to set
	 */
	public void setUpPage(int upPage) {
		this.upPage = upPage;
	}

	/**
	 * @return the nextPage
	 */
	public int getNextPage() {
		return nextPage;
	}

	/**
	 * @param nextPage the nextPage to set
	 */
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	/**
	 * @return the totalCount
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @return the totalPage
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * @param totalPage the totalPage to set
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * @return the list
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<T> list) {
		this.list = list;
	}

	/**
	 * @return the pageUrl
	 */
	public String getPageUrl() {
		return pageUrl;
	}

	/**
	 * @param pageUrl the pageUrl to set
	 */
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	/**
	 * 初始化计算分页
	 */
	private void init() {
		this.setTotalPage();	// 设置一共页数
		this.setUpPage();		// 设置上一页
		this.setNextPage();		// 设置下一页
	}
}
