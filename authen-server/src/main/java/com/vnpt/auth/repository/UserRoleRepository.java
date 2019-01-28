package com.vnpt.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vnpt.auth.entity.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Long>  {
	public UserRole findOneByUserRoleId(Long userRoleId);
	
	@Query(value = "SELECT u FROM UserRole u WHERE u.userRoleName like %:keyword%")
	public List<UserRole> searchByKeyword(@Param("keyword") String keyword);
}
