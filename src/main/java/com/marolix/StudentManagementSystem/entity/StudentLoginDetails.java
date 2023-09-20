package com.marolix.StudentManagementSystem.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student_login_details")
public class StudentLoginDetails {
	@Id
	private String username;
	private String password;
	// private Integer studentId;


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

	public StudentLoginDetails() {
		super();
		// TODO Auto-generated constructor stub
	}




}
