package com.cndatacom.rbac.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * 类名: SysBase</br> 
 * 包名：com.cndatacom.rbac.pojo </br> 
 * 描述: 通用接口模板实体类</br>
 * 发布版本号：</br>
 * 开发人员： 朱恋青</br>
 * 创建时间： 2015-6-29
 */
@Entity
@Table(name="expressage_base"
    ,catalog="expressage"
)
public class SysBase implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String baseId;
	private String sqlName;
	private String parameterNum;
	private String paramenter1;
	private String paramenter2;
	private String paramenter3;
	private String paramenter4;
	private String paramenter5;
	private String paramenter6;
	private String pages;
	private String pageSize;
	private String depict;
	private String type;
	
	@GenericGenerator(name="generator", strategy="uuid")@Id @GeneratedValue(generator="generator")
    @Column(name="base_id", unique=true, nullable=false, length=128)
	public String getBaseId() {
		return baseId;
	}
	public void setBaseId(String baseId) {
		this.baseId = baseId;
	}
	
	@Column(name="sql_name")
	public String getSqlName() {
		return sqlName;
	}
	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
	}
	
	@Column(name="parameter_num")
	public String getParameterNum() {
		return parameterNum;
	}
	public void setParameterNum(String parameterNum) {
		this.parameterNum = parameterNum;
	}
	
	@Column(name="paramenters1")
	public String getParamenter1() {
		return paramenter1;
	}
	public void setParamenter1(String paramenter1) {
		this.paramenter1 = paramenter1;
	}
	
	@Column(name="paramenters2")
	public String getParamenter2() {
		return paramenter2;
	}
	public void setParamenter2(String paramenter2) {
		this.paramenter2 = paramenter2;
	}
	
	@Column(name="paramenters3")
	public String getParamenter3() {
		return paramenter3;
	}
	public void setParamenter3(String paramenter3) {
		this.paramenter3 = paramenter3;
	}
	
	@Column(name="paramenters4")
	public String getParamenter4() {
		return paramenter4;
	}
	public void setParamenter4(String paramenter4) {
		this.paramenter4 = paramenter4;
	}
	
	@Column(name="paramenters5")
	public String getParamenter5() {
		return paramenter5;
	}
	public void setParamenter5(String paramenter5) {
		this.paramenter5 = paramenter5;
	}
	
	@Column(name="paramenters6")
	public String getParamenter6() {
		return paramenter6;
	}
	public void setParamenter6(String paramenter6) {
		this.paramenter6 = paramenter6;
	}
	
	@Column(name="depict")
	public String getDepict() {
		return depict;
	}
	public void setDepict(String depict) {
		this.depict = depict;
	}
	
	@Column(name="page")
	public String getPages() {
		return pages;
	}
	public void setPages(String pages) {
		this.pages = pages;
	}
	
	@Column(name="page_size")
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	
	@Column(name="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
