package com.vnpt.auth.repository;

import java.util.List;
import java.util.Map;

/**
 * UserFunctionRepositoryJdbc
 */
public interface UserFunctionRepositoryJdbc {
    public List<String> findAllByUserRoleId(Integer userRoleId);
}