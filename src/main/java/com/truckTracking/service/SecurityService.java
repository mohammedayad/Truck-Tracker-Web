package com.truckTracking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.truckTracking.model.entities.User;


public interface SecurityService {

	LoginData validateUserLogin(String submittedUsername, String submittedPassword, String checksumKey,
			String submittedHashSum);

	List<User> getUserByUsername(String username);

	String addUserPassword(String password);

	enum LOGIN_STATUS {
		SUCCESS_LOGIN, FORCE_CHANGE_PASSWORD, PASSWORD_EXPIRED, LOGIN_FAILED, USER_LOCKED, PASSWORD_LIFETIME_EXPIRED,
		USER_NOTFOUND, USER_INACTIVE, NO_OF_RETRIES_REACHED
	}

	class LoginData {

		private User user;
		private LOGIN_STATUS loginStatus;

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public LOGIN_STATUS getLoginStatus() {
			return loginStatus;
		}

		public void setLoginStatus(LOGIN_STATUS loginStatus) {
			this.loginStatus = loginStatus;
		}

	}

}
