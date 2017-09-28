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
@Table(name = "expressage_f", catalog = "expressage")
public class ExpressageF implements java.io.Serializable {

	private String fid;
	private String fname;
	private String fvalue;
	private Date createDate;
	
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "fid", unique = true, nullable = false)
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	
	@Column(name = "fname")
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	@Column(name = "fvalue")
	public String getFvalue() {
		return fvalue;
	}
	public void setFvalue(String fvalue) {
		this.fvalue = fvalue;
	}
	
	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	
}