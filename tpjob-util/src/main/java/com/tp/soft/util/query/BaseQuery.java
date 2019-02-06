package com.tp.soft.util.query;

/**
 * 
 * TODO: 基础查询对象
 */
public class BaseQuery {

	private String id;

	private Integer lockFlag;

	private Integer isDel;

	private String orderString;

	private String orderField;

	private String orderDirection;

	/**
	 * 管理用户ID，用户区分数据权限
	 */
	private String manageUserId;

	public String getManageUserId() {
		return manageUserId;
	}

	public void setManageUserId(String manageUserId) {
		this.manageUserId = manageUserId;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getLockFlag() {
		return lockFlag;
	}

	public void setLockFlag(Integer lockFlag) {
		this.lockFlag = lockFlag;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public String getOrderString() {
		return orderString;
	}

	public void setOrderString(String orderString) {
		this.orderString = orderString;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}

}
