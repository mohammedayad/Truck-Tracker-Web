/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truckTracking.model.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mohammed.ayad
 */
@Entity
@Table(name = "menu")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m"),
		@NamedQuery(name = "Menu.findByMenuId", query = "SELECT m FROM Menu m WHERE m.menuId = :menuId"),
		@NamedQuery(name = "Menu.findByMenuName", query = "SELECT m FROM Menu m WHERE m.menuName = :menuName"),
		@NamedQuery(name = "Menu.findByLink", query = "SELECT m FROM Menu m WHERE m.link = :link"),
		@NamedQuery(name = Menu.NAMED_QUERY_MENU_FIND_MENU_BY_MODEL_TYPE, query = "SELECT m FROM Menu m WHERE m.modelType = :modelType"),
//		@NamedQuery(name = "Menu.findByParentId", query = "SELECT m FROM Menu m WHERE m.parentId = :parentId"),
		@NamedQuery(name = "Menu.findByMenuOrder", query = "SELECT m FROM Menu m WHERE m.menuOrder = :menuOrder") })
public class Menu implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String NAMED_QUERY_MENU_FIND_MENU_BY_MODEL_TYPE = "Menu.findByModelType";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "menu_id")
	private Integer menuId;
	@Basic(optional = false)
	@Column(name = "menu_name")
	private String menuName;
	@Basic(optional = false)
	@Column(name = "link")
	private String link;
	@Column(name = "model_type")
	private String modelType;
//	@Column(name = "parent_id", insertable = false, updatable = false)
//	@Basic(optional = true)
//	private Integer parentId;
	@Column(name = "menu_order")
	private Integer menuOrder;
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	@ManyToOne(optional = false)
	private User userId;

	@OneToMany(mappedBy = "parentMenu", fetch = FetchType.LAZY)
	private List<Menu> childMenu;

	@JoinColumn(name = "parent_id", referencedColumnName = "menu_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Menu parentMenu;

	public Menu() {
	}

	public Menu(Integer menuId) {
		this.menuId = menuId;
	}

	public Menu(Integer menuId, String menuName, String link) {
		this.menuId = menuId;
		this.menuName = menuName;
		this.link = link;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

//	public Integer getParentId() {
//		return parentId;
//	}
//
//	public void setParentId(Integer parentId) {
//		this.parentId = parentId;
//	}

	public Integer getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public List<Menu> getChildMenu() {
		return childMenu;
	}

	public void setChildMenu(List<Menu> childMenu) {
		this.childMenu = childMenu;
	}

	public Menu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (menuId != null ? menuId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Menu)) {
			return false;
		}
		Menu other = (Menu) object;
		if ((this.menuId == null && other.menuId != null)
				|| (this.menuId != null && !this.menuId.equals(other.menuId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "truckTracker.Menu[ menuId=" + menuId + " ]";
	}

}
