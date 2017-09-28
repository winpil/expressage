package com.cndatacom.rbac.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ExpressageCompany entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "expressage_company", catalog = "expressage")
public class ExpressageCompany implements java.io.Serializable {

	// Fields

	private String companyId;
	private String companyName;
	private Date createDate;
	private String phone;
	private String linkman;
	private String remark;

	// Constructors

	/** default constructor */
	public ExpressageCompany() {
	}

	/** full constructor */
	public ExpressageCompany(String companyName, Date createDate, String phone,
			String linkman, String remark) {
		this.companyName = companyName;
		this.createDate = createDate;
		this.phone = phone;
		this.linkman = linkman;
		this.remark = remark;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "company_id", unique = true, nullable = false)
	public String getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Column(name = "company_name")
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "create_date", length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "phone")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "linkman")
	public String getLinkman() {
		return this.linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	@Column(name = "remark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}