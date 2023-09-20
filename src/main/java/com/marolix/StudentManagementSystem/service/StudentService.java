package com.marolix.StudentManagementSystem.service;

import java.util.List;

import com.marolix.StudentManagementSystem.dto.StudentDTO;
import com.marolix.StudentManagementSystem.utility.StudentManagementException;

public interface StudentService {
	public StudentDTO registerNewStudent(StudentDTO dto);

	public StudentDTO searchStudentByPhoneNumber(String phoneNumber) throws StudentManagementException;

	public List<StudentDTO> filterByPhoneOrName(String poneNumber, String name) throws StudentManagementException;

	StudentDTO searchByUsername(String s) throws StudentManagementException;

	public void deleteStudentInfo(String username) throws StudentManagementException;

	public List<StudentDTO> filterByName(String name) throws StudentManagementException;

	public StudentDTO updateStudent(String oldPhoneNumber, String newPhoneNumber) throws StudentManagementException;
}
