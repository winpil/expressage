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
@Table(name = "expressage_integral", catalog = "expressage")
public class ExpressageIntegral implements java.io.Serializable {

	// Fields

	private String integralId;
	private String userId;
	private String content;
	private String integralNum;
	private String type;
	private String status;
	private Date createDate;
	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "integral_id", unique = true, nullable = false, length = 32)
	public String getIntegralId() {
		return integralId;
	}
	public void setIntegralId(String integralId) {
		this.integralId = integralId;
	}
	
	@Column(name = "user_id")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name = "content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name = "integral_num")
	public String getIntegralNum() {
		return integralNum;
	}
	public void setIntegralNum(String integralNum) {
		this.integralNum = integralNum;
	}
	
	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	



}