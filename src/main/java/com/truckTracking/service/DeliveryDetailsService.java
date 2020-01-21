package com.truckTracking.service;

import java.util.List;
import java.util.Optional;

import com.truckTracking.model.entities.DeliveryDetails;

public interface DeliveryDetailsService {

	public boolean addNewDeliveryDetails(String userToken, DeliveryDetails deliveryDetails);
	
	public boolean updateDeliveryLocation(String userToken, DeliveryDetails deliveryDetails);

	public List<DeliveryDetails> getAllDeliveryDetails();

	public DeliveryDetails findDeliveryDetailsById(int DeliveryId);

}
