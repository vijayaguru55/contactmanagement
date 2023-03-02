package com.contacts.dto;

public class Contact {
	private String name;
	private long mobileNumber;

	public Contact(String name, long mobileNumber) {
		this.name = name;
		this.mobileNumber = mobileNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

}
