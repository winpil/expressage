package com.cndatacom.rbac.pojo;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * ExpressageBank entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "expressage_bank", catalog = "expressage")
public class ExpressageBank implements java.io.Serializable {

	private String codeId;
	private String codeNumber;
	private String courierId;
	private String bankName;
	private Date createDate;
	private String remark;


	/** default constructor */
	public ExpressageBank() {
	}

	public ExpressageBank(String codeId, String codeNumber, String courierId,
			String bankName, Date createDate, String remark) {
		this.codeId = codeId;
		this.codeNumber = codeNumber;
		this.courierId = courierId;
		this.bankName = bankName;
		this.createDate = createDate;
		this.remark = remark;
	}
	
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "code_id", unique = true, nullable = false)
	public String getCodeId() {
		return this.codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	@Column(name = "code_number")
	public String getCodeNumber() {
		return this.codeNumber;
	}

	public void setCodeNumber(String codeNumber) {
		this.codeNumber = codeNumber;
	}

	@Column(name = "courier_id")
	public String getCourierId() {
		return this.courierId;
	}

	public void setCourierId(String courierId) {
		this.courierId = courierId;
	}

	@Column(name = "bank_name")
	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
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