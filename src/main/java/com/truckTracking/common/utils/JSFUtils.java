package com.truckTracking.common.utils;

import java.util.Arrays;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.primefaces.context.RequestContext;

public class JSFUtils {

	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public static String getRequestParamValue(String requestParamName) {
		return getExternalContext().getRequestParameterMap().get(requestParamName);
	}

	public static Map<String, Object> getSessionMap() {
		return getExternalContext().getSessionMap();
	}

	public static Object getSessionValue(String sessionKey) {
		return getSessionMap().get(sessionKey);
	}

	public static ExternalContext getExternalContext() {
		return getFacesContext().getExternalContext();
	}

	public static Map<String, Object> getRequestMap() {
		return getExternalContext().getRequestMap();
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) getExternalContext().getRequest();
	}

	public static FacesMessage addInfoMessage(String msgContent) {
		return addFacesMessage(null, msgContent, FacesMessage.SEVERITY_INFO);
	}

	public static FacesMessage addWarnMessage(String msgContent) {
		return addFacesMessage(null, msgContent, FacesMessage.SEVERITY_WARN);
	}

	public static FacesMessage addErrorMessage(String msgContent) {
		return addFacesMessage(null, msgContent, FacesMessage.SEVERITY_ERROR);
	}

	public static FacesMessage addFacesMessage(String msgContent, Severity msgSeverity) {
		return addFacesMessage(null, msgContent, msgSeverity);
	}

	public static FacesMessage addFacesMessage(String msgId, String msgContent, Severity msgSeverity) {
		return addFacesMessage(msgId, msgContent, null, msgSeverity);
	}

	public static FacesMessage addFacesMessage(String msgId, String msgContent, String msgDetails,
			Severity msgSeverity) {
		FacesMessage facesMessage = getFacesMessage(msgContent, msgDetails, msgSeverity);
		getFacesContext().addMessage(msgId, facesMessage);
		return facesMessage;
	}

	public static FacesMessage getFacesMessage(String msgContent, String msgDetails, Severity msgSeverity) {
		FacesMessage facesMessage = new FacesMessage(msgSeverity, msgContent, msgDetails);
		return facesMessage;
	}

	public static FacesMessage getFacesMessage(String msgContent, Severity msgSeverity) {
		return getFacesMessage(msgContent, null, msgSeverity);
	}

//    public static void executeClientAction(String clientAction) {
//        RequestContext context = getRequestContext();
//        context.execute(clientAction);
//    }
//
//    private static RequestContext getRequestContext() {
//        RequestContext context = RequestContext.getCurrentInstance();
//        return context;
//    }

	public static Object resolveExpression(String expression) {
		ExpressionFactory elFactory = getExpressionFactory();
		ELContext elContext = getELContext();
		ValueExpression valueExp = elFactory.createValueExpression(elContext, expression, Object.class);
		return valueExp.getValue(elContext);
	}

	public static ExpressionFactory getExpressionFactory() {
		return getApplication().getExpressionFactory();
	}

	public static ELContext getELContext() {
		return getFacesContext().getELContext();
	}

	public static Application getApplication() {
		return getFacesContext().getApplication();
	}

	public static HttpServletResponse getResponse() {
		return (HttpServletResponse) getExternalContext().getResponse();
	}

//    public static void update(String... ids) {
//        getRequestContext().update(Arrays.asList(ids));
//    }
//
//    public static void hideDialog(String dialogId) {
//        executeClientAction("PF('" + dialogId + "').hide();");
//    }

}
