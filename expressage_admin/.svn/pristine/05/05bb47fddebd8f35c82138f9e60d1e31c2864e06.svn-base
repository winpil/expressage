package com.cndatacom.rbac.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ExpressageComment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "expressage_comment", catalog = "expressage")
public class ExpressageComment implements java.io.Serializable {

	// Fields

	private String commentId;
	private String courierId;
	private String orderId;
	private String rank;
	private Date createDate;
	private String remark;

	// Constructors

	/** default constructor */
	public ExpressageComment() {
	}

	/** full constructor */
	public ExpressageComment(String courierId, String orderId, String rank,
			Date createDate, String remark) {
		this.courierId = courierId;
		this.orderId = orderId;
		this.rank = rank;
		this.createDate = createDate;
		this.remark = remark;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "comment_id", unique = true, nullable = false)
	public String getCommentId() {
		return this.commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	@Column(name = "courier_id")
	public String getCourierId() {
		return this.courierId;
	}

	public void setCourierId(String courierId) {
		this.courierId = courierId;
	}

	@Column(name = "order_id")
	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name = "rank")
	public String getRank() {
		return this.rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
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

}