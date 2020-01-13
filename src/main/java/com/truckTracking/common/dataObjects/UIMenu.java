package com.truckTracking.common.dataObjects;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UIMenu extends UIMenuLink {

	/**
	 *
	 */
	private static final long serialVersionUID = -4290356126062197134L;

	private String label;
	private int menuOrder;
	private List<UIMenu> childrenMenu;
	private UIMenu parentMenu;

	private boolean childrenSorted;

	private String functionId;

	public UIMenu() {

	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(int menuOrder) {
		this.menuOrder = menuOrder;
	}

	public List<UIMenu> getChildrenMenu() {
		sortChildren();
		return childrenMenu;
	}

	private void sortChildren() {
		if (!childrenSorted) {
			childrenSorted = true;
			Comparator<UIMenu> c = new Comparator<UIMenu>() {
				public int compare(UIMenu o1, UIMenu o2) {
					if (o1.getMenuOrder() > o2.getMenuOrder()) {
						return 1;
					} else {
						return -1;
					}

				};
			};
			Collections.sort(childrenMenu, c);
		}
	}

	public void setChildrenMenu(List<UIMenu> childrenMenu) {
		childrenSorted = false;
		this.childrenMenu = childrenMenu;
	}

	public UIMenu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(UIMenu parentMenu) {
		this.parentMenu = parentMenu;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public String getFunctionId() {
		return functionId;
	}

}
