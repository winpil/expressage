package com.cndatacom.rbac.pojo;

import java.io.Serializable;
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
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "SYS_AUTHORITY")
public class SysAuthority implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * SpringSecurity中默认的角色/授权名前缀
	 */
	public static final String AUTHORITY_PREFIX = "ROLE_";

	private String authorityId;

	private Long authorityType;

	private String authorityUrl;

	private String authorityName;

	private String authorityNote;

	private Set<SysRole> sysRoles = new HashSet<SysRole>();

	private SysMenu sysMenu;

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name = "AUTHORITY_ID", unique = true, nullable = false, length = 32)
	public String getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(String authorityId) {
		if ("".equals(authorityId)) {
			authorityId = null;
		}
		this.authorityId = authorityId;
	}

	@Column(name = "AUTHORITY_NAME", nullable = false, unique = true)
	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	@Column(name = "AUTHORITY_TYPE", precision = 9, scale = 0)
	public Long getAuthorityType() {
		return authorityType;
	}

	public void setAuthorityType(Long authorityType) {
		this.authorityType = authorityType;
	}

	@Column(name = "AUTHORITY_URL")
	public String getAuthorityUrl() {
		return authorityUrl;
	}

	public void setAuthorityUrl(String authorityUrl) {
		this.authorityUrl = authorityUrl;
	}

	@Column(name = "AUTHORITY_NOTE")
	public String getAuthorityNote() {
		return authorityNote;
	}

	public void setAuthorityNote(String authorityNote) {
		this.authorityNote = authorityNote;
	}

	@ManyToMany(mappedBy = "authorities")
	@JoinTable(name = "SYS_ROLE_AUTHORITY", joinColumns = { @JoinColumn(name = "AUTHORITY_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	@Fetch(FetchMode.SUBSELECT)
	public Set<SysRole> getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(Set<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}

	public SysAuthority() {
	}

	@Transient
	public String getPrefixedName() {
		return AUTHORITY_PREFIX + authorityName;
	}

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "MENU_ID")
	public SysMenu getSysMenu() {
		return sysMenu;
	}

	public void setSysMenu(SysMenu sysMenu) {
		this.sysMenu = sysMenu;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE, false);
	}

	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof SysAuthority)) {
			return false;
		}

		SysAuthority authority = (SysAuthority) obj;

		return authority.authorityId.equals(this.authorityId);

	}

	public int hashCode() {
		if (this.authorityId == null) {
			return super.hashCode();
		} else {
			return 51 * this.authorityId.hashCode();
		}
	}
}
