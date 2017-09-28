package com.cndatacom.rbac.pojo;


public class OrderModel {

	// Fields

	private String orderId;
	private String packType;//包裹类别
	private String orderNo;//订单号
	private String packWeight;//包裹重量
	private String jPhone;//寄件人手机
	private String jAddress;//寄件人地址
	private String jName;//寄件人姓名
	private String sPhone;//收件人手机
	private String sAddress;//收件人地址
	private String sName;//收件人姓名
	private String courierId;//快递员id
	private String favorableId;//快递公司id
	private String favorableName;//公司
	private String userId;//用户id
	private String gatherDate;//收件时间
	private String createDate;//
	private String status;//状态1：待接单，2为已接单待揽收，3为揽收完毕，待评价，4订单完成，5废单
	private String type;//1
	private String payId;//支付id
	private String orderPrice;//实际价格
	
	private String productId;
	private String productName;
	private String img1;
	private String integral;
	private String reputation;
	private String orderDate;
	
	private CourierExpressageMode cem;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPackType() {
		return packType;
	}
	public void setPackType(String packType) {
		this.packType = packType;
	}
	public String getPackWeight() {
		return packWeight;
	}
	public void setPackWeight(String packWeight) {
		this.packWeight = packWeight;
	}
	public String getjPhone() {
		return jPhone;
	}
	public void setjPhone(String jPhone) {
		this.jPhone = jPhone;
	}
	public String getjAddress() {
		return jAddress;
	}
	public void setjAddress(String jAddress) {
		this.jAddress = jAddress;
	}
	public String getjName() {
		return jName;
	}
	public void setjName(String jName) {
		this.jName = jName;
	}
	public String getsPhone() {
		return sPhone;
	}
	public void setsPhone(String sPhone) {
		this.sPhone = sPhone;
	}
	public String getsAddress() {
		return sAddress;
	}
	public void setsAddress(String sAddress) {
		this.sAddress = sAddress;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getCourierId() {
		return courierId;
	}
	public void setCourierId(String courierId) {
		this.courierId = courierId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGatherDate() {
		return gatherDate;
	}
	public void setGatherDate(String gatherDate) {
		this.gatherDate = gatherDate;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPayId() {
		return payId;
	}
	public void setPayId(String payId) {
		this.payId = payId;
	}
	public String getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getFavorableId() {
		return favorableId;
	}
	public void setFavorableId(String favorableId) {
		this.favorableId = favorableId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getImg1() {
		return img1;
	}
	public void setImg1(String img1) {
		this.img1 = img1;
	}
	public String getIntegral() {
		return integral;
	}
	public void setIntegral(String integral) {
		this.integral = integral;
	}
	public String getReputation() {
		return reputation;
	}
	public void setReputation(String reputation) {
		this.reputation = reputation;
	}
	public CourierExpressageMode getCem() {
		return cem;
	}
	public void setCem(CourierExpressageMode cem) {
		this.cem = cem;
	}
	public String getFavorableName() {
		return favorableName;
	}
	public void setFavorableName(String favorableName) {
		this.favorableName = favorableName;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	
	
}