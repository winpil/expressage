package com.cndatacom.rbac.pojo;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

/**
 * 系统菜单 实体类
 * @author yab
 */
@Entity
@Table(name = "SYS_MENU")
public class SysMenu implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String menuDescn;
	private String iconCls;
	private String menuName;
	private String qtip;
	private Long theSort;
	private String url;
	private boolean checked = false;
	private SysMenu parent;//父菜单
	private Set<SysMenu> subMenus = new HashSet<SysMenu>();//子菜单
	private Set<SysAuthority> authorities = new HashSet<SysAuthority>();
	
	@Transient
	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}


	public SysMenu() {
	}

	public SysMenu(String id) {
		this.id = id;
	}

	public SysMenu(String id, String menuDescn, String iconCls,
			String menuName, String qtip, Long theSort, String url) {
		this.id = id;
		this.menuDescn = menuDescn;
		this.iconCls = iconCls;
		this.menuName = menuName;
		this.qtip = qtip;
		this.theSort = theSort;
		this.url = url;
	}

	@Id
	@Column(name = "ID", unique=true, nullable=false, length=32)
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		if ("".equals(id)) {
			id = null;
		}
		this.id = id;
	}

	@Column(name = "MENU_DESCN", length = 200)
	public String getMenuDescn() {
		return this.menuDescn;
	}

	public void setMenuDescn(String menuDescn) {
		this.menuDescn = menuDescn;
	}

	@Column(name = "ICON_CLS", length = 50)
	public String getIconCls() {
		return this.iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	@Column(name = "MENU_NAME", length = 50)
	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Column(name = "QTIP", length = 50)
	public String getQtip() {
		return this.qtip;
	}

	public void setQtip(String qtip) {
		this.qtip = qtip;
	}

	@Column(name = "THE_SORT", precision = 9, scale = 0)
	public Long getTheSort() {
		return this.theSort;
	}

	public void setTheSort(Long theSort) {
		this.theSort = theSort;
	}

	@Column(name = "URL", length = 512)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@OneToMany(cascade={CascadeType.REMOVE,CascadeType.REFRESH})
	@JoinColumn(name="PARENT_ID")
	@OrderBy("theSort asc,id asc")
	@Fetch(FetchMode.SUBSELECT)
	@JSON(serialize=false)
	public Set<SysMenu> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(Set<SysMenu> subMenus) {
		this.subMenus = subMenus;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PARENT_ID")
	@JSON(serialize=false)
	public SysMenu getParent() {
		return parent;
	}

	public void setParent(SysMenu parent) {
		this.parent = parent;
	}
	
	@Transient
	public String getText() {
        return this.menuName;
    }
	
	@Transient
    public boolean isRoot() {
        return getParent() == null;
    }

	@Transient
    public boolean isLeaf() {
        return this.subMenus.size() == 0;
    }
	
	@Transient
	public Set<SysMenu> getChildren() {
	        return subMenus;
	}
	
	@OneToMany(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="MENU_ID")
	@JSON(serialize=false)
	@Fetch(FetchMode.SUBSELECT)
	public Set<SysAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<SysAuthority> authorities) {
		this.authorities = authorities;
	}

	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof SysMenu)) {
			return false;
		}

		SysMenu menu = (SysMenu) obj;
		
		return menu.id.equals(this.id);
		
	}

	public int hashCode() {
		if(this.id  == null) {
			return super.hashCode();
		}else{
			return 31 * this.id.hashCode();
		}
	}
	
	public String toString() {
		return new ReflectionToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE){
			protected boolean accept(Field field) {
				return super.accept(field) && 
				!"subMenus".equals(field.getName()) && !"authorities".equals(field.getName()) && 
						!"parent".equals(field.getName());
			}
		}.toString();
	}
}