package com.cndatacom.rbac.pojo;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;



/**
 * 组织架构实体类
 * 
 * @author yab
 * 
 */
@Entity
@Table(name = "SYS_GROUP")
public class SysGroup implements Serializable {
	private static final long serialVersionUID = 1935458542426876649L;

	private String groupId;

	private String groupName;

	private Long orderId;

	private String groupNote;

	private Long groupType;


	private Set<SysGroup> children = new HashSet<SysGroup>();

	private SysGroup parent;

	private Set<SysUser> users = new HashSet<SysUser>();

	@OneToMany(cascade = { CascadeType.REMOVE, CascadeType.REFRESH })
	@JoinColumn(name = ("GROUP_ID"))
	@OrderBy("userId asc")
	public Set<SysUser> getUsers() {
		return users;
	}

	public void setUsers(Set<SysUser> users) {
		this.users = users;
	}

	@OneToMany(cascade = { CascadeType.REMOVE, CascadeType.REFRESH })
	@JoinColumn(name = "PARENT_ID")
	@OrderBy("orderId asc")
	@Fetch(FetchMode.SUBSELECT)
	public Set<SysGroup> getChildren() {
		return children;
	}

	public void setChildren(Set<SysGroup> children) {
		this.children = children;
	}

	@ManyToOne
	@JoinColumn(name = "PARENT_ID")
	public SysGroup getParent() {
		return parent;
	}

	public void setParent(SysGroup parent) {
		this.parent = parent;
	}

	public SysGroup() {
	}

	public SysGroup(String groupId) {
		this.groupId = groupId;
	}

	public SysGroup(String groupId, String groupName, Long orderId,
			String groupNote) {
		this.groupId = groupId;
		this.groupName = groupName;
		this.orderId = orderId;
		this.groupNote = groupNote;
	}

	@Id
	@Column(name = "GROUP_ID",unique=true, nullable=false, length=32)
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		if ("".equals(groupId)) {
			groupId = null;
		}
		this.groupId = groupId;
	}

	@Column(name = "GROUP_NAME", length = 30)
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "ORDER_ID", precision = 9, scale = 0)
	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	@Column(name = "GROUP_NOTE")
	public String getGroupNote() {
		return this.groupNote;
	}

	@Column(name = "GROUP_TYPE")
	public Long getGroupType() {
		return groupType;
	}

	public void setGroupType(Long groupType) {
		this.groupType = groupType;
	}

	public void setGroupNote(String groupNote) {
		this.groupNote = groupNote;
	}

	@Transient
	public boolean isLeaf() {
		return this.children.size() == 0;
	}

	@Transient
	public boolean isRoot() {
		return getParent() == null;
	}



	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof SysGroup)) {
			return false;
		}

		SysGroup group = (SysGroup) obj;

		return group.groupId.equals(this.groupId);

	}

	public int hashCode() {
		if (this.groupId == null) {
			return super.hashCode();
		} else {
			return 71 * this.groupId.hashCode();
		}
	}

	public String toString() {
		return new ReflectionToStringBuilder(this,
				ToStringStyle.SHORT_PREFIX_STYLE) {
			protected boolean accept(Field field) {
				return super.accept(field)
						&& !"children".equals(field.getName())
						&& !"users".equals(field.getName())
						&& !"parent".equals(field.getName())
						&& !"spInfo".equals(field.getName());
			}
		}.toString();
	}

}