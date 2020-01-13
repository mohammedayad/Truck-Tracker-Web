/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truckTracking.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truckTracking.common.utils.Logger;
import com.truckTracking.model.entities.Menu;
import com.truckTracking.model.repository.MenuRepository;
import com.truckTracking.service.MenuService;

/**
 *
 * @author mohammed.ayad
 */

@Service("menuService")
public class MenuServiceImpl implements MenuService {

	private static final Logger logger = Logger.getLogger(MenuServiceImpl.class);

	@Autowired
	private MenuRepository menuRepository;

	@PostConstruct
	private void init() {
		logger.debug("MenuService has been initilized");

	}

	// Add business logic below. (Right-click in editor and choose
	// "Insert Code > Add Business Method")
	@Override
	public List<Menu> getUserRootMenus() {
		logger.debug("getUserRootMenus");
		List<Menu> menus = getAllMenus();
		List<Menu> rootMenus = new ArrayList<>();
		logger.debug("All Menus " + menus);
		for (Menu menu : menus) {
			if (menu.getLink().trim().equalsIgnoreCase("root")) {
				rootMenus.add(menu);
			}

		}
		logger.debug("Root Menus " + rootMenus);
		return rootMenus;
	}

	@Override
	public List<Menu> getAllMenus() {
		logger.debug("getAllMenus");
//		List<Menu> result = getNamedQueryResult(Menu.FIND_BY_MODEL_TYPE, "DRM");
		List<Menu> result = menuRepository.findAll();
		logger.debug(result);
		return result;
	}
}
