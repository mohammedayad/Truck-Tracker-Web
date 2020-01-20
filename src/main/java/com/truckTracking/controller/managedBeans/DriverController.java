package com.truckTracking.controller.managedBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.truckTracking.common.utils.Logger;
import com.truckTracking.model.entities.DeliveryDetails;
import com.truckTracking.model.entities.User;
import com.truckTracking.service.DeliveryDetailsService;

@Named("driverBean")
@ViewScoped
public class DriverController extends AbstractManagedBean {
	private static final Logger logger = Logger.getLogger(DriverController.class);
	@Autowired
	private DeliveryDetailsService deliveryDetailsService;
	private List<DeliveryDetails> allDeliveryDetails;
	private DeliveryDetails selectedDelivery;
	private User selectedUser;
	private String currentLatitude;
	private String currentLongitude;

	@PostConstruct
	public void init() {
		logger.debug("DriverController has been initilized.....");
		allDeliveryDetails = deliveryDetailsService.getAllDeliveryDetails();

	}

	public void updateDeliveryMap() {
		logger.debug("<<<updateDeliveryMap>>>");

	}

	public String getSelectedDeliveryLocation() {
		logger.debug("<<<getSelectedDeliveryLocation>>>");
		logger.debug(selectedDelivery);
		String selectedDeliveryLocation = "0,0";
		if (selectedDelivery != null && selectedDelivery.getLocationLatitude() != null
				&& selectedDelivery.getLocationLongitude() != null) {
			selectedDeliveryLocation = selectedDelivery.getLocationLatitude() + ","
					+ selectedDelivery.getLocationLongitude();

		}
		logger.debug("SelectedDeliveryLocation>>> " + selectedDeliveryLocation);
		return selectedDeliveryLocation;

	}

	public DeliveryDetailsService getDeliveryDetailsService() {
		return deliveryDetailsService;
	}

	public void setDeliveryDetailsService(DeliveryDetailsService deliveryDetailsService) {
		this.deliveryDetailsService = deliveryDetailsService;
	}

	public List<DeliveryDetails> getAllDeliveryDetails() {
		return allDeliveryDetails;
	}

	public void setAllDeliveryDetails(List<DeliveryDetails> allDeliveryDetails) {
		this.allDeliveryDetails = allDeliveryDetails;
	}

	public DeliveryDetails getSelectedDelivery() {
		return selectedDelivery;
	}

	public void setSelectedDelivery(DeliveryDetails selectedDelivery) {
		this.selectedDelivery = selectedDelivery;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	public String getCurrentLatitude() {
		return currentLatitude;
	}

	public void setCurrentLatitude(String currentLatitude) {
		this.currentLatitude = currentLatitude;
	}

}
