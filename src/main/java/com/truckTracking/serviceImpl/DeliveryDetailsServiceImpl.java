package com.truckTracking.serviceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.truckTracking.common.utils.Logger;
import com.truckTracking.model.entities.DeliveryDetails;
import com.truckTracking.model.entities.User;
import com.truckTracking.model.repository.DeliveryDetailsRepository;
import com.truckTracking.service.DeliveryDetailsService;
import com.truckTracking.service.SecurityService;

@Service("deliveryDetailsService")
@Transactional
public class DeliveryDetailsServiceImpl implements DeliveryDetailsService {
	private static final Logger logger = Logger.getLogger(DeliveryDetailsServiceImpl.class);

	@Autowired
	private DeliveryDetailsRepository deliveryDetailsRepository;

	@Autowired
	private SecurityService securityService;

	@Override
	public boolean addNewDeliveryDetails(String userToken, DeliveryDetails deliveryDetails) {
		logger.debug("<<<addNewDeliveryDetails>>>");
		logger.debug(deliveryDetails);
		try {
			Optional<User> user = securityService.findUserByToken(userToken);
			if (user.isPresent()) {
				logger.debug("user exist");
				User userEntity = user.get();
				deliveryDetails.setUserId(userEntity);
				deliveryDetailsRepository.saveAndFlush(deliveryDetails);
				return true;

			} else {
				logger.debug("user not exist");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}
	}

	@Override
	public List<DeliveryDetails> getAllDeliveryDetails() {
		logger.debug("<<<getAllDeliveryDetails>>>");
		List<DeliveryDetails> allDeliveryDetails = deliveryDetailsRepository.findAll();
		if (allDeliveryDetails != null && !allDeliveryDetails.isEmpty()) {
			logger.debug("allDeliveryDetails size>>> " + allDeliveryDetails.size());
			return allDeliveryDetails;
		}
		logger.debug("<<<No DeliveryDetails exist>>>");
		return Collections.emptyList();
	}

}
