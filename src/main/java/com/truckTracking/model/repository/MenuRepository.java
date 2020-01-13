package com.truckTracking.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.truckTracking.model.entities.Menu;

@Repository("menuRepository")
public interface MenuRepository extends JpaRepository<Menu, Integer> {

//	@Query(Menu.NAMED_QUERY_MENU_FIND_MENU_BY_MODEL_TYPE)
//	List<Menu> findAllMenusByModelType(@Param("modelType") String modelType);

}
