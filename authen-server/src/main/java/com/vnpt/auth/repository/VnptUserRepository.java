package com.vnpt.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vnpt.auth.entity.VnptUser;


public interface VnptUserRepository extends CrudRepository<VnptUser, String> {
	public VnptUser findOneByUsername(String username);
	
	@Query(value = "SELECT u FROM VnptUser u WHERE u.username like %:keyword% OR u.email like %:keyword%")
	public List<VnptUser> searchUsers(@Param("keyword")String keyword);
}
