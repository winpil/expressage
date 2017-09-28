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
 * 
 * 类名: ExpressageOrderLog</br> 
 * 包名：com.cndatacom.rbac.pojo </br> 
 * 描述: 订单日志实体类</br>
 * 发布版本号：</br>
 * 开发人员： 朱恋青</br>
 * 创建时间： 2016-9-30 
 * 公司 ：广州亚乐恒网络科技有限公司(http://www.aleven.cn/)</br>
 */
@Entity
@Table(name = "expressage_order_log", catalog = "expressage")
public class ExpressageOrderLog implements java.io.Serializable {

	private String orderLogId;
	private String orderId;
	private String content;
	private String method;
	private String userId;
	private String type;//1为用户，2为快递员，3为管理员
	private Date createDate;


	/** default constructor */
	public ExpressageOrderLog() {
	}

	
	/** full constructor */
	public ExpressageOrderLog(String orderLogId, String orderId,
			String content, String method, Date createDate) {
		this.orderLogId = orderLogId;
		this.orderId = orderId;
		this.content = content;
		this.method = method;
		this.createDate = createDate;
	}
	
	@GenericGenerator(name="generator", strategy="uuid")@Id @GeneratedValue(generator="generator")
    
    @Column(name="order_log_id", unique=true, nullable=false, length=3)
	public String getOrderLogId() {
		return this.orderLogId;
	}

	public void setOrderLogId(String orderLogId) {
		this.orderLogId = orderLogId;
	}

	@Column(name = "order_id")
	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "method")
	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	@Column(name = "create_date", length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "user_id", length = 19)
	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "type", length = 19)
	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	
	
	
}