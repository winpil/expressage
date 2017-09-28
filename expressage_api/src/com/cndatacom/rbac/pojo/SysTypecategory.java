package com.cndatacom.rbac.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * 系统类型说明实体类 
 * @author yab
 *
 */
@Entity
@Table(name = "SYS_TYPECATEGORY")
public class SysTypecategory implements Serializable {

	private static final long serialVersionUID = 1L;

	private String typeCategoryid;
	private String typeCodeName;
	
	@Id 
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
    @Column(name="TYPE_CATEGORYID",unique=true, nullable=false, length=32)
	public String getTypeCategoryid() {
		return typeCategoryid;
	}    
    
	public void setTypeCategoryid(String typeCategoryid) {
		if ("".equals(typeCategoryid)) {
			typeCategoryid = null;
		}
		this.typeCategoryid = typeCategoryid;
	}
	
	@Column(name="TYPE_CODE_NAME", length=50)
	public String getTypeCodeName() {
		return typeCodeName;
	}

	public void setTypeCodeName(String typeCodeName) {
		this.typeCodeName = typeCodeName;
	}
	

}