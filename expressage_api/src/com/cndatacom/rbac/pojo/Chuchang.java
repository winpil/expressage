package com.cndatacom.rbac.pojo;

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
@Table(name = "chuchang", catalog = "expressage")
public class Chuchang implements java.io.Serializable {

	// Fields

	private String id;
	private String type;
	private String cn;
	private String qn;
	private String zname;
	private String hname;
	private String address;
	private String haddress;
	private String yytime;
	private String xxr;
	private String jt;
	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false, length = 128)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "cn")
	public String getCn() {
		return cn;
	}
	public void setCn(String cn) {
		this.cn = cn;
	}
	
	@Column(name = "qn")
	public String getQn() {
		return qn;
	}
	public void setQn(String qn) {
		this.qn = qn;
	}
	
	@Column(name = "zname")
	public String getZname() {
		return zname;
	}
	public void setZname(String zname) {
		this.zname = zname;
	}
	
	@Column(name = "hname")
	public String getHname() {
		return hname;
	}
	public void setHname(String hname) {
		this.hname = hname;
	}
	
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "yytime")
	public String getYytime() {
		return yytime;
	}
	public void setYytime(String yytime) {
		this.yytime = yytime;
	}
	
	@Column(name = "xxr")
	public String getXxr() {
		return xxr;
	}
	public void setXxr(String xxr) {
		this.xxr = xxr;
	}
	
	@Column(name = "jt")
	public String getJt() {
		return jt;
	}
	public void setJt(String jt) {
		this.jt = jt;
	}
	
	@Column(name = "haddress")
	public String getHaddress() {
		return haddress;
	}
	public void setHaddress(String haddress) {
		this.haddress = haddress;
	}
	
	
}