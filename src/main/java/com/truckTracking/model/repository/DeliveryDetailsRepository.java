package com.truckTracking.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truckTracking.model.entities.DeliveryDetails;
import com.truckTracking.model.entities.User;

@Repository("deliveryDetailsRepository")
public interface DeliveryDetailsRepository extends JpaRepository<DeliveryDetails, Integer> {

	Optional<DeliveryDetails> findByUserId(User user);

}
