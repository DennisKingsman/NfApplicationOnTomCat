package com.trainig.spring.main.project.mapper;

import com.trainig.spring.main.project.entity.Addressee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddresseeRowMapper implements RowMapper<Addressee> {

    @Override
    public Addressee mapRow(ResultSet resultSet, int i) throws SQLException {
        Addressee addressee = new Addressee();
        addressee.setAddresseeId(resultSet.getLong("addressee_id"));
        addressee.setAddresseeEmail(resultSet.getString("addressee_email"));
        addressee.setAddresseeName(resultSet.getString("addressee_name"));
        return addressee;
    }

}
