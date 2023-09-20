package com.marolix.StudentManagementSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.marolix.StudentManagementSystem.dto.StudentAddressDTO;
import com.marolix.StudentManagementSystem.dto.StudentDTO;
import com.marolix.StudentManagementSystem.dto.StudentLoginDetailsDTO;
import com.marolix.StudentManagementSystem.entity.StudentAddress;
import com.marolix.StudentManagementSystem.entity.StudentInfo;
import com.marolix.StudentManagementSystem.entity.StudentLoginDetails;
import com.marolix.StudentManagementSystem.repository.StudentAddressRepository;
import com.marolix.StudentManagementSystem.repository.StudentLoginRepository;
import com.marolix.StudentManagementSystem.repository.StudentRepository;
import com.marolix.StudentManagementSystem.utility.StudentManagementException;

//@Component

//@Controller
//REpository
@Service(value = "studentService")
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private StudentLoginRepository studentLoginRepository;
	@Autowired
	private StudentAddressRepository studentAddressRepository;

	@Override
	public StudentDTO registerNewStudent(StudentDTO dto) {
		System.out.println("in service");
		String fNme = dto.getFatherName();

		StudentInfo newRow = new StudentInfo();
		newRow.setStudentId(dto.getStudentId());
		newRow.setStudentFatherName(fNme);
		newRow.setStudentName(dto.getStudentName());
		newRow.setPhoneNumber(dto.getPoneNumber());
		newRow.setGrade(dto.getGrade());
		newRow.setJoiningDate(dto.getJoiningDate());
		newRow.setType(dto.getType());
		String userName = dto.getStudentName().substring(0, 3) + dto.getPoneNumber().substring(3, 7) + dto.getGrade();

		// setting login credentials
		StudentLoginDetails newCredentials = new StudentLoginDetails();
		newCredentials.setUsername(userName);
		newCredentials.setPassword(dto.getLoginDTO().getPassword());
		newRow.setLogin(newCredentials);
		// set address details

		List<StudentAddress> list = dto.getAddressDTO().stream().map(p -> {
			StudentAddress s = new StudentAddress();
			s.setAddressLine1(p.getAddressLine1());
			s.setAddressLine2(p.getAddressLine2());
			s.setAddressType(p.getAddressType());
			s.setCity(p.getCity());
			s.setState(p.getState());
			s.setPincode(p.getPincode());
			return s;

		}).collect(Collectors.toList());

		newRow.setAddresses(list);

//		studentLoginRepository.save(newCredentials);

		studentRepository.save(newRow);
		return dto;
	}

	@Override
	public StudentDTO searchStudentByPhoneNumber(String phoneNumber) throws StudentManagementException {
		StudentInfo s = studentRepository.fetchDetailsByPhoneNumber(phoneNumber);
		if (s == null)
			throw new StudentManagementException("No student details found with given phone number " + phoneNumber);
		StudentDTO dto = new StudentDTO();
		dto.setStudentId(s.getStudentId());
		dto.setStudentName(s.getStudentName());
		dto.setFatherName(s.getStudentFatherName());
		dto.setGrade(s.getGrade());
		dto.setPoneNumber(s.getPhoneNumber());
		dto.setJoiningDate(s.getJoiningDate());
		dto.setType(s.getType());
		List<StudentAddressDTO> ladto = new ArrayList<StudentAddressDTO>();
		// ladto.add(null)
		// fetching addressdetails and converting the entity to dto object using map
		// method
		ladto = s.getAddresses().stream().map(t -> {
			StudentAddressDTO sadto = new StudentAddressDTO();
			sadto.setAddressId(t.getAddressId());
			sadto.setAddressLine1(t.getAddressLine1());
			sadto.setAddressLine2(t.getAddressLine2());
			sadto.setCity(t.getCity());
			sadto.setPincode(t.getPincode());
			sadto.setState(t.getState());
			sadto.setAddressType(t.getAddressType());
			// sadto.setAddressLine2(t.getAddressLine2());

			return sadto;
		}).collect(Collectors.toList());
		// set addreess dto
		dto.setAddressDTO(ladto);

//fetching entity login details from the student info object
		StudentLoginDetails login = s.getLogin();
		StudentLoginDetailsDTO ldto = new StudentLoginDetailsDTO();
		// setting the values to the login dto object
		ldto.setUsername(login.getUsername());
		dto.setLoginDTO(ldto);
		return dto;
	}

	@Override
	public List<StudentDTO> filterByPhoneOrName(String phoneNumber, String name) throws StudentManagementException {
//		List<StudentInfo> s = studentRepository.fetchingBasedOnContactOrName(phoneNumber, name);
		List<StudentInfo> s = studentRepository.findByPhoneNumberOrStudentName(phoneNumber, name);
		if (s.isEmpty())
			throw new StudentManagementException(

					"No student details found with given phone number or name" + phoneNumber + "or " + name);
		return s.stream().map(p -> {
			StudentDTO dto = new StudentDTO();
			dto.setStudentId(p.getStudentId());
			dto.setStudentName(p.getStudentName());

			return dto;
		}).collect(Collectors.toList());

	}

	@Override
	public StudentDTO searchByUsername(String username) throws StudentManagementException {
		StudentInfo s = studentRepository.findByLoginUsername(username);

		StudentDTO dto = new StudentDTO();
		dto.setStudentId(s.getStudentId());
		dto.setStudentName(s.getStudentName());

		return dto;

	}

	@Override
	@Transactional
	public void deleteStudentInfo(String username) throws StudentManagementException {
		StudentInfo s = studentRepository.findByLoginUsername(username);
		if (s == null)
			throw new StudentManagementException("no student found with provided user name");
		List<StudentAddress> address = s.getAddresses();
		
		studentAddressRepository.deleteAll(address);
		studentLoginRepository.deleteById(username);
		// studentRepository.deleteByUserName(username);
		studentRepository.delete(s);

	}

	@Override
	public List<StudentDTO> filterByName(String name) throws StudentManagementException {
//		List<StudentInfo> s = studentRepository.fetchingBasedOnContactOrName(phoneNumber, name);
		List<StudentInfo> s = studentRepository.filterStudentName(name);
		if (s.isEmpty())
			throw new StudentManagementException(

					"No student details found with given  name " + name);
		return s.stream().map(p -> {
			StudentDTO dto = new StudentDTO();
			dto.setStudentId(p.getStudentId());
			dto.setStudentName(p.getStudentName());

			return dto;
		}).collect(Collectors.toList());

	}

	@Override
	@Transactional
	public StudentDTO updateStudent(String oldPhoneNumber, String newPhoneNumber) throws StudentManagementException {
		StudentInfo s = studentRepository.fetchDetailsByPhoneNumber(oldPhoneNumber);
		if (s == null)
			throw new StudentManagementException("No student found with provided details");
		s.setPhoneNumber(newPhoneNumber);
		studentRepository.save(s);
		StudentDTO dto = new StudentDTO();
		dto.setStudentId(s.getStudentId());
		dto.setStudentName(s.getStudentName());
		dto.setFatherName(s.getStudentFatherName());
		dto.setGrade(s.getGrade());
		dto.setPoneNumber(s.getPhoneNumber());
		dto.setJoiningDate(s.getJoiningDate());
		dto.setType(s.getType());
		List<StudentAddressDTO> ladto = new ArrayList<StudentAddressDTO>();
		// ladto.add(null)
		// fetching addressdetails and converting the entity to dto object using map
		// method
		ladto = s.getAddresses().stream().map(t -> {
			StudentAddressDTO sadto = new StudentAddressDTO();
			sadto.setAddressId(t.getAddressId());
			sadto.setAddressLine1(t.getAddressLine1());
			sadto.setAddressLine2(t.getAddressLine2());
			sadto.setCity(t.getCity());
			sadto.setPincode(t.getPincode());
			sadto.setState(t.getState());
			sadto.setAddressType(t.getAddressType());
			// sadto.setAddressLine2(t.getAddressLine2());

			return sadto;
		}).collect(Collectors.toList());
		// set addreess dto
		dto.setAddressDTO(ladto);

//fetching entity login details from the student info object
		StudentLoginDetails login = s.getLogin();
		StudentLoginDetailsDTO ldto = new StudentLoginDetailsDTO();
		// setting the values to the login dto object
		ldto.setUsername(login.getUsername());
		dto.setLoginDTO(ldto);
		return dto;
	}
}
