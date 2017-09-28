package com.cndatacom.rbac.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "expressage_note", catalog = "expressage")
public class ExpressageNote implements java.io.Serializable{
	
	private String noteId;
	private String phone;
	private String noteContent;
	private Date noteTime;
	private int status;
	private String orderNumber;
	private String courierId;

	public ExpressageNote() {
	}

	public ExpressageNote(String phone, String noteContent, Date noteTime,
			int status, String courierId,String orderNumber) {
		super();
		this.phone = phone;
		this.noteContent = noteContent;
		this.noteTime = noteTime;
		this.status = status;
		this.courierId = courierId;
		this.orderNumber = orderNumber;
	}


	@Column(name="order_number")
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Column(name="courier_id")
	public String getCourierId() {
		return courierId;
	}
	public void setCourierId(String courierId) {
		this.courierId = courierId;
	}
	
	@GenericGenerator(name="generator",strategy="uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name="note_id",unique=true,nullable=false,length=128)
	public String getNoteId() {
		return noteId;
	}
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}
	
	@Column(name="phone",length=12)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name="note_content")
	public String getNoteContent() {
		return noteContent;
	}
	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="note_time")
	public Date getNoteTime() {
		return noteTime;
	}
	public void setNoteTime(Date noteTime) {
		this.noteTime = noteTime;
	}
	
	@Column(name="status")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
