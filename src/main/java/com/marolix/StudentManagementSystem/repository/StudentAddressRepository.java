package com.marolix.StudentManagementSystem.repository;

import org.springframework.data.repository.CrudRepository;

import com.marolix.StudentManagementSystem.entity.StudentAddress;

public interface StudentAddressRepository extends CrudRepository<StudentAddress, Integer> {

}
