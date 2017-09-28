package com.cndatacom.rbac.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * 类名: ExpressageCourier</br> 
 * 包名：com.cndatacom.rbac.pojo </br> 
 * 描述: 快递员实体类</br>
 * 发布版本号：</br>
 * 开发人员： 朱恋青</br>
 * 创建时间： 2016-9-30 
 * 公司 ：广州亚乐恒网络科技有限公司(http://www.aleven.cn/)</br>
 */
@Entity
@Table(name = "expressage_courier", catalog = "expressage")
public class ExpressageCourier implements java.io.Serializable {

	// Fields

	private String courierId;
	private String phone;
	private String phoneNumber;
	private String password;
	private String isPayp;
	private String payPassword;
	private String courierName;
	private String avatar;
	private String bankCard;
	private String gender;
	private String companyId;
	private String status;
	private String rank;
	private String longitude;
	private String latitude;
	private String isWork;
	private String referrerId;
	private String branchId;
	private String balance;
	private Date llDate;
	private String clientNumber;
	private String clientPwd;
	private String icode;
	private String img1;
	private String img2;
	private String img3;
	private String isAuth;
	private String area;
	// Constructors

	/** default constructor */
	public ExpressageCourier() {
	}

	/** full constructor */
	public ExpressageCourier(String phone, String password, String isPayp,
			String payPassword, String courierName, String avatar,
			String bankCard, String gender, String companyId, String rank) {
		this.phone = phone;
		this.password = password;
		this.isPayp = isPayp;
		this.payPassword = payPassword;
		this.courierName = courierName;
		this.avatar = avatar;
		this.bankCard = bankCard;
		this.gender = gender;
		this.companyId = companyId;
		this.rank = rank;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "courier_id", unique = true, nullable = false)
	public String getCourierId() {
		return this.courierId;
	}

	public void setCourierId(String courierId) {
		this.courierId = courierId;
	}

	@Column(name = "phone")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name = "phone_number")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Column(name = "password")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "is_payp")
	public String getIsPayp() {
		return this.isPayp;
	}

	public void setIsPayp(String isPayp) {
		this.isPayp = isPayp;
	}

	@Column(name = "pay_password")
	public String getPayPassword() {
		return this.payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	@Column(name = "courier_name")
	public String getCourierName() {
		return this.courierName;
	}

	public void setCourierName(String courierName) {
		this.courierName = courierName;
	}

	@Column(name = "avatar")
	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Column(name = "bank_card")
	public String getBankCard() {
		return this.bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	@Column(name = "gender")
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "company_id")
	public String getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Column(name = "rank")
	public String getRank() {
		return this.rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
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
	
	@Column(name = "is_work")
	public String getIsWork() {
		return isWork;
	}

	public void setIsWork(String isWork) {
		this.isWork = isWork;
	}
	
	@Column(name = "referrer_id")
	public String getReferrerId() {
		return referrerId;
	}

	public void setReferrerId(String referrerId) {
		this.referrerId = referrerId;
	}
	
	@Column(name = "branch_id")
	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	
	@Column(name = "balance")
	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	@Column(name = "ll_date")
	public Date getLlDate() {
		return llDate;
	}

	public void setLlDate(Date llDate) {
		this.llDate = llDate;
	}
	@Column(name = "client_number")
	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}
	
	@Column(name = "client_pwd")
	public String getClientPwd() {
		return clientPwd;
	}

	public void setClientPwd(String clientPwd) {
		this.clientPwd = clientPwd;
	}
	
	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "icode")
	public String getIcode() {
		return icode;
	}

	public void setIcode(String icode) {
		this.icode = icode;
	}
	
	@Column(name = "img1")
	public String getImg1() {
		return img1;
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	}
	
	@Column(name = "img2")
	public String getImg2() {
		return img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	}
	
	@Column(name = "img3")
	public String getImg3() {
		return img3;
	}

	public void setImg3(String img3) {
		this.img3 = img3;
	}
	
	@Column(name = "is_auth")
	public String getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(String isAuth) {
		this.isAuth = isAuth;
	}
	
	@Column(name = "area")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
}