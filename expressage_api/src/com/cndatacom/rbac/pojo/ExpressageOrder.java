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
 * 类名: ExpressageOrder</br> 
 * 包名：com.cndatacom.rbac.pojo </br> 
 * 描述: 订单实体类</br>
 * 发布版本号：</br>
 * 开发人员： 朱恋青</br>
 * 创建时间： 2016-9-30 
 * 公司 ：广州亚乐恒网络科技有限公司(http://www.aleven.cn/)</br>
 */
@Entity
@Table(name = "expressage_order", catalog = "expressage")
public class ExpressageOrder implements java.io.Serializable {

	// Fields

	private String orderId;
	private String orderNo;//订单号
	private String packType;//包裹类别
	private String packWeight;//包裹重量
	private String jPhone;//寄件人手机
	private String jAddress;//寄件人地址
	private String jName;//寄件人姓名
	private String sPhone;//收件人手机
	private String sAddress;//收件人地址
	private String sName;//收件人姓名
	private String courierId;//快递员id
	private String userId;//用户id
	private String favorableId;//优惠券id
	private String gatherDate;//收件时间
	private Date createDate;//
	private Date orderDate;//
	private String status;//状态1：待接单，2为已接单待揽收，3为揽收完毕，待评价，4订单完成，5废单
	private String type;//1为定向下单，2为快快下单
	private String payId;//支付id
	private String orderNumber;//运单号
	private String reservePrice;//预定价格
	private String orderPrice;//实际价格
	private String productId;//积分商品id
	private String integralNum;//兑换积分
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