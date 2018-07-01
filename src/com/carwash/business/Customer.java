package com.carwash.business;

import java.io.Serializable;
import java.util.List;

public class Customer implements Serializable{
	
    private String Email;
    private String Password;
    private String FirstName;
    private String LastName;  
    private String PhoneNumber;
    private int DoorNo;
    private String Street;
    private String City;
    private String Province;
    private String PostalCode;
    private String role;
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phone) {
		PhoneNumber = phone;
	}
	public int getDoorNo() {
		return DoorNo;
	}
	public void setDoorNo(int doorNo) {
		DoorNo = doorNo;
	}
	public String getStreet() {
		return Street;
	}
	public void setStreet(String street) {
		Street = street;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getProvince() {
		return Province;
	}
	public void setProvince(String province) {
		Province = province;
	}
	public String getPostalCode() {
		return PostalCode;
	}
	public void setPostalCode(String postalCode) {
		PostalCode = postalCode;
	}
    

}
