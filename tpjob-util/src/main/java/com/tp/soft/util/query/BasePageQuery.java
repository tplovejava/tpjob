package com.tp.soft.util.query;

/**
 * 
 * TODO: 基础分页查询对象
 * 
 */
public class BasePageQuery extends BaseQuery {

	/**
	 * 每页记录数
	 */
	private Integer pageSize = 20;

	/**
	 * 显示页码
	 */
	private Integer pageNumber = 1;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getStartRow() {
 		return (null == this.pageNumber || 0 >= this.pageNumber ? 0 : (this.pageNumber - 1) * this.pageSize);
	}

	public Integer getEndRow() {
		return this.getStartRow() + this.pageSize;
	}

}
