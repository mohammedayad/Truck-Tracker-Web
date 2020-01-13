package com.truckTracking.common.dataObjects;

import java.io.Serializable;

import com.truckTracking.common.utils.NavigationType;

public class UIMenuLink implements Serializable {

	private int id;
	private String url;
	private NavigationType navigationType;
	private String label;
	private String functionId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setNavigationType(NavigationType navigationType) {
		this.navigationType = navigationType;
	}

	public NavigationType getNavigationType() {
		return navigationType;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public String getFunctionId() {
		return functionId;
	}
}
