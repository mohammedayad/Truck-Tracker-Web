package com.truckTracking.common.exceptions;

public class SystemException extends RuntimeException {

	private String code;
	private String description;

	public SystemException() {
		super();
	}

	public SystemException(String code, String description) {
		super(description);
		this.code = code;
		this.description = description;
	}

	public SystemException(String code, String description, Throwable cause) {
		super(cause);
		this.code = code;
		this.description = description;

	}

	public SystemException(String code, String description, String msg, Throwable cause) {
		super(msg, cause);
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
