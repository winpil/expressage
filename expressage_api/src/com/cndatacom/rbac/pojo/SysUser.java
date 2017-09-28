package com.cndatacom.rbac.pojo;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

@Table(name = "SYS_USER")
@Entity(name = "SecuritySysUser")
public class SysUser implements UserDetails {

	private static final long serialVersionUID = 1L;

	private static final Long ACCOUNT_ENABLED = 1L;

	private static final Long SYS_USER_ENABLED = 1L;

	private String userId;

	private String username;

	private String password;

	private String name;

	private String email;

	private String phone;

	private String mobilePhone;

	private Date expirationDate;

	private Date credentialsExpirationDate;

	private Long enableStatus;

	private Long accountStatus;

	private String manufacId;
	
	private Date createdtime;

	private SysGroup sysGroup = new SysGroup();

	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "GROUP_ID")
	public SysGroup getSysGroup() {
		return sysGroup;
	}

	public void setSysGroup(SysGroup sysGroup) {
		this.sysGroup = sysGroup;
	}

	@Column(name = "NAME", length = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "EMAIL", length = 50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "PHONE", length = 20)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "MOBILE_PHONE", length = 20)
	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EXPIRATION_DATE", length = 7)
	@JSON(format = "yyyy-MM-dd")
	public Date getExpirationDate() {
		return expirationDate;
	}
	
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATEDTIME", length=23)
	public Date getCreatedtime() {
		return createdtime;
	}

	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Column(name = "ENABLE_STATUS", precision = 9, scale = 0)
	public Long getEnableStatus() {
		return enableStatus;
	}

	public void setEnableStatus(Long enableStatus) {
		this.enableStatus = enableStatus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREDENTIALS_EXPIRATION_DATE", length = 7)
	public Date getCredentialsExpirationDate() {
		return credentialsExpirationDate;
	}

	public void setCredentialsExpirationDate(Date credentialsExpirationDate) {
		this.credentialsExpirationDate = credentialsExpirationDate;
	}

	@Column(name = "ACCOUNT_STATUS", precision = 9, scale = 0)
	public Long getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(Long accountStatus) {
		this.accountStatus = accountStatus;
	}

	private Set<SysRole> roles = new HashSet<SysRole>();

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name = "USER_ID", unique = true, nullable = false, length = 32)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		if ("".equals(userId)) {
			userId = null;
		}
		this.userId = userId;
	}

	@ManyToMany
	@JoinTable(name = "SYS_USER_ROLE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	public Set<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<SysRole> roles) {
		this.roles = roles;
	}

	@Column(name = "PASSWORD", length = 40)
	public String getPassword() {
		return password;
	}

	@Column(name = "USERNAME", length = 100)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "MANUFAC_ID", length = 32)
	public String getManufacId() {
		return manufacId;
	}

	public void setManufacId(String manufacId) {
		this.manufacId = manufacId;
	}


	@Transient
	public boolean isAccountNonExpired() {
		return new Date().before(this.expirationDate);
	}

	@Transient
	public boolean isAccountNonLocked() {
		return ACCOUNT_ENABLED.equals(this.accountStatus);
	}

	@Transient
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Transient
	public boolean isEnabled() {
		return SYS_USER_ENABLED.equals(this.enableStatus);
	}

	@Transient
	public Collection<GrantedAuthority> getAuthorities() {

		Set<GrantedAuthority> grantAuthorities = new HashSet<GrantedAuthority>();

		GrantedAuthority grantedAuthority = null;

		for (SysRole sysRole : roles) {

			Set<SysAuthority> authorities = sysRole.getAuthorities();

			for (SysAuthority sysAuthority : authorities) {
				grantedAuthority = new GrantedAuthorityImpl(sysAuthority
						.getPrefixedName());

				grantAuthorities.add(grantedAuthority);
			}

		}

		return grantAuthorities;
	}
	
	

	public String toString() {
		return new ReflectionToStringBuilder(this,
				ToStringStyle.SHORT_PREFIX_STYLE) {
			protected boolean accept(Field field) {
				return super.accept(field)
						&& !"password".equals(field.getName())
						&& !"roles".equals(field.getName())
						&& !"sysGroup".equals(field.getName());
			}
		}.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof SysUser)) {
			return false;
		}
		SysUser user = (SysUser) obj;
		return user.getUserId().equals(userId)
				&& user.getUsername().equals(username);
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = result * 31 + (userId==null?0:userId.hashCode());
		result = result * 31 + (username == null ? 0 : username.hashCode());
		return result;
	}

}
