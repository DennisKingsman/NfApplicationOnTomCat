package com.trainig.spring.main.project.repository;

import com.trainig.spring.main.project.entity.Addressee;
import com.trainig.spring.main.project.mapper.AddresseeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class AddresseRepositoryImpl implements AddresseRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public long createAddressee(Addressee addressee) {
        String sqlRequest = "insert into addressee (addressee_email, addressee_name) " +
                "values (?, ?) returning addressee_id";
        return jdbcTemplate.queryForObject(sqlRequest, Long.class,
                addressee.getAddresseeEmail(),
                addressee.getAddresseeName());
    }

    @Override
    public int updateAddressee(Addressee addressee) {
        String sqlRequest = "update addressee set " +
                "addressee_email = ?, addressee_name = ? where addressee_id = ?";
        return jdbcTemplate.update(sqlRequest,
                addressee.getAddresseeEmail(),
                addressee.getAddresseeName(),
                addressee.getAddresseeId());
    }

    @Override
    public int deleteAddressee(long addresseeId) {
        String sqlRequest = "delete from addressee where addressee_id = ?";
        return jdbcTemplate.update(sqlRequest, addresseeId);
    }

    @Override
    public boolean isAddresseeExists(String name, String email) {
        String sqlRequest = "select count(addressee_id) from addressee " +
                "where addressee_email = ? and addressee_name = ?";
        Integer count = jdbcTemplate.queryForObject(sqlRequest, Integer.class, email, name);
        return count != 0;
    }

    @Override
    public Addressee getById(Long id) {
        String sqlRequest = "select addressee_id, addressee_email, addressee_name " +
                "from addressee where addressee_id = ?";
        Addressee addressee = jdbcTemplate.queryForObject(sqlRequest, new AddresseeRowMapper(), id);
        return addressee == null ? new Addressee() : addressee;
    }

    @Override
    public List<Addressee> getAll() {
        String sqlRequest = "select addressee_id, addressee_email, addressee_name from addressee";
        return jdbcTemplate.query(sqlRequest, new AddresseeRowMapper());
    }
}
