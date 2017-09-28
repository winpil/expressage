package com.cndatacom.rbac.pojo;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

/**
 * 系统角色对应的实体类
 * @author yab
 *
 */
@Entity
@Table(name = "SYS_ROLE")
public class SysRole implements Serializable {
	private static final long serialVersionUID = 1L;
	private String roleId;
	private String roleName;
	private String roleNote;
	private Set<SysUser> users = new HashSet<SysUser>();
	private Set<SysAuthority> authorities = new HashSet<SysAuthority>();
	
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name="ROLE_ID",unique=true, nullable=false, length=32)
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		if ("".equals(roleId)) {
			roleId = null;
		}
		this.roleId = roleId;
	}

	@ManyToMany(mappedBy="roles")
	@JoinTable(name="SYS_USER_ROLE",joinColumns={@JoinColumn(name="ROLE_ID")},inverseJoinColumns={@JoinColumn(name="USER_ID")})
	@Fetch(FetchMode.SUBSELECT)
	public Set<SysUser> getUsers() {
		return users;
	}

	public void setUsers(Set<SysUser> users) {
		this.users = users;
	}

	@Column(name="ROLE_NOTE")
	public String getRoleNote() {
		return roleNote;
	}

	public void setRoleNote(String roleNote) {
		this.roleNote = roleNote;
	}
	
	@Column(nullable = false, unique = true,name="ROLE_NAME")
	public String getRoleName() {
		return roleName;
	}



	public SysRole() {
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@ManyToMany
	@JoinTable(name = "SYS_ROLE_AUTHORITY", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTHORITY_ID") })
	@OrderBy("authorityId asc")
	@Fetch(FetchMode.SUBSELECT)
	public Set<SysAuthority> getAuthorities(){
		return authorities;
	}

	public void setAuthorities(Set<SysAuthority> authorities) {
		this.authorities = authorities;
	}
	
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SysRole)) {
			return false;
		}
		SysRole role = (SysRole) obj;
		
		return role.roleId.equals(this.roleId);
		
	}

	public int hashCode() {
		if (this.roleId == null) {
            return super.hashCode();
        } else {
            return 65 * this.roleId.hashCode();
        }
	}
	
	public String toString() {
		return new ReflectionToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE){
			protected boolean accept(Field field) {
				return super.accept(field) && !"users".equals(field.getName())
				 && !"authorities".equals(field.getName());
			}
		}.toString();
	}
}
