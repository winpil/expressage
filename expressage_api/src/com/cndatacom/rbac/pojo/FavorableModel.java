package com.cndatacom.rbac.pojo;

public class FavorableModel {


	private String favorableId;
	private String favorableName;
	private String userId;
	private String status;
	private String createDate;
	private String pastDue;

	
	public String getFavorableId() {
		return this.favorableId;
	}

	public void setFavorableId(String favorableId) {
		this.favorableId = favorableId;
	}

	public String getFavorableName() {
		return this.favorableName;
	}

	public void setFavorableName(String favorableName) {
		this.favorableName = favorableName;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getPastDue() {
		return pastDue;
	}

	public void setPastDue(String pastDue) {
		this.pastDue = pastDue;
	}


}