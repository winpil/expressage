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
 * ����: ExpressageOrderLog</br> 
 * ������com.cndatacom.rbac.pojo </br> 
 * ����: ������־ʵ����</br>
 * �����汾�ţ�</br>
 * ������Ա�� ������</br>
 * ����ʱ�䣺 2016-9-30 
 * ��˾ ���������ֺ�����Ƽ����޹�˾(http://www.aleven.cn/)</br>
 */
@Entity
@Table(name = "expressage_order_log", catalog = "expressage")
public class ExpressageOrderLog implements java.io.Serializable {

	private String orderLogId;
	private String orderId;
	private String content;
	private String method;
	private String userId;
	private String type;//1Ϊ�û���2Ϊ���Ա��3Ϊ����Ա
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