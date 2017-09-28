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
@Table(name = "pp_detail", catalog = "expressage")
public class PpDetail implements java.io.Serializable {

	// Fields

	private String id;
	private Integer moneyNum;
	private String ccId;
	private String rcId;
	
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
	
	@Column(name = "money_num")
	public Integer getMoneyNum() {
		return moneyNum;
	}
	public void setMoneyNum(Integer moneyNum) {
		this.moneyNum = moneyNum;
	}
	
	@Column(name = "cc_id")
	public String getCcId() {
		return ccId;
	}
	public void setCcId(String ccId) {
		this.ccId = ccId;
	}
	
	@Column(name = "rc_id")
	public String getRcId() {
		return rcId;
	}
	public void setRcId(String rcId) {
		this.rcId = rcId;
	}
	
	


	
}