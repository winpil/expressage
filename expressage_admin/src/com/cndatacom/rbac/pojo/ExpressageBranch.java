package com.cndatacom.rbac.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "expressage_branch", catalog = "expressage")
public class ExpressageBranch  implements java.io.Serializable{
	
	private String branchId;
	private String branchName;
	
	//private String companyId;
	private ExpressageCompany companyId;
	
	private ExpressageProvincial provincialId;
	private ExpressageCity cityId;
	private ExpressageDistrict districtId;
	private String addressId;
	
	/*private String describe;*/
	private String introduce;//√Ë ˆ
	
	
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "branch_id", unique = true, nullable = false)
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	
	@Column(name = "branch_name")
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "company_id")
	public ExpressageCompany getCompanyId() {
		return companyId;
	}
	public void setCompanyId(ExpressageCompany companyId) {
		this.companyId = companyId;
	}

	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "provincial_id")
	public ExpressageProvincial getProvincialId() {
		return provincialId;
	}
	public void setProvincialId(ExpressageProvincial provincialId) {
		this.provincialId = provincialId;
	}


	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "city_id")
	public ExpressageCity getCityId() {
		return cityId;
	}
	public void setCityId(ExpressageCity cityId) {
		this.cityId = cityId;
	}
	
	
	

	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "district_id")
	public ExpressageDistrict getDistrictId() {
		return districtId;
	}
	public void setDistrictId(ExpressageDistrict districtId) {
		this.districtId = districtId;
	}
	
	@Column(name = "address_id")
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	
	
	@Column(name = "introduce")
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	/*@Column(name = "describe")
	public String getDescribe() {
		return describe;
	}
	
	public void setDescribe(String describe) {
		this.describe = describe;
	}*/
	
	
	
}
