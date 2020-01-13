package com.truckTracking.common.utils;

public enum UserAction {

	SUCCESS(1), FAIL(2), ADD(3), DELETE(4), MODIFY(5), VIEW(6), INQUIRY(7), EXPORT(8), APPROVE(9), LOGIN(10),
	LOGOUT(11), CHANGE_LOGIN_ID(12);
	int m_code;

	private UserAction(int code) {
		this.m_code = code;
	}

	public int getCode() {
		return m_code;
	}
}
