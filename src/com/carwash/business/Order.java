package com.carwash.business;

public class Order {
	String id;
	String carWash;
	String date;
	String washType;
	String washTime;
	String ownerEmail;
	String customerEmail;
	Boolean cancel;
	public Boolean getCancel() {
		return cancel;
	}
	public void setCancel(Boolean cancel) {
		this.cancel = cancel;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCarWash() {
		return carWash;
	}
	public void setCarWash(String carWash) {
		this.carWash = carWash;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getWashType() {
		return washType;
	}
	public void setWashType(String washType) {
		this.washType = washType;
	}
	public String getWashTime() {
		return washTime;
	}
	public void setWashTime(String washTime) {
		this.washTime = washTime;
	}
	public String getOwnerEmail() {
		return ownerEmail;
	}
	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
}
