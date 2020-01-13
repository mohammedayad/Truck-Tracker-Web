/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truckTracking.common.utils;

/**
 *
 * @author mohammed.ayad
 */
public interface Constants {

	public static final String SESSION_KEYS_LOGGED_IN_USER = "user";
	public static final String SESSION_KEYS_IS_USER_LOGGED_IN = "loggedIn";
	public static final String SYSTEM_SECURITY_HASH_ALGORITHM = "SHA-256";
	public static final String SESSION_KEYS_USER_ID = "userId";
	public static final String SESSION_KEYS_LAST_LOGIN_TIME = "lastLoginTime";
	public static final String KEYS_LOGIN_COOKIE_NAME = "LID";
//    public static final String SUFFIX_JSF_PAGE_EXTENSION = "jsf";
	public static final String SUFFIX_JSF_PAGE_EXTENSION = "xhtml";
	public static final String JNDI_GLOBAL_SEPARATOR = "/";
	public static final String JNDI_GLOBAL_PREFIX = "java:global".concat(JNDI_GLOBAL_SEPARATOR);
	public static final String JNDI_GLOBAL_APP_NAME = JNDI_GLOBAL_PREFIX.concat("FrontEndDRM-ear")
			.concat(JNDI_GLOBAL_SEPARATOR);
	public static final String JNDI_GLOBAL_BUSINESS_MODULE_NAME = JNDI_GLOBAL_APP_NAME
			.concat("FrontEndDRM-ejb-1.0-SNAPSHOT").concat(JNDI_GLOBAL_SEPARATOR);

	public static enum SYSTEM_ERRORS {
		BACKEND_INVOKATION_ERROR("FD_LS_1000", "Unexpected error when calling backend service"),
		GENERAL_ERROR("FD_LS_1001", "General error"), UNKOWN_ERROR("FD_LS_1002", "Unexpected error"),
		USER_NOT_LOGGED_IN_ERROR("FD_LS_1003", "User is not logged in"),
		USER_SESSION_TIMEOUT_ERROR("FD_LS_1004", "User session timeout out has been reached"),
		INVALID_INPUT_ERROR("FD_LS_1005", "User input is invalid, please chack the input values"),
		INVALID_TRANSACTION_PASSWORD_ERROR("FD_LS_1006", "User transaction password is invalid"),
		INVALID_USER_PACKAGE_ERROR("FD_LS_1007",
				"User package does not contain the product that you are trying to invoke");
		private String code;
		private String description;

		private SYSTEM_ERRORS(String code, String description) {
			this.code = code;
			this.description = description;

		}

		public String getCode() {
			return code;
		}

		public String getDescription() {
			return description;
		}

	};

	public static enum SYSTEM_PAGES {
		LOGOUT_URL("/dologout"), GENERAL_ERROR_URL("/error." + SUFFIX_JSF_PAGE_EXTENSION),
		HOME_PAGE_URL("/homepage." + SUFFIX_JSF_PAGE_EXTENSION), FORM_LOGIN_URL("/login." + SUFFIX_JSF_PAGE_EXTENSION),
		BASIC_LOGIN_URL("/dologin"), LOGOUT_RESULT_URL("/logout." + Constants.SUFFIX_JSF_PAGE_EXTENSION);

		private String url;

		private SYSTEM_PAGES(String url) {
			this.url = url;
		}

		public String getUrl() {
			return url;
		}

	};

}
