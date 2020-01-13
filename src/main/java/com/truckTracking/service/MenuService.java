/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truckTracking.service;

import java.util.List;

import com.truckTracking.model.entities.Menu;

/**
 *
 * @author mohammed.ayad
 */

public interface MenuService {
	public List<Menu> getUserRootMenus();

	public List<Menu> getAllMenus();

}
