package com.cndatacom.rbac.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * ����: ExpressageOrder</br> 
 * ������com.cndatacom.rbac.pojo </br> 
 * ����: ����ʵ����</br>
 * �����汾�ţ�</br>
 * ������Ա�� ������</br>
 * ����ʱ�䣺 2016-9-30 
 * ��˾ ���������ֺ�����Ƽ����޹�˾(http://www.aleven.cn/)</br>
 */
@Entity
@Table(name = "expressage_order", catalog = "expressage")
public class ExpressageOrder implements java.io.Serializable {

	// Fields

	private String orderId;
	private String orderNo;//������
	private String packType;//�������
	private String packWeight;//��������
	private String jPhone;//�ļ����ֻ�
	private String jAddress;//�ļ��˵�ַ
	private String jName;//�ļ�������
	private String sPhone;//�ռ����ֻ�
	private String sAddress;//�ռ��˵�ַ
	private String sName;//�ռ�������
	private String courierId;//���Աid
	private String userId;//�û�id
	private String favorableId;//�Ż�ȯid
	private String gatherDate;//�ռ�ʱ��
	private Date createDate;//
	private Date orderDate;//
	private String status;//״̬1�����ӵ���2Ϊ�ѽӵ������գ�3Ϊ������ϣ������ۣ�4������ɣ�5�ϵ�
	private String type;//1Ϊ�����µ���2Ϊ����µ�
	private String payId;//֧��id
	private String orderNumber;//�˵���
	private String reservePrice;//Ԥ���۸�
	private String orderPrice;//ʵ�ʼ۸�
	private String productId;//������Ʒid
	private String integralNum;//�һ�����
	private String longitude;
	private String latitude;
	private String area;

	// Constructors

	/** default constructor */
	public ExpressageOrder() {
	}

	/** full constructor */
	public ExpressageOrder(String packType, String packWeight, Date createDate,
			String status) {
		this.packType = packType;
		this.packWeight = packWeight;
		this.createDate = createDate;
		this.status = status;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "order_id", unique = true, nullable = false)
	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name = "pack_type")
	public String getPackType() {
		return this.packType;
	}

	public void setPackType(String packType) {
		this.packType = packType;
	}

	@Column(name = "pack_weight")
	public String getPackWeight() {
		return this.packWeight;
	}

	public void setPackWeight(String packWeight) {
		this.packWeight = packWeight;
	}

	@Column(name = "create_date", length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "status")
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	@Column(name = "j_phone")
	public String getjPhone() {
		return jPhone;
	}

	public void setjPhone(String jPhone) {
		this.jPhone = jPhone;
	}
	
	
	@Column(name = "j_address")
	public String getjAddress() {
		return jAddress;
	}

	public void setjAddress(String jAddress) {
		this.jAddress = jAddress;
	}
	
	@Column(name = "j_name")
	public String getjName() {
		return jName;
	}
	
	public void setjName(String jName) {
		this.jName = jName;
	}
	
	@Column(name = "s_phone")
	public String getsPhone() {
		return sPhone;
	}

	public void setsPhone(String sPhone) {
		this.sPhone = sPhone;
	}
	
	@Column(name = "s_address")
	public String getsAddress() {
		return sAddress;
	}

	public void setsAddress(String sAddress) {
		this.sAddress = sAddress;
	}
	
	@Column(name = "s_name")
	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}
	
	@Column(name = "courier_id")
	public String getCourierId() {
		return courierId;
	}

	public void setCourierId(String courierId) {
		this.courierId = courierId;
	}
	
	@Column(name = "user_id")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name = "favorable_id")
	public String getFavorableId() {
		return favorableId;
	}

	public void setFavorableId(String favorableId) {
		this.favorableId = favorableId;
	}
	
	@Column(name = "gather_date")
	public String getGatherDate() {
		return gatherDate;
	}

	public void setGatherDate(String gatherDate) {
		this.gatherDate = gatherDate;
	}
	
	@Column(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "pay_id")
	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}
	
	@Column(name = "order_number")
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Column(name = "reserve_price")
	public String getReservePrice() {
		return reservePrice;
	}

	public void setReservePrice(String reservePrice) {
		this.reservePrice = reservePrice;
	}
	
	@Column(name = "order_price")
	public String getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}
	
	@Column(name = "longitude")
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	@Column(name = "latitude")
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	@Column(name = "product_id")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	@Column(name = "integral_num")
	public String getIntegralNum() {
		return integralNum;
	}

	public void setIntegralNum(String integralNum) {
		this.integralNum = integralNum;
	}
	
	@Column(name = "order_no")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Column(name = "order_date")
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	@Column(name = "area")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	
	
	
}