package com.marolix.StudentManagementSystem.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.marolix.StudentManagementSystem.entity.AdmissionType;

public class StudentDTO {
	private Integer studentId;
	@NotNull(message = "please provide student name")
	@Pattern(regexp = "[a-zA-Z]{3,}", message = "please provide a valid name")
	//can have multiple words each word should be separated by space
	private String studentName;
	//can have multiple words each word should be separated by space
	private String fatherName;
//change to string object
	//romans IIIVX
	//VIII
	private Character grade;

	@NotNull(message = "please provide phone number")
	@Pattern(regexp = "[4-9][0-9]{9}", message = "please provide a valid phone number")
	private String poneNumber;
//present date or future 
	private LocalDate joiningDate;
	//iit/medicon
	private AdmissionType type;
	//validate again use appropriate annotation
	private StudentLoginDetailsDTO loginDTO;
	@Valid
	@NotNull(message = "please provide address")
	private List<StudentAddressDTO> addressDTO;

	public StudentDTO(Integer studentId, String studentName, String fatherName, Character grade, String poneNumber,
			LocalDate joiningDate, AdmissionType type, StudentLoginDetailsDTO loginDTO) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.fatherName = fatherName;
		this.grade = grade;
		this.poneNumber = poneNumber;
		this.joiningDate = joiningDate;
		this.type = type;
		this.loginDTO = loginDTO;
	}

	public StudentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentLoginDetailsDTO getLoginDTO() {
		return loginDTO;
	}

	public List<StudentAddressDTO> getAddressDTO() {
		return addressDTO;
	}

	public void setAddressDTO(List<StudentAddressDTO> addressDTO) {
		this.addressDTO = addressDTO;
	}

	public void setLoginDTO(StudentLoginDetailsDTO loginDTO) {
		this.loginDTO = loginDTO;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	public AdmissionType getType() {
		return type;
	}

	public void setType(AdmissionType type) {
		this.type = type;
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

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public Character getGrade() {
		return grade;
	}

	public void setGrade(Character grade) {
		this.grade = grade;
	}

	public String getPoneNumber() {
		return poneNumber;
	}

	public void setPoneNumber(String poneNumber) {
		this.poneNumber = poneNumber;
	}

	@Override
	public String toString() {
		return "StudentDTO [studentId=" + studentId + ", studentName=" + studentName + ", fatherName=" + fatherName
				+ ", grade=" + grade + ", poneNumber=" + poneNumber + ", joiningDate=" + joiningDate + ", type=" + type
				+ ", loginDTO=" + loginDTO + "]";
	}

}
