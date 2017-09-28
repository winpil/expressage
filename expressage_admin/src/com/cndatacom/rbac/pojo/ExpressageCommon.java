package com.cndatacom.rbac.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ExpressageCommon entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "expressage_common", catalog = "expressage")
public class ExpressageCommon implements java.io.Serializable {

	// Fields

	private String commonId;
	private String userId;
	private String phone;
	private String name;
	private String provincialId;
	private String cityId;
	private String districtId;
	private String address;
	private String type;
	private Date createDate;
	private String remark;
	private String status;//是否默认，1为是，2为否

	// Constructors

	/** default constructor */
	public ExpressageCommon() {
	}

	/** full constructor */
	public ExpressageCommon(String userId, String phone, String name,
			String provincialId, String cityId, String address, String type,
			Date createDate, String remark) {
		this.userId = userId;
		this.phone = phone;
		this.name = name;
		this.provincialId = provincialId;
		this.cityId = cityId;
		this.address = address;
		this.type = type;
		this.createDate = createDate;
		this.remark = remark;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "common_id", unique = true, nullable = false)
	public String getCommonId() {
		return this.commonId;
	}

	public void setCommonId(String commonId) {
		this.commonId = commonId;
	}

	@Column(name = "user_id")
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "phone")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Column(name = "type")
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "create_date", length = 19)
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
	
	@Column(name = "district_id")
	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	
	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}