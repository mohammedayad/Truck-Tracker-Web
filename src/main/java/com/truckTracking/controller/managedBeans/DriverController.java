package com.truckTracking.controller.managedBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.beans.factory.annotation.Autowired;

import com.truckTracking.common.utils.Logger;
import com.truckTracking.model.entities.DeliveryDetails;
import com.truckTracking.model.entities.User;
import com.truckTracking.service.DeliveryDetailsService;

@Named("driverBean")
@ViewScoped
//@SessionScoped
public class DriverController extends AbstractManagedBean implements Converter {
	private static final Logger logger = Logger.getLogger(DriverController.class);
	@Autowired
	private DeliveryDetailsService deliveryDetailsService;
	private List<DeliveryDetails> allDeliveryDetails;
	private DeliveryDetails selectedDelivery;
	private User selectedUser;
	private MapModel simpleModel;

	@PostConstruct
	public void init() {
		logger.debug("DriverController has been initilized.....");
		allDeliveryDetails = deliveryDetailsService.getAllDeliveryDetails();
		simpleModel = new DefaultMapModel();

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
			double currentLatitude = Double.parseDouble(selectedDelivery.getLocationLatitude());
			double currentLongitude = Double.parseDouble(selectedDelivery.getLocationLongitude());
			// Shared coordinates
			LatLng coord1 = new LatLng(currentLatitude, currentLongitude);
			// Basic marker
			simpleModel.addOverlay(new Marker(coord1, "here"));
			selectedDeliveryLocation = currentLatitude + "," + currentLongitude;

		}
		logger.debug("SelectedDeliveryLocation>>> " + selectedDeliveryLocation);
		return selectedDeliveryLocation;

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
		if (modelValue == null) {
			return "";
		}

		if (modelValue instanceof DeliveryDetails) {
			return String.valueOf(((DeliveryDetails) modelValue).getDeliveryId());
		} else {
			throw new ConverterException(new FacesMessage(modelValue + "is not a valid DeliveryDetails entity"));
		}
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
		if (submittedValue == null || submittedValue.isEmpty()) {
			return null;
		}

		try {
			return deliveryDetailsService.findDeliveryDetailsById(Integer.valueOf(submittedValue));
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(submittedValue + " is not a valid DeliveryDetails ID"));
		}
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

	public MapModel getSimpleModel() {
		return simpleModel;
	}

	public void setSimpleModel(MapModel simpleModel) {
		this.simpleModel = simpleModel;
	}

}
