package com.cndatacom.rbac.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "expressage_note_temp", catalog = "expressage")
public class ExpressageNoteTemp implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String tempId;
	private String tempName;
	private String tempContent;


	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name="temp_id",unique=true,nullable=false,length=128)
	public String getTempId() {
		return tempId;
	}
	public void setTempId(String tempId) {
		this.tempId = tempId;
	}
	
	@Column(name = "temp_name")
	public String getTempName() {
		return tempName;
	}
	public void setTempName(String tempName) {
		this.tempName = tempName;
	}
	
	@Column(name = "temp_content")
	public String getTempContent() {
		return tempContent;
	}
	public void setTempContent(String tempContent) {
		this.tempContent = tempContent;
	}
	
	
}
