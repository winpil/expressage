package com.cndatacom.rbac.pojo;


public class WeixinOrder {
	private String packType;//包裹类别
	private String packWeight;//包裹重量
	private String jId;//寄件人id
	private String sId;//收件人id
	private String courierId;//快递员id
	private String userId;//用户id
	private String favorableId;//优惠券id
	private String gatherDate;//收件时间
	private String type;//1为定向下单，2为快快下单
	private String payId;//支付id
	private String reservePrice;//预定价格
	private String orderPrice;//实际价格
	private String latitude;//
	private String longitude;
	private String oId;
	
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
	public String getjId() {
		return jId;
	}
	public void setjId(String jId) {
		this.jId = jId;
	}
	public String getsId() {
		return sId;
	}
	public void setsId(String sId) {
		this.sId = sId;
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
	public String getFavorableId() {
		return favorableId;
	}
	public void setFavorableId(String favorableId) {
		this.favorableId = favorableId;
	}
	public String getGatherDate() {
		return gatherDate;
	}
	public void setGatherDate(String gatherDate) {
		this.gatherDate = gatherDate;
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
	public String getReservePrice() {
		return reservePrice;
	}
	public void setReservePrice(String reservePrice) {
		this.reservePrice = reservePrice;
	}
	public String getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getoId() {
		return oId;
	}
	public void setoId(String oId) {
		this.oId = oId;
	}
	
	
}
