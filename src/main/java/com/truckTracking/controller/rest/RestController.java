package com.truckTracking.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.truckTracking.common.utils.Logger;
import com.truckTracking.controller.managedBeans.LoginController;
import com.truckTracking.model.entities.DeliveryDetails;
import com.truckTracking.model.entities.User;
import com.truckTracking.service.DeliveryDetailsService;
import com.truckTracking.service.SecurityService;
import com.truckTracking.service.SecurityService.LOGIN_STATUS;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("api/truckTracking")
public class RestController {

	private static final Logger logger = Logger.getLogger(RestController.class);

	@Autowired
	private SecurityService securityService;

	@Autowired
	private DeliveryDetailsService deliveryDetailsService;

	@RequestMapping(value = "test/{name}", method = RequestMethod.GET)
	public String test(@PathVariable String name) {
		logger.debug("call test api>>>> " + name);
		return "Hello " + name;
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@RequestBody User user) {
		logger.debug("<<<<call login api>>>>");
//		SecurityService.LoginData loginData = securityService.validateUserLoginMobile(user.getUserName(),
//				user.getUserPasswordID().getPassword());
//		return "{\"state\":\"" + loginData.getLoginStatus().toString() + "\"}";
		SecurityService.LoginData loginData = securityService.login(user.getUserName(),
				user.getUserPasswordID().getPassword());
		if (loginData.getLoginStatus().equals(LOGIN_STATUS.SUCCESS_LOGIN)) {
			return "{\"state\":\"" + loginData.getLoginStatus().toString() + "\",\"token\":\""
					+ loginData.getUser().getToken() + "\"}";
		}
		return "{\"state\":\"" + loginData.getLoginStatus().toString() + "\"}";
	}

	@RequestMapping(value = "deliveryDetails/{userToken}", method = RequestMethod.POST)
	public String addDeliveryDetails(@PathVariable String userToken,@RequestBody DeliveryDetails deliveryDetails) {
		logger.debug("<<<<call addDeliveryDetails api>>>>");
		boolean isAddedSuccessfully = deliveryDetailsService.addNewDeliveryDetails(userToken,deliveryDetails);
		return "{\"isAddedSuccessfully\":" + isAddedSuccessfully + "}";

	}

}
