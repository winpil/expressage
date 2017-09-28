package com.cndatacom.rbac.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 * ExpressageFavorable entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "expressage_favorable", catalog = "expressage")
public class ExpressageFavorable implements java.io.Serializable {

	// Fields

	private String favorableId;
	private String favorableName;
	private String userId;
	private String status;
	private Date createDate;
	private Date pastDue;
	private String condit;

	// Constructors

	/** default constructor */
	public ExpressageFavorable() {
	}

	/** full constructor */
	public ExpressageFavorable(String favorableName, String userId,
			String status, Date createDate, Date pastDue, String condit) {
		this.favorableName = favorableName;
		this.userId = userId;
		this.status = status;
		this.createDate = createDate;
		this.pastDue = pastDue;
		this.condit = condit;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "favorable_id", unique = true, nullable = false)
	public String getFavorableId() {
		return this.favorableId;
	}

	public void setFavorableId(String favorableId) {
		this.favorableId = favorableId;
	}

	@Column(name = "favorable_name")
	public String getFavorableName() {
		return this.favorableName;
	}

	public void setFavorableName(String favorableName) {
		this.favorableName = favorableName;
	}

	@Column(name = "user_id")
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "status")
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "create_date", length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "past_due", length = 10)
	public Date getPastDue() {
		return this.pastDue;
	}

	public void setPastDue(Date pastDue) {
		this.pastDue = pastDue;
	}

	@Column(name = "condit")
	public String getCondit() {
		return this.condit;
	}

	public void setCondit(String condit) {
		this.condit = condit;
	}

}