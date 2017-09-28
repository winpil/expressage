package com.cndatacom.common.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * �˵����ؽڵ���
 * @author yab
 */
public class CheckNode {
	
	private String id;
	
	private String text;
	
	private boolean checked = false;
	
	private String cls;
	
	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	private List<CheckNode> children = new ArrayList<CheckNode>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public List<CheckNode> getChildren() {
		return children;
	}

	public void setChildren(List<CheckNode> children) {
		this.children = children;
	}

	public boolean isLeaf() {
        return this.children.size() == 0;
    }
	
}
