package com.marolix.StudentManagementSystem.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marolix.StudentManagementSystem.dto.StudentAddressDTO;
import com.marolix.StudentManagementSystem.dto.StudentDTO;
import com.marolix.StudentManagementSystem.dto.StudentLoginDetailsDTO;
import com.marolix.StudentManagementSystem.entity.AdmissionType;
import com.marolix.StudentManagementSystem.entity.StudentAddressType;

import com.marolix.StudentManagementSystem.service.StudentService;
import com.marolix.StudentManagementSystem.utility.StudentManagementException;

class Login {
	String username;
	String password;
}

@Controller(value = "studentController")
@ResponseBody
@Validated
@RequestMapping(value = "controller")
public class StudentControllerImpl {
	@Autowired
	private StudentService studentService;

	@PostMapping(value = "/register")
	public ResponseEntity<StudentDTO> registerNewStudent(@Valid @RequestBody StudentDTO dto) {

		return new ResponseEntity<StudentDTO>(studentService.registerNewStudent(dto), HttpStatus.CREATED);
	}

	@GetMapping(value = "/get")
//	public ResponseEntity<StudentDTO> searchStudentByPhoneNumber(@PathVariable String phoneNumber) {
	public ResponseEntity<?> searchStudentByPhoneNumber(@RequestParam("phone") String phoneNumber)
			throws StudentManagementException {
		StudentDTO d = null;

		d = studentService.searchStudentByPhoneNumber(phoneNumber);

		return new ResponseEntity<StudentDTO>(d, HttpStatus.OK);
	}

	public void searchStudentByPhoneNumberOrName() throws StudentManagementException {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter phone number");
		String phone = sc.next();
		System.out.println("enter name");
		String name = sc.next();

		List<StudentDTO> s = studentService.filterByPhoneOrName(phone, name);
		System.out.println(s);
	}

	public void searchByUserName() throws StudentManagementException {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter user name");

		StudentDTO list = studentService.searchByUsername(sc.next());

	}

	@DeleteMapping(value = "/delete/{username}")
	public ResponseEntity<String> deleteByUserName(@PathVariable String username) {

		try {
			studentService.deleteStudentInfo(username);
		} catch (StudentManagementException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("deleted successfully", HttpStatus.OK);
	}

	public void serachByName() {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter student name");
		try {
			List<StudentDTO> dto = studentService.filterByName(sc.next());
			for (StudentDTO studentDTO : dto) {
				System.out.println(studentDTO);
			}
		} catch (StudentManagementException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

//	@PutMapping(value = "/update/old/{phoneNumber}/new/{newPhoneNumber}")
	@PutMapping(value = "/update/old/{phoneNumber}")
	public ResponseEntity<?> updateStudentDetails(@PathVariable String phoneNumber, @RequestBody String newPhoneNumber)
			throws StudentManagementException {
		StudentDTO dto = null;

		dto = studentService.updateStudent(phoneNumber, newPhoneNumber);

		return new ResponseEntity<StudentDTO>(dto, HttpStatus.OK);
	}

	@PostMapping(value = "/dummyget")
	public String thisIsDummyValidLogin(@RequestBody Login username) {
		return "dummy login called";
	}
}
