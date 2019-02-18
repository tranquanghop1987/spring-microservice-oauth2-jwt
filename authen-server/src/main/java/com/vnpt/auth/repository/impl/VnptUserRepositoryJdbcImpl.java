package com.vnpt.auth.repository.impl;

import java.util.List;
import java.util.Map;

import com.vnpt.auth.repository.VnptUserRepositoryJdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * VnptUserRepositoryImpl
 */
@Repository
public class VnptUserRepositoryJdbcImpl implements VnptUserRepositoryJdbc {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Map<String, Object> findOneByUsername(String username) {
        String sql = "SELECT * FROM vnpt_user WHERE username = ?";
        List<Map<String,Object>> rows = jdbcTemplate.queryForList(sql, username);
        Map<String, Object> vnptUser = rows.get(0);
        return vnptUser;
    }
}