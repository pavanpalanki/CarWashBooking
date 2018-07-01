package com.carwash.business;

public class Wash {
	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	private int washId;
	private String oEmail;
	private String ownerName;
	private String washType;
	private float washPrice;
	private String date;
	private String status;
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	private int slotId;
	private String slotTime;

	public int getSlotId() {
		return slotId;
	}

	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}

	public String getSlotTime() {
		return slotTime;
	}

	public void setSlotTime(String slotTime) {
		this.slotTime = slotTime;
	}

	public float getWashPrice() {
		return washPrice;
	}

	public void setWashPrice(float washPrice) {
		this.washPrice = washPrice;
	}

	public int getWashId() {
		return washId;
	}

	public void setWashId(int washId) {
		this.washId = washId;
	}

	public String getoEmail() {
		return oEmail;
	}

	public void setoEmail(String oEmail) {
		this.oEmail = oEmail;
	}

	public String getWashType() {
		return washType;
	}

	public void setWashType(String washType) {
		this.washType = washType;
	}

}
