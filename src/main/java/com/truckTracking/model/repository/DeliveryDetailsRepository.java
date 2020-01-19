package com.truckTracking.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truckTracking.model.entities.DeliveryDetails;

@Repository("deliveryDetailsRepository")
public interface DeliveryDetailsRepository extends JpaRepository<DeliveryDetails, Integer> {

}
