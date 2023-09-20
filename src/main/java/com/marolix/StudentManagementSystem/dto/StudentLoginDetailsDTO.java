package com.marolix.StudentManagementSystem.dto;

public class StudentLoginDetailsDTO {
	private String username;
	//min 4 max 8
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
