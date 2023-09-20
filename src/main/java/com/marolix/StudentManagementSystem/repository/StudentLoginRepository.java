package com.marolix.StudentManagementSystem.repository;

import org.springframework.data.repository.CrudRepository;

import com.marolix.StudentManagementSystem.entity.StudentLoginDetails;

public interface StudentLoginRepository extends CrudRepository<StudentLoginDetails, String> {

}
