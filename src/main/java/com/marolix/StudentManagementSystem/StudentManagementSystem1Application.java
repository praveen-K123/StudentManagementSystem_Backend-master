package com.marolix.StudentManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@SpringBootApplication
public class StudentManagementSystem1Application {
	

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystem1Application.class, args);

		ApplicationContext context = new AnnotationConfigApplicationContext();
		
	}

	
}
