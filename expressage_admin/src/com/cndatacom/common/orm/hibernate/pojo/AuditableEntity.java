package com.cndatacom.common.orm.hibernate.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.apache.struts2.json.annotations.JSON;

/**
 * �������Ϣ��Entity����.
 * 
 * @author calvin
 */
@MappedSuperclass
public class AuditableEntity {

	protected Date createdDate;
	protected Date modifiedDate;

	/**
	 * ����ʱ��.
	 */
	// ������ֻ��saveʱ��Ч,updateʱ��Ч.
	@Column(updatable = false, name = "CREATED_DATE")
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * ����޸�ʱ��.
	 */
	// ������ֻ��updateʱ��Ч,saveʱ��Ч.
	@Column(insertable = false, name = "MODIFIED_DATE")
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
