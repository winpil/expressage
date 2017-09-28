package com.cndatacom.rbac.pojo;

// Generated 2010-11-26 11:23:15 by Hibernate Tools 3.2.1.GA
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.GenericGenerator;

/**
 * SysLog generated by hbm2java
 */
@Entity
@Table(name = "SYS_LOG")
public class SysLog implements Serializable {

	private static final long serialVersionUID = 9158121894294668798L;

	private String id;
	private String moduleName;
	private Long logType;
	private String operAccount;
	private String operDetail;
	private String operStatus;
	private String clientIp;
	private Date createdtime;

	public SysLog() {
	}

	public SysLog(String id, Date createdtime) {
		this.id = id;
		this.createdtime = createdtime;
	}

	public SysLog(String id, String moduleName, Long logType,
			String operAccount, String operDetail, String operStatus,
			String clientIp, Date createdtime) {
		this.id = id;
		this.moduleName = moduleName;
		this.logType = logType;
		this.operAccount = operAccount;
		this.operDetail = operDetail;
		this.operStatus = operStatus;
		this.clientIp = clientIp;
		this.createdtime = createdtime;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		if ("".equals(id)) {
			id = null;
		}
		this.id = id;
	}

	@Column(name = "MODULE_NAME", length = 50)
	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	@Column(name = "LOG_TYPE", precision = 4, scale = 0)
	public Long getLogType() {
		return this.logType;
	}

	public void setLogType(Long logType) {
		this.logType = logType;
	}

	@Column(name = "OPER_ACCOUNT", length = 50)
	public String getOperAccount() {
		return this.operAccount;
	}

	public void setOperAccount(String operAccount) {
		this.operAccount = operAccount;
	}

	@Column(name = "OPER_DETAIL", length = 1024)
	public String getOperDetail() {
		return this.operDetail;
	}

	public void setOperDetail(String operDetail) {
		this.operDetail = operDetail;
	}

	@Column(name = "OPER_STATUS", length = 50)
	public String getOperStatus() {
		return this.operStatus;
	}

	public void setOperStatus(String operStatus) {
		this.operStatus = operStatus;
	}

	@Column(name = "CLIENT_IP", length = 64)
	public String getClientIp() {
		return this.clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	@JSON(format = "yyyy-MM-dd HH��mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDTIME", nullable = false, length = 7)
	public Date getCreatedtime() {
		return this.createdtime;
	}

	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}

}
