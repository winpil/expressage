package com.cndatacom.rbac.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * ExpressageCourier entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "expressage_courier_dl", catalog = "expressage")
public class ExpressageCourierDl implements java.io.Serializable {

	// Fields

	private String courierId;
	private String courierName;
	private String provinceName;
	private String cityName;
	private String districtsName;
	private String phone;
	private String company;
	private String send;
	private String remark;

	/** default constructor */
	public ExpressageCourierDl() {
	}

	/** full constructor */
	
	public ExpressageCourierDl(String courierName, String provinceName,
			String cityName, String districtsName, String phone,
			String company, String send, String remark) {
		super();
		this.courierName = courierName;
		this.provinceName = provinceName;
		this.cityName = cityName;
		this.districtsName = districtsName;
		this.phone = phone;
		this.company = company;
		this.send = send;
		this.remark = remark;
	}
	
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "courier_id", unique = true, nullable = false)
	public String getCourierId() {
		return this.courierId;
	}

	public void setCourierId(String courierId) {
		this.courierId = courierId;
	}

	@Column(name = "phone")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name = "courier_name")
	public String getCourierName() {
		return courierName;
	}

	public void setCourierName(String courierName) {
		this.courierName = courierName;
	}
	
	@Column(name = "province_name")
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	@Column(name = "city_name")
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	@Column(name = "districts_name")
	public String getDistrictsName() {
		return districtsName;
	}

	public void setDistrictsName(String districtsName) {
		this.districtsName = districtsName;
	}
	
	@Column(name = "company")
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	@Column(name = "send")
	public String getSend() {
		return send;
	}

	public void setSend(String send) {
		this.send = send;
	}
	
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}