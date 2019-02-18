package com.vnpt.auth.repository;

import java.util.List;
import java.util.Map;

/**
 * VnptUserRepositoryJdbc
 */
public interface VnptUserRepositoryJdbc {
    public Map<String,Object> findOneByUsername(String username);
}