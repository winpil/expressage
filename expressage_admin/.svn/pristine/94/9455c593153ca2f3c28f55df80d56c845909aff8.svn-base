package com.cndatacom.rbac.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ExpressageUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "expressage_user", catalog = "expressage")
public class ExpressageUser implements java.io.Serializable {

	
	private static final long serialVersionUID = -9174263509448186863L;
	private String userId;
	private String userName;
	private String phone;
	private String phoneNumber;
	private String password;
	private String avatar;
	private String gender;
	private String couponNum;
	private String isLock;
	private Date createDate;
	private String remark;
	private String integral;
	private String validIntegral;
	private String reputation;
	private ExpressageUser referrerId;

	// Constructors

	/** default constructor */
	public ExpressageUser() {
	}

	/** full constructor */
	public ExpressageUser(String userName, String phone, String password,
			String avatar, String gender, String isLock, String couponNum, String remark,Date createDate) {
		this.userName = userName;
		this.phone = phone;
		this.password = password;
		this.avatar = avatar;
		this.gender = gender;
		this.couponNum = couponNum;
		this.remark = remark;
		this.isLock = isLock;
		this.createDate = createDate;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "user_id", unique = true, nullable = false)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "user_name")
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "phone")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name = "phone_number")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "password")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "avatar")
	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Column(name = "gender")
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "coupon_num")
	public String getCouponNum() {
		return this.couponNum;
	}

	public void setCouponNum(String couponNum) {
		this.couponNum = couponNum;
	}
	
	@Column(name = "is_lock")
	public String getIsLock() {
		return isLock;
	}

	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}

	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "referrer_id")
	public ExpressageUser getReferrerId() {
		return referrerId;
	}

	public void setReferrerId(ExpressageUser referrerId) {
		this.referrerId = referrerId;
	}
	
	
	@Column(name = "integral")
	public String getIntegral() {
		return integral;
	}

	public void setIntegral(String integral) {
		this.integral = integral;
	}
	
	
	@Column(name = "valid_integral")
	public String getValidIntegral() {
		return validIntegral;
	}

	public void setValidIntegral(String validIntegral) {
		this.validIntegral = validIntegral;
	}
	
	
	@Column(name = "reputation")
	public String getReputation() {
		return reputation;
	}

	public void setReputation(String reputation) {
		this.reputation = reputation;
	}
	
	
	
}