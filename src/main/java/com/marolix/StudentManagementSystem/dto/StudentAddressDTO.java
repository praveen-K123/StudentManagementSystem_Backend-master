package com.marolix.StudentManagementSystem.dto;

import javax.validation.constraints.NotNull;

import com.marolix.StudentManagementSystem.entity.StudentAddressType;

public class StudentAddressDTO {
	private Integer addressId;
	@NotNull(message = "please provide addressLine1")
	//will accept alphanumberics and ,-
	
	private String addressLine1;
	//will accept alphanumberics and ,-
	private String addressLine2;
	//one word
	private String city;
	//only numbers max 6
	private String pincode;
	//only albabets each word should be separated by space
	private String state;
	//temper/permannet
	private StudentAddressType addressType;

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public StudentAddressType getAddressType() {
		return addressType;
	}

	public void setAddressType(StudentAddressType addressType) {
		this.addressType = addressType;
	}

}
