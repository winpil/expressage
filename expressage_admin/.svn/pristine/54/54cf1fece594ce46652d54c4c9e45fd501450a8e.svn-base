package com.cndatacom.rbac.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ExpressageProvincial entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "expressage_provincial", catalog = "expressage")
public class ExpressageProvincial implements java.io.Serializable {

	// Fields

	private String provincialId;
	private String name;
	private String wm;

	// Constructors

	/** default constructor */
	public ExpressageProvincial() {
	}

	/** full constructor */
	public ExpressageProvincial(String name, String wm) {
		this.name = name;
		this.wm = wm;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "provincial_id", unique = true, nullable = false, length = 3)
	public String getProvincialId() {
		return this.provincialId;
	}

	public void setProvincialId(String provincialId) {
		this.provincialId = provincialId;
	}

	@Column(name = "name", length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "wm", length = 5)
	public String getWm() {
		return this.wm;
	}

	public void setWm(String wm) {
		this.wm = wm;
	}

}