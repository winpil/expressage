package com.cndatacom.rbac.pojo;

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
@Table(name = "expressage_city", catalog = "expressage")
public class ExpressageCity implements java.io.Serializable {

	// Fields

	private String cityId;
	private String name;
	private String provincialId;
	private String cityNo;

	// Constructors

	/** default constructor */
	public ExpressageCity() {
	}

	/** full constructor */
	public ExpressageCity(String name, String provincialId) {
		this.name = name;
		this.provincialId = provincialId;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "city_id", unique = true, nullable = false, length = 128)
	public String getCityId() {
		return this.cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	@Column(name = "name", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "provincial_id", length = 128)
	public String getProvincialId() {
		return this.provincialId;
	}

	public void setProvincialId(String provincialId) {
		this.provincialId = provincialId;
	}
	
	@Column(name = "city_no", length = 128)
	public String getCityNo() {
		return cityNo;
	}

	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}
	
	
}