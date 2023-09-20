package com.marolix.StudentManagementSystem.utility;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerClass {

	@ExceptionHandler(StudentManagementException.class)
	public ResponseEntity<ErrorInfo> methodToHandleStudentManagementException(Exception e) {
		ErrorInfo error = new ErrorInfo();
		error.setErrorMessage(e.getMessage());
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setTime(LocalDateTime.now());
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.BAD_REQUEST);
	}
//	@ExceptionHandler(StudentManagementException.class)
//	public String methodToHandleStudentManagementException(Exception e) {
//
//		return e.getMessage();
//	}

//	@ExceptionHandler(NullPointerException.class)
//	public String methodToHandleNPE() {
//		return "npe occured";
//	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> generalException(Exception e) {

		ErrorInfo error = new ErrorInfo();
		error.setErrorMessage(e.getMessage());
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setTime(LocalDateTime.now());
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorInfo> invalidData(Exception e) {
		ErrorInfo error = new ErrorInfo();
		String msg = null;
		if (e instanceof MethodArgumentNotValidException) {
			MethodArgumentNotValidException manm = (MethodArgumentNotValidException) e;
			msg = manm.getAllErrors().stream().map(e2 -> e2.getDefaultMessage()).collect(Collectors.joining(", "));
		}
		error.setErrorMessage(msg);
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setTime(LocalDateTime.now());
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.BAD_REQUEST);
	}
}
