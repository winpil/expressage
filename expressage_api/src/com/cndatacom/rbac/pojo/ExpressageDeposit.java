package com.cndatacom.rbac.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "expressage_deposit", catalog = "expressage")
public class ExpressageDeposit implements java.io.Serializable{
	
	private String depositId;
	private String courierId;//
	private String stageId;
	private String orderNumber;
	private String phone;
	private String yzName;
	private Date createDate;
	
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "deposit_id", unique = true, nullable = false)
	public String getDepositId() {
		return depositId;
	}
	public void setDepositId(String depositId) {
		this.depositId = depositId;
	}
	
	@Column(name = "courier_id")
	public String getCourierId() {
		return courierId;
	}
	public void setCourierId(String courierId) {
		this.courierId = courierId;
	}
	
	@Column(name = "stage_id")
	public String getStageId() {
		return stageId;
	}
	public void setStageId(String stageId) {
		this.stageId = stageId;
	}
	
	@Column(name = "order_number")
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name = "yz_name")
	public String getYzName() {
		return yzName;
	}
	public void setYzName(String yzName) {
		this.yzName = yzName;
	}
	
	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	
}
