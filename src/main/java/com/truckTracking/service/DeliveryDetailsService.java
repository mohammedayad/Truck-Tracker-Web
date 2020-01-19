package com.truckTracking.service;

import com.truckTracking.model.entities.DeliveryDetails;

public interface DeliveryDetailsService {

	public boolean addNewDeliveryDetails(String userToken,DeliveryDetails deliveryDetails);

}
