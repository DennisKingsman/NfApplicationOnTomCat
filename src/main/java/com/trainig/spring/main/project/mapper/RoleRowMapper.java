package com.trainig.spring.main.project.mapper;

import com.trainig.spring.main.project.entity.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet resultSet, int i) throws SQLException {
        Role role = new Role();
        role.setRoleId(resultSet.getLong("role_id"));
        role.setRoleName(resultSet.getString("role_name"));
        return role;
    }

}
