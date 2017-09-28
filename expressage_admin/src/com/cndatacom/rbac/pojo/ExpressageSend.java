package com.cndatacom.rbac.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * ExpressageSend entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "expressage_send", catalog = "expressage")
public class ExpressageSend implements java.io.Serializable {

	// Fields

	private String sendId;
	private String courierId;
	private String provincialId;
	private String cityId;
	private String address;
	private Date createDate;
	private String remark;

	// Constructors

	/** default constructor */
	public ExpressageSend() {
	}

	/** full constructor */
	public ExpressageSend(String courierId, String provincialId, String cityId,
			String address, Date createDate, String remark) {
		this.courierId = courierId;
		this.provincialId = provincialId;
		this.cityId = cityId;
		this.address = address;
		this.createDate = createDate;
		this.remark = remark;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "send_id", unique = true, nullable = false)
	public String getSendId() {
		return this.sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	@Column(name = "courier_id")
	public String getCourierId() {
		return this.courierId;
	}

	public void setCourierId(String courierId) {
		this.courierId = courierId;
	}

	@Column(name = "provincial_id")
	public String getProvincialId() {
		return this.provincialId;
	}

	public void setProvincialId(String provincialId) {
		this.provincialId = provincialId;
	}

	@Column(name = "city_id")
	public String getCityId() {
		return this.cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	@Column(name = "address")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "create_date")
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "remark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}