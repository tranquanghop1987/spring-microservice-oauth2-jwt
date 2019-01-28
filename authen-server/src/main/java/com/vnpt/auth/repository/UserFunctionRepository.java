package com.vnpt.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vnpt.auth.entity.UserFunction;

public interface UserFunctionRepository extends CrudRepository<UserFunction, Long> {
	
	@Query(value = "SELECT u FROM UserFunction u INNER JOIN FunctionRole f ON u.userFunctionId = f.id.userFunctionId WHERE f.id.userRoleId = :userRoleId")
	public List<UserFunction> findAllByUserRoleId(@Param("userRoleId") Long userRoleId);
	
	@Query(value = "SELECT u FROM UserFunction u WHERE u.userFunctionName like %:keyword%")
	public List<UserFunction> searchByKeyword(@Param("keyword") String keyword);
	
	public UserFunction findOneByUserFunctionId(Long userFunctionId);
}
