package com.cndatacom.rbac.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="expressage_token"
    ,catalog="expressage"
)
public class ExpressageToken implements java.io.Serializable{
	private static final long serialVersionUID = -5777836269683894225L;
	
	private String tokenId;
	private String userId;
	private String tokenName;
	private Date createDate;
	private String type;
	
	@GenericGenerator(name="generator", strategy="uuid")@Id @GeneratedValue(generator="generator")
    
    @Column(name="token_id", unique=true, nullable=false, length=128)
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	
	@Column(name="user_id")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name="token_name")
	public String getTokenName() {
		return tokenName;
	}
	
	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}
	
	@Column(name="create_date")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
