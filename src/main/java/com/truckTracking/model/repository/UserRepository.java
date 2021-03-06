package com.truckTracking.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.truckTracking.model.entities.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(name = User.NAMED_QUERY_USER_FIND_USER_BY_USERNAME)
	List<User> findAllUsersByUserName(@Param("userName") String userName);

	@Query(value = "SELECT u FROM User u where u.userName = ?1 and u.userPasswordID.password = ?2 ")
	Optional<User> login(String username, String password);

	Optional<User> findByToken(String token);

}
