package com.cndatacom.rbac.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ExpressageVersion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "expressage_version", catalog = "expressage")
public class ExpressageVersion implements java.io.Serializable {

	// Fields

	private String versionId;
	private String versionName;
	private String url;
	private Date updateTime;
	private String versionCode;
	private String versionIntroduction;
	private String passageway;
	private String typeId;

	// Constructors

	/** default constructor */
	public ExpressageVersion() {
	}

	/** full constructor */
	public ExpressageVersion(String versionName, String url, Date updateTime,
			String versionCode, String versionIntroduction, String passageway,
			String typeId) {
		this.versionName = versionName;
		this.url = url;
		this.updateTime = updateTime;
		this.versionCode = versionCode;
		this.versionIntroduction = versionIntroduction;
		this.passageway = passageway;
		this.typeId = typeId;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "version_Id", unique = true, nullable = false)
	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	@Column(name = "version_name")
	public String getVersionName() {
		return this.versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	@Column(name = "url")
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "update_time", length = 19)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "version_code")
	public String getVersionCode() {
		return this.versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	@Column(name = "version_introduction")
	public String getVersionIntroduction() {
		return this.versionIntroduction;
	}

	public void setVersionIntroduction(String versionIntroduction) {
		this.versionIntroduction = versionIntroduction;
	}

	@Column(name = "passageway")
	public String getPassageway() {
		return this.passageway;
	}

	public void setPassageway(String passageway) {
		this.passageway = passageway;
	}

	@Column(name = "type_id")
	public String getTypeId() {
		return this.typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

}