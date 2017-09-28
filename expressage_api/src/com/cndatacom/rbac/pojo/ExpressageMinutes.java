package com.cndatacom.rbac.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * ExpressageCity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "expressage_minutes", catalog = "expressage")
public class ExpressageMinutes implements java.io.Serializable {

	// Fields

	private String minutesId;
	private String userId;
	private String courierId;
	private String minutesNum;
	private String mesage;
	private String type;	
	private Date createDate;


	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "minutes_id", unique = true, nullable = false, length = 128)
	public String getMinutesId() {
		return minutesId;
	}

	public void setMinutesId(String minutesId) {
		this.minutesId = minutesId;
	}
	
	@Column(name = "user_id", length = 128)
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name = "courier_id", length = 128)
	public String getCourierId() {
		return courierId;
	}
	
	public void setCourierId(String courierId) {
		this.courierId = courierId;
	}
	
	@Column(name = "minutes_num", length = 128)
	public String getMinutesNum() {
		return minutesNum;
	}
	
	public void setMinutesNum(String minutesNum) {
		this.minutesNum = minutesNum;
	}
	
	@Column(name = "mesage", length = 128)
	public String getMesage() {
		return mesage;
	}
	
	public void setMesage(String mesage) {
		this.mesage = mesage;
	}
	
	@Column(name = "type", length = 128)
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "create_date", length = 128)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	
	
}