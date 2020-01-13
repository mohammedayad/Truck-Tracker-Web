/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truckTracking.controller.managedBeans;

import com.truckTracking.common.utils.JSFUtils;
import com.truckTracking.common.utils.TruckTrackingUtils;
import com.truckTracking.model.entities.User;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author mohammed.ayad
 */
public abstract class AbstractManagedBean implements Serializable {

//    public abstract void auditAction(String actionResult, String actionValue);
//    public void auditAction(UserAction action, String actionResult, String actionValue, Logger logger) {
//        logger.debug("auditAction start auditing...");
//        Audit audit = DrmUtils.getAuditEntity();
//        User currentUser = getCurrentLoggedInUser();
//        if (currentUser != null) {
//            logger.debug("auditAction current user exist...");
//            audit.setUserId(currentUser);
//        } else {
//            logger.debug("auditAction current user not exist...");
//        }
//        audit.setActionValue(actionValue);
//        audit.setActionResult(actionResult);
//        audit.setAction(action.name());
//        DrmUtils.saveAudit(audit);
//        logger.debug("auditAction end auditing...");
//    }

	public User getCurrentLoggedInUser() {
		HttpServletRequest currentRequest = getCurrentRequest();
		User currentUser = TruckTrackingUtils.getCurrentUser(currentRequest);
		return currentUser;
	}

	private HttpServletRequest getCurrentRequest() {
		HttpServletRequest currentRequest = (HttpServletRequest) JSFUtils.getExternalContext().getRequest();
		return currentRequest;
	}

}
