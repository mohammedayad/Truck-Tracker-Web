/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truckTracking.controller.managedBeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.FacesException;
import javax.faces.application.Application;
import javax.faces.application.Resource;
import javax.faces.component.UIComponent;
import javax.faces.component.UIPanel;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DynamicMenuModel;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;

import com.truckTracking.common.dataObjects.UIMenu;
import com.truckTracking.common.dataObjects.UIMenuLink;
import com.truckTracking.common.utils.JSFUtils;
import com.truckTracking.common.utils.Logger;
import com.truckTracking.common.utils.NavigationType;
//import org.apache.commons.collections.Closure;
import com.truckTracking.model.entities.Menu;
import com.truckTracking.model.entities.User;
import com.truckTracking.service.MenuService;

/**
 *
 * @author mohammed.ayad
 */
@Named("menuBean")
@SessionScoped
public class MenuController extends AbstractManagedBean {

	public static final String SESSION_KEY_MENU_LIST = "menu-list";
	private static final String MENU_CONTROL_ATTRIBUTE_MENU_URL = "menu-url";
	private static final Logger logger = Logger.getLogger(MenuController.class);
	private UIComponent inputForm;
	private List<UIMenu> uiMenuPanel;
	private MenuModel breadCrumbModel;
	private List<Menu> rootElements;
	private List<UIMenuLink> uiMenuPermissions;
//    @EJB
	@Autowired
	private MenuService menuService;
	private String menuURL;

