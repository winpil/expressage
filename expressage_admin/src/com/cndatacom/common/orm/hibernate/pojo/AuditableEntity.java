package com.cndatacom.common.orm.hibernate.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.apache.struts2.json.annotations.JSON;

/**
 * 含审计信息的Entity基类.
 * 
 * @author calvin
 */
@MappedSuperclass
public class AuditableEntity {

	protected Date createdDate;
	protected Date modifiedDate;

	/**
	 * 创建时间.
	 */
	// 本属性只在save时有效,update时无效.
	@Column(updatable = false, name = "CREATED_DATE")
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * 最后修改时间.
	 */
	// 本属性只在update时有效,save时无效.
	@Column(insertable = false, name = "MODIFIED_DATE")
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
