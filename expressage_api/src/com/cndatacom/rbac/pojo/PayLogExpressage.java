package com.cndatacom.rbac.pojo;


public class PayLogExpressage {
	
	private String payId;
	private String type;//1为现金支付，2为微信支付，3为支付宝支付，4为提现，5为奖励
	private String moneyNum;//金额
	private String userId;
	private String courierId;
	private String orderId;
	private String remark;//备注
	private String status;//状态，1为成功，0为失败，进行中
	private String bankName;//银行名称
	private String bankCode;//银行卡号
	private String createDate;
	private String balance;
	public String getPayId() {
		return payId;
	}
	public void setPayId(String payId) {
		this.payId = payId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMoneyNum() {
		return moneyNum;
	}
	public void setMoneyNum(String moneyNum) {
		this.moneyNum = moneyNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCourierId() {
		return courierId;
	}
	public void setCourierId(String courierId) {
		this.courierId = courierId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	
	
}
