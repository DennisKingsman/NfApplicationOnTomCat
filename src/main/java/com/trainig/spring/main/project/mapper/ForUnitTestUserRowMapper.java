package com.trainig.spring.main.project.mapper;

import com.trainig.spring.main.project.entity.Role;
import com.trainig.spring.main.project.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

public class ForUnitTestUserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getLong("user_id"));
        user.setUserName(resultSet.getString("user_name"));
        user.setUserPassword(resultSet.getString("user_password"));
        user.setRoles(Collections.singleton(
                new Role(
                        resultSet.getLong("role_id"),
                        resultSet.getString("role_name"))
        ));
        return user;
    }

}
