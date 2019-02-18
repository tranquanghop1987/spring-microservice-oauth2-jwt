package com.vnpt.auth.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.vnpt.auth.repository.UserFunctionRepositoryJdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * UserFunctionRepositoryJdbc
 */
@Repository
public class UserFunctionRepositoryJdbcImpl implements UserFunctionRepositoryJdbc {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<String> findAllByUserRoleId(Integer userRoleId) {
        String sql = "SELECT user_function.user_function_id, user_function.user_function_name "+
        " FROM user_function INNER JOIN function_role ON user_function.user_function_id = function_role.user_function_id "+
        " WHERE function_role.user_role_id = ?";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, userRoleId);
        List<String> returList = new ArrayList<>();
        for(Map<String, Object> item : rows){
            String userFuntionName = (String)item.get("user_function_name");
            returList.add(userFuntionName);
        }
        // rows.forEach(item->{
        //     returList.add((String)item.get("user_funtion_name"));
        // });
        return returList;
    }
    
}