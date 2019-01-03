package com.vnpt.auth.repository;

import org.springframework.data.repository.CrudRepository;

import com.vnpt.auth.entity.VnptUser;


public interface VnptUserRepository extends CrudRepository<VnptUser, Long> {
	public VnptUser findOneByUsername(String username);
}
