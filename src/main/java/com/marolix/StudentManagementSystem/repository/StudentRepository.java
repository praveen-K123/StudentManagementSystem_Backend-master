package com.marolix.StudentManagementSystem.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.marolix.StudentManagementSystem.entity.StudentInfo;

public interface StudentRepository extends CrudRepository<StudentInfo, Integer> {
//save to insert a new row /update
	// findById to retive using primary key
	// deleteById to delete using using primary key

	// public StudentInfo findByPhoneNumber(String phoneNumber);

	public List<StudentInfo> findByJoiningDate(LocalDate date);

	public List<StudentInfo> findByPhoneNumberOrStudentName(String phoneNumber, String name);

	public List<StudentInfo> findByJoiningDateGreaterThan(LocalDate date);

	public List<StudentInfo> findByJoiningDateLessThan(LocalDate date);

	public List<StudentInfo> findByJoiningDateBetween(LocalDate date1, LocalDate date2);

	// Query approach methods
//	@Query(value = "select * from student where std_phone_number=:phonenumber or std_name=:name", nativeQuery = true)
//	public List<StudentInfo> fetchingBasedOnContactOrName(@Param("phonenumber") String contact,
//			@Param("name") String name);
//	@Query(value = "select * from student where std_phone_number=?1 or std_name=?2", nativeQuery = true)
//	public List<StudentInfo> fetchingBasedOnContactOrName(String contact, String name);
//	@Query(value = "select s from StudentInfo s where s.phoneNumber=:number")
//	public StudentInfo fetchDetailsByPhoneNumber(@Param("number") String phone);
	@Query(value = "select s from StudentInfo s where s.phoneNumber=?1")
	public StudentInfo fetchDetailsByPhoneNumber(String phone);

	// fetching details based on the child table column name

	StudentInfo findByLoginUsername(String username);

	@Query(value = "delete from student where username=?1", nativeQuery = true)
	@Modifying(clearAutomatically = true)
	public int deleteByUserName(String userName);

	public List<StudentInfo> filterStudentName(String studentname);
}
