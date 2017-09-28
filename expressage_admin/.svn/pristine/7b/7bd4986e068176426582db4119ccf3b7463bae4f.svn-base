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
 * BaseIdeaFeedback entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "expressage_idea", catalog = "expressage")
public class ExpressageIdea implements java.io.Serializable {

	// Fields

	private String id;
	private ExpressageUser userId;
	private ExpressageCourier courierId;
	private String content;
	private String type;
	private String status;
	private String orderNo;
	private Date createDate;
	private Date updateDate;
	private String sysUser;
	private String remarks;

	// Constructors

	/** default constructor */
	public ExpressageIdea() {
	}

	/** full constructor */
	public ExpressageIdea( String content, String type,
			Date createDate, Date updateDate, String sysUser,
			String remarks) {
		this.content = content;
		this.type = type;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.sysUser = sysUser;
		this.remarks = remarks;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "type", length = 1)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "create_date", length = 0)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "update_date", length = 0)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "sys_user", length = 32)
	public String getSysUser() {
		return this.sysUser;
	}

	public void setSysUser(String sysUser) {
		this.sysUser = sysUser;
	}

	@Column(name = "remarks")
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "order_no")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public ExpressageUser getUserId() {
		return userId;
	}

	public void setUserId(ExpressageUser userId) {
		this.userId = userId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "courier_id")
	public ExpressageCourier getCourierId() {
		return courierId;
	}

	public void setCourierId(ExpressageCourier courierId) {
		this.courierId = courierId;
	}
	
	
	
}