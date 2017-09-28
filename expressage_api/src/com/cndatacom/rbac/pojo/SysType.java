package com.cndatacom.rbac.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.GenericGenerator;

/**
 * 系统类型定义实体类
 * @author yab
 */
@Entity
@Table(name = "SYS_TYPE")
public class SysType {
	private String typeId;
	private String typeName;
	private String typeCode;
	private SysTypecategory typeCategory;
	private String typeNote;

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name="TYPE_ID",unique=true, nullable=false, length=32)
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		if ("".equals(typeId)) {
			typeId = null;
		}
		this.typeId = typeId;
	}
	
	@Column(name="TYPE_NAME",length=50)
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	@Column(name="TYPE_CODE",length=50)
	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	
	@ManyToOne
	@JoinColumn(name="TYPE_CATEGORYID")
	@JSON(serialize=false)
	public SysTypecategory getTypeCategory() {
		return typeCategory;
	}

	public void setTypeCategory(SysTypecategory typeCategory) {
		this.typeCategory = typeCategory;
	}
	
	@Column(name="TYPE_NOTE",length=250)
	public String getTypeNote() {
		return typeNote;
	}

	public void setTypeNote(String typeNote) {
		this.typeNote = typeNote;
	}
}
