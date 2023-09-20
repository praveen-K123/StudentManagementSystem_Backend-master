package com.marolix.StudentManagementSystem.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "student")
@NamedQuery(name="StudentInfo.filterStudentName",query="select s from StudentInfo s where s.studentName=?1")
public class StudentInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "std_id", nullable = false)
	private Integer studentId;
	@Column(name = "std_name", nullable = false)
	private String studentName;
	@Column(name = "std_father_name", nullable = false)
	private String studentFatherName;
	@Column(name = "std_grade", nullable = false)
	private Character grade;
	@Column(name = "std_phone_number", nullable = false, unique = true)
	private String phoneNumber;
	@Enumerated(EnumType.STRING)
//	@Enumerated(EnumType.ORDINAL)
	private AdmissionType type;
//	@Lob
//	private byte[] photo;
//	@Temporal(TemporalType.DATE)
//	private Date joiningdate;
	private LocalDate joiningDate;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "username")
	private StudentLoginDetails login;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "std_id")
	private List<StudentAddress> addresses;

	public List<StudentAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<StudentAddress> addresses) {
		this.addresses = addresses;
	}

	public StudentLoginDetails getLogin() {
		return login;
	}

	public void setLogin(StudentLoginDetails login) {
		this.login = login;
	}

	public AdmissionType getType() {
		return type;
	}

	public void setType(AdmissionType type) {
		this.type = type;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentFatherName() {
		return studentFatherName;
	}

	public void setStudentFatherName(String studentFatherName) {
		this.studentFatherName = studentFatherName;
	}

	public Character getGrade() {
		return grade;
	}

	public void setGrade(Character grade) {
		this.grade = grade;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
