package com.cndatacom.rbac.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * 类名: ExpressageLog</br> 
 * 包名：com.cndatacom.rbac.pojo </br> 
 * 描述:日志记录实体类 </br>
 * 发布版本号：</br>
 * 开发人员： 朱恋青</br>
 * 创建时间： 2016-8-6 
 * 公司 ：广州亚乐恒网络科技有限公司(http://www.aleven.cn/)</br>
 */
@Entity
@Table(name = "expressage_log", catalog = "expressage")
public class ExpressageLog implements java.io.Serializable {

	// Fields

	private String logId;
	private String method;
	private String content;
	private String status;
	private String userId;
	private String courierId;
	private String ip;
	private String type;
	private Date createDate;

	// Constructors

	/** default constructor */
	public ExpressageLog() {
	}

	/** full constructor */
	public ExpressageLog(String method, String content, String status,
			String userId, String courierId, String ip, String type,
			Date createDate) {
		this.method = method;
		this.content = content;
		this.status = status;
		this.userId = userId;
		this.courierId = courierId;
		this.ip = ip;
		this.type = type;
		this.createDate = createDate;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "log_id", unique = true, nullable = false)
	public String getLogId() {
		return this.logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	@Column(name = "method")
	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "status")
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "user_id")
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

	@Column(name = "ip")
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "type")
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "create_date", length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}