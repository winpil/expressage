package com.cndatacom.rbac.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "expressage_pay_log", catalog = "expressage")
public class ExpressagePayLog implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String payId;
	private String type;//1Ϊ�ֽ�֧����2Ϊ΢��֧����3Ϊ֧����֧����4Ϊ���֣�5Ϊ������6Ϊ����
	private String moneyNum;//���
	private String userId;
	private String courierId;
	private String orderId;
	private String remark;//��ע
	private String status;//״̬��1Ϊ�ɹ���0Ϊʧ�ܣ�2������
	private String bankName;//��������
	private String bankCode;//���п���
	private Date createDate;
	
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "pay_id", unique = true, nullable = false)
	public String getPayId() {
		return payId;
	}
	public void setPayId(String payId) {
		this.payId = payId;
	}
	
	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "money_num")
	public String getMoneyNum() {
		return moneyNum;
	}
	public void setMoneyNum(String moneyNum) {
		this.moneyNum = moneyNum;
	}
	
	@Column(name = "user_id")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name = "courier_id")
	public String getCourierId() {
		return courierId;
	}
	public void setCourierId(String courierId) {
		this.courierId = courierId;
	}
	
	@Column(name = "order_id")
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name = "bank_name")
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	@Column(name = "bank_code")
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	
	
}