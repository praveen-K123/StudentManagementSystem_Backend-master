package com.marolix.StudentManagementSystem.utility;

import java.time.LocalDateTime;

public class ErrorInfo {
	private String errorMessage;
	private LocalDateTime time;
	private Integer errorCode;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

}
