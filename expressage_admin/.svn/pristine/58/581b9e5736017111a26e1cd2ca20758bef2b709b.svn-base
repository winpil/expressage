package com.cndatacom.rbac.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ExpressageMessage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "expressage_message", catalog = "expressage")
public class ExpressageMessage implements java.io.Serializable {

	// Fields

	private String messageId;
	private String title;
	private String content;
	private String userId;
	private String courierId;
	private Date createDate;
	private String status;

	// Constructors

	/** default constructor */
	public ExpressageMessage() {
	}

	/** full constructor */
	public ExpressageMessage(String title, String content, String userId,
			String courierId, Date createDate, String status) {
		this.title = title;
		this.content = content;
		this.userId = userId;
		this.courierId = courierId;
		this.createDate = createDate;
		this.status = status;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "message_id", unique = true, nullable = false, length = 128)
	public String getMessageId() {
		return this.messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	@Column(name = "title", length = 50)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", length = 128)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "user_id", length = 128)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "courier_id")
	public String getCourierId() {
		return this.courierId;
	}

	public void setCourierId(String courierId) {
		this.courierId = courierId;
	}

	@Column(name = "create_date", length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "status", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}