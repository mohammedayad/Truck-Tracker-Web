package com.truckTracking.serviceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.truckTracking.common.utils.Logger;
import com.truckTracking.common.utils.TruckTrackingUtils;
import com.truckTracking.model.entities.User;
import com.truckTracking.model.repository.UserRepository;
import com.truckTracking.service.SecurityService;

@Service("securityService")
@Transactional
public class SecurityServiceImpl implements SecurityService {

	private static final Logger logger = Logger.getLogger(SecurityServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	private void init() {
		logger.debug("SecurityService has been initilized");

	}

	@Override
	public LoginData login(String username, String password) {
		logger.debug("login>>> " + username);
		LoginData loginData = new LoginData();
		String expectedHashedPassword = TruckTrackingUtils.getHash(password);
		Optional<User> user = userRepository.login(username, expectedHashedPassword);
		if (user.isPresent()) {
			logger.debug("user found successfully");
			String token = UUID.randomUUID().toString();
			User userEntity = user.get();
			userEntity.setToken(token);
			userRepository.save(userEntity);
			loginData.setUser(userEntity);
			loginData.setLoginStatus(LOGIN_STATUS.SUCCESS_LOGIN);
			logger.debug("LOGIN_STATUS " + LOGIN_STATUS.SUCCESS_LOGIN);
		} else {
			logger.debug("user not found with this user name " + username + " and password " + expectedHashedPassword);
			loginData.setLoginStatus(LOGIN_STATUS.USER_NOTFOUND);
		}

		return loginData;
	}

	@Override
	public Optional<User> findUserByToken(String token) {
		logger.debug("findUserByToken>>> " + token);
		Optional<User> user = userRepository.findByToken(token);
		return user;
	}


	@Override
	public LoginData validateUserLoginMobile(String submittedUsername, String submittedPassword) {
		logger.debug("validateUserLoginMobile " + submittedUsername);
		LoginData loginData = new LoginData();
		User userEntity = validateUser(submittedUsername);
		if (userEntity != null) {// user found
			String expectedHashedPassword = TruckTrackingUtils.getHash(submittedPassword);
			logger.debug("expectedHashedSum " + expectedHashedPassword);
			if (expectedHashedPassword.equals(userEntity.getUserPasswordID().getPassword())) {
				logger.debug("password matched successfully");
				loginData.setUser(userEntity);
				loginData.setLoginStatus(LOGIN_STATUS.SUCCESS_LOGIN);
				logger.debug("LOGIN_STATUS " + LOGIN_STATUS.SUCCESS_LOGIN);
			} else {
				logger.debug("password not matched successfully");
				loginData.setLoginStatus(LOGIN_STATUS.LOGIN_FAILED);
				logger.debug("LOGIN_STATUS " + LOGIN_STATUS.LOGIN_FAILED);

			}

		} else {
			logger.debug("user not found with this user name " + submittedUsername);
			loginData.setLoginStatus(LOGIN_STATUS.USER_NOTFOUND);
		}
		return loginData;
	}

	@Override
	public LoginData validateUserLogin(String submittedUsername, String submittedPassword, String checksumKey,
			String submittedHashSum) {
		logger.debug("validateUserLogin " + submittedUsername);
		LoginData loginData = new LoginData();
//		List<User> results = getUserByUsername(submittedUsername);
//		if (results != null && !results.isEmpty()) {// user found
//			logger.debug("user is found with this user name " + submittedUsername);
//			User userEntity = null;
//			for (User user : results) {
//				userEntity = user;
//				break;
//			}
		User userEntity = validateUser(submittedUsername);
		if (userEntity != null) {// user found
			String expectedHashedSum = TruckTrackingUtils.getHash(submittedPassword + submittedUsername);
			logger.debug("expectedHashedSum " + expectedHashedSum);
			String expectedHashedValue = TruckTrackingUtils
					.getHash(userEntity.getUserPasswordID().getPassword() + checksumKey);
			logger.debug("expectedHashedValue " + expectedHashedValue);
			if (expectedHashedValue.equals(submittedPassword) && expectedHashedSum.equals(submittedHashSum)) {
				logger.debug("password matched successfully");
				loginData.setUser(userEntity);
				loginData.setLoginStatus(LOGIN_STATUS.SUCCESS_LOGIN);
				logger.debug("LOGIN_STATUS " + LOGIN_STATUS.SUCCESS_LOGIN);
			} else {
				logger.debug("password not matched successfully");
				loginData.setLoginStatus(LOGIN_STATUS.LOGIN_FAILED);
				logger.debug("LOGIN_STATUS " + LOGIN_STATUS.LOGIN_FAILED);

			}
		} else {
			logger.debug("user not found with this user name " + submittedUsername);
			loginData.setLoginStatus(LOGIN_STATUS.USER_NOTFOUND);
		}

		return loginData;

	}

	private User validateUser(String submittedUsername) {
		logger.debug("validateUser " + submittedUsername);
		User userEntity = null;
		List<User> results = getUserByUsername(submittedUsername);
		if (results != null && !results.isEmpty()) {// user found
			logger.debug("user is found with this user name " + submittedUsername);
			for (User user : results) {
				userEntity = user;
				break;
			}
		}
		return userEntity;

	}

	@Override
	public List<User> getUserByUsername(String userName) {
		logger.debug("getUserByUsername " + userName);

//		List<User> results = getNamedQueryResult(User.NAMED_QUERY_USER_FIND_USER_BY_USERNAME, username);
		List<User> results = userRepository.findAllUsersByUserName(userName);

		return results;
	}

	@Override
	public String addUserPassword(String password) {
		String hashedPassword = hashingPassword(password);
		return hashedPassword;

	}

	private String hashingPassword(String newPassword) {
		logger.debug("hashingPassword ", newPassword);
		String hashedPassword = TruckTrackingUtils.getHash(newPassword);
		logger.debug("password " + newPassword + " hashed Password" + hashedPassword);
		return hashedPassword;
//        UserPassword password = new UserPassword();
//        password.setPassword(hashedPassword);
//        password.setCreateDate(new Date());
//        password.setUpdateDate(new Date());
//        user.setUserPasswordID(password);
	}

}
