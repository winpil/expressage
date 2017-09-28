package com.cndatacom.rbac.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "expressage_logistics", catalog = "expressage")
public class ExpressageLogistics implements java.io.Serializable{
	
	private String logisticsId;//
	private String logisticsJc;//快递代号
	private String logisticsName;//快递名称
	private String logisticsOrder;//快递单号
	private String logisticsMessage;//提示信息
	private String logisticsStatus;//单号状态
	private String logisticsData;//跟踪数据
	private Date createDate;//查询时间
	
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "logistics_id", unique = true, nullable = false)
	public String getLogisticsId() {
		return logisticsId;
	}
	public void setLogisticsId(String logisticsId) {
		this.logisticsId = logisticsId;
	}
	
	@Column(name = "logistics_jc")
	public String getLogisticsJc() {
		return logisticsJc;
	}
	public void setLogisticsJc(String logisticsJc) {
		this.logisticsJc = logisticsJc;
	}
	
	@Column(name = "logistics_name")
	public String getLogisticsName() {
		return logisticsName;
	}
	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}
	
	@Column(name = "logistics_order")
	public String getLogisticsOrder() {
		return logisticsOrder;
	}
	public void setLogisticsOrder(String logisticsOrder) {
		this.logisticsOrder = logisticsOrder;
	}
	
	@Column(name = "logistics_message")
	public String getLogisticsMessage() {
		return logisticsMessage;
	}
	public void setLogisticsMessage(String logisticsMessage) {
		this.logisticsMessage = logisticsMessage;
	}
	
	@Column(name = "logistics_status")
	public String getLogisticsStatus() {
		return logisticsStatus;
	}
	public void setLogisticsStatus(String logisticsStatus) {
		this.logisticsStatus = logisticsStatus;
	}
	
	@Column(name = "logistics_data")
	public String getLogisticsData() {
		return logisticsData;
	}
	public void setLogisticsData(String logisticsData) {
		this.logisticsData = logisticsData;
	}
	
	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	
}
