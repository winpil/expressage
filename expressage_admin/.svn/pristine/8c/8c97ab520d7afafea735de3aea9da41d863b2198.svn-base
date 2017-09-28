package com.cndatacom.rbac.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "expressage_stage", catalog = "expressage")
public class ExpressageStage implements java.io.Serializable {
	
	private String stageId;
	private String stageName;
	private String stageAddress;
	private String stagePhone;
	private String longitude;
	private String latitude; 
	
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "stage_id", unique = true, nullable = false)
	public String getStageId() {
		return stageId;
	}
	public void setStageId(String stageId) {
		this.stageId = stageId;
	}
	
	@Column(name = "stage_name")
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	
	@Column(name = "stage_address")
	public String getStageAddress() {
		return stageAddress;
	}
	public void setStageAddress(String stageAddress) {
		this.stageAddress = stageAddress;
	}
	
	@Column(name = "stage_phone")
	public String getStagePhone() {
		return stagePhone;
	}
	public void setStagePhone(String stagePhone) {
		this.stagePhone = stagePhone;
	}
	@Column(name = "longitude")
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	@Column(name = "latitude")
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	
}
