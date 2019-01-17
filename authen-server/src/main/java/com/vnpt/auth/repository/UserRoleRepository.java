package com.vnpt.auth.repository;

import org.springframework.data.repository.CrudRepository;

import com.vnpt.auth.entity.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Long>  {
	public UserRole findOneByUserRoleId(Long userRoleId);
}