	@PostConstruct
	public void init() {
		logger.debug("initializeMenu has been called");

		breadCrumbModel = new DynamicMenuModel();
		DefaultMenuItem homeElement = new DefaultMenuItem();
		homeElement.setId("Home");
		homeElement.setValue("Home");
		homeElement.setCommand("/homepage.jsf");
		breadCrumbModel.addElement(homeElement);

		if (menuService != null) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
			if (session != null) {
				User user = (User) session.getAttribute("user");
				logger.debug(user);
				rootElements = menuService.getUserRootMenus();
				logger.debug("rootElements " + rootElements);
				Set<Menu> tmpMenu = new HashSet<Menu>(rootElements);
				rootElements.clear();
				rootElements.addAll(tmpMenu);
				logger.debug("rootElements " + rootElements);
				uiMenuPanel = new ArrayList<UIMenu>();
				final List<Menu> privilegesForUser = buildUserMenuPermissions(user);
				logger.debug("init all menus  " + privilegesForUser);
				buildUserMenuTree(privilegesForUser, rootElements);
			}
		} else {
			rootElements = new ArrayList<Menu>();
		}

	}

	private List<Menu> buildUserMenuPermissions(User user) {
		logger.debug("buildUserMenuPermissions");

		final List<Menu> privilegesForUser = menuService.getAllMenus();// get all menues
		uiMenuPermissions = new ArrayList<UIMenuLink>();
		// TODO we need to use this cached menu in permission checks
		CollectionUtils.forAllDo(privilegesForUser, new Closure<Object>() {

			@Override
			public void execute(Object element) {
				logger.debug("inside clouser execute");
				if (element instanceof Menu) {
					logger.debug("element instanceof Menu");
					Menu menu = (Menu) element;
					UIMenuLink uiMenu = new UIMenuLink();
					uiMenu.setId(menu.getMenuId());
					uiMenu.setUrl(menu.getLink());
					uiMenu.setNavigationType(NavigationType.DIRECT);
					uiMenu.setLabel(menu.getMenuName());
//                    uiMenu.setFunctionId(menu.getFunctionId());
					uiMenuPermissions.add(uiMenu);

				} else {
					logger.debug("element not instanceof Menu");
				}

			}
		});

		HttpSession session = (HttpSession) JSFUtils.getExternalContext().getSession(false);
		session.setAttribute(SESSION_KEY_MENU_LIST, uiMenuPermissions);

		return privilegesForUser;
	}

	private void buildUserMenuTree(final List<Menu> privilegesForUser, List<Menu> rootElements) {
		logger.debug("buildUserMenuTree");
		logger.debug("all menus  " + privilegesForUser);
		logger.debug("rootElements " + rootElements);

		CollectionUtils.forAllDo(rootElements, new Closure() {

			@Override
			public void execute(Object element) {
				logger.debug("inside clouser execute");
				if (element instanceof Menu) {
					logger.debug("element instanceof Menu");
					Menu menu = (Menu) element;
					UIMenu uiMenu = getUIMenu(menu);
					if (!uiMenuPanel.contains(uiMenu)) {
						uiMenuPanel.add(uiMenu);
					}
				} else {
					logger.debug("element not instanceof Menu");
				}

			}

			private UIMenu getUIMenu(Menu menu) {
				logger.debug("getUIMenu " + menu);
				logger.debug("getUIMenu " + menu.getMenuName());
				UIMenu uiMenu = new UIMenu();
				uiMenu.setId(menu.getMenuId());
				uiMenu.setLabel(menu.getMenuName());
				uiMenu.setMenuOrder(menu.getMenuOrder());
				uiMenu.setParentMenu(null);
				uiMenu.setUrl(menu.getLink());
//                uiMenu.setFunctionId(menu.getFunctionId());

				uiMenu.setChildrenMenu(getChildren(menu));
				return uiMenu;
			}

			private List<UIMenu> getChildren(Menu menu) {
				logger.debug("getChildren of " + menu.getMenuName());
				List<UIMenu> list = new ArrayList<UIMenu>();

				List<Menu> menuCollection = menu.getChildMenu();
				logger.debug("Children of " + menu.getMenuName() + " are " + menuCollection);
				logger.debug("size of children " + menuCollection.size());
				logger.debug("loop on Children of " + menu.getMenuName());
				logger.debug("all user menus " + privilegesForUser);
				for (Menu childMenu : menuCollection) {
					logger.debug("child menu " + childMenu);
					logger.debug("child name " + childMenu.getMenuName());
					if (privilegesForUser.contains(childMenu)) {// user
						// permission
						// has this
						// menu
						// child
						logger.debug("all privilage menus for user contain this menu child " + childMenu.getMenuName());
						list.add(getUIMenu(childMenu));
						logger.debug("child name " + childMenu.getMenuName() + " added successfully to its parent "
								+ menu.getMenuName());
					} else {
						logger.debug(
								"all privilage menus for user not contain this menu child " + childMenu.getMenuName());

					}
				}
				return list;
			}
		});
	}

	public void selectItem(ActionEvent event) {
		logger.debug("selectItem");
		Object object = event.getComponent().getAttributes().get(MENU_CONTROL_ATTRIBUTE_MENU_URL);
		logger.debug("selected menu " + object);
		System.out.println(object);
		this.menuURL = String.valueOf(object);
		this.menuURL = this.menuURL.substring(1);
		logger.debug("selected menu url " + menuURL);
	}

	public String openItem() throws IOException {
		logger.debug("openItem");
		String returnResult = null;
		DefaultMenuItem element = new DefaultMenuItem();
		logger.debug("loop on all menus " + uiMenuPermissions);
		for (UIMenuLink uiMenuLink : uiMenuPermissions) {
			String currentMenuURL = uiMenuLink.getUrl();
			logger.debug("currentMenuURL " + currentMenuURL);
			if (currentMenuURL != null && currentMenuURL.equals(menuURL)) {
				logger.debug("currentMenuURL=" + currentMenuURL);
				logger.debug("menuURL=" + menuURL);
				if (uiMenuLink.getNavigationType() == null
						|| uiMenuLink.getNavigationType().equals(NavigationType.COMPOSITE)) {
					inputForm.getChildren().clear();
					includeCompositeComponent(inputForm, "components", menuURL,
							inputForm.getClientId().concat("_".concat(String.valueOf(uiMenuLink.getId()))));
					break;
				} else if (uiMenuLink.getNavigationType().equals(NavigationType.DIRECT)) {
					returnResult = currentMenuURL;
				}
				element.setValue(uiMenuLink.getLabel());
				element.setId(String.valueOf(uiMenuLink.getId()));
				element.setCommand(currentMenuURL);

			}
		}
		boolean menuElementNotFound = true;

		for (MenuElement menuItem : breadCrumbModel.getElements()) {
			if (menuItem instanceof DefaultMenuItem) {
				DefaultMenuItem defaultMenuItem = (DefaultMenuItem) menuItem;
				if (defaultMenuItem.getValue().equals(element.getValue())) {
					menuElementNotFound = false;
					break;
				}
			}
		}

		if (menuElementNotFound) {
			breadCrumbModel.addElement(element);
		}
		return returnResult;
	}

	public void includeCompositeComponent(UIComponent parent, String libraryName, String resourceName, String id) {
		logger.debug("includeCompositeComponent");
		logger.debug(
				"parent " + parent + " libraryName " + libraryName + " resourceName " + resourceName + " id " + id);
		// Prepare.
		FacesContext context = FacesContext.getCurrentInstance();
		Application application = context.getApplication();
		FaceletContext faceletContext = (FaceletContext) context.getAttributes()
				.get(FaceletContext.FACELET_CONTEXT_KEY);

		// This basically creates <ui:component> based on <composite:interface>.
		Resource resource = application.getResourceHandler().createResource(resourceName, libraryName);
		UIComponent composite = application.createComponent(context, resource);
		composite.setId(id); // Mandatory for the case composite is part of
		// UIForm! Otherwise JSF can't find inputs.

		// This basically creates <composite:implementation>.
		UIComponent implementation = application.createComponent(UIPanel.COMPONENT_TYPE);
		implementation.setRendererType("javax.faces.Group");
		composite.getFacets().put(UIComponent.COMPOSITE_FACET_NAME, implementation);
		// composite.

		UIComponent findComponent = faceletContext.getFacesContext().getViewRoot().findComponent(id);
		if (findComponent != null) {
			findComponent.getParent().getChildren().remove(findComponent);
			findComponent.setParent(null);
		}
		// Now include the composite component file in the given parent.

		parent.getChildren().add(composite);
		parent.pushComponentToEL(context, composite); // This makes #{cc}
		// available.

		try {
			// composite.getAttributes().put("success",
			// "/admins/admins?faces-redirect=true");

			faceletContext.includeFacelet(implementation, resource.getURL());
		} catch (IOException e) {
			logger.error(e);
			throw new FacesException(e);
		} finally {
			parent.popComponentFromEL(context);
		}
	}

//    @Override
//    public void auditAction(String actionResult, String actionValue) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

	public UIComponent getInputForm() {
		return inputForm;
	}

	public void setInputForm(UIComponent inputForm) {
		this.inputForm = inputForm;
	}

	public List<UIMenu> getUiMenuPanel() {
		return uiMenuPanel;
	}

	public void setUiMenuPanel(List<UIMenu> uiMenuPanel) {
		this.uiMenuPanel = uiMenuPanel;
	}

}
