package com.cndatacom.rbac.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ExpressageComCourier entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "expressage_com_courier", catalog = "expressage")
public class ExpressageComCourier implements java.io.Serializable {

	// Fields

	private String comCourierId;
	private String courierId;
	private String userId;

	// Constructors

	/** default constructor */
	public ExpressageComCourier() {
	}

	/** full constructor */
	public ExpressageComCourier(String courierId, String userId) {
		this.courierId = courierId;
		this.userId = userId;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "com_courier_id", unique = true, nullable = false)
	public String getComCourierId() {
		return this.comCourierId;
	}

	public void setComCourierId(String comCourierId) {
		this.comCourierId = comCourierId;
	}

	@Column(name = "courier_id")
	public String getCourierId() {
		return this.courierId;
	}

	public void setCourierId(String courierId) {
		this.courierId = courierId;
	}

	@Column(name = "user_id")
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}