package com.trainig.spring.main.project.repository.addressee;

import com.trainig.spring.main.project.entity.Addressee;
import com.trainig.spring.main.project.mapper.AddresseeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Transactional
@Repository
public class AddresseRepositoryImpl implements AddresseeRepository {

    private static final String SAVE_ADDRESSEE_WITH_USER = "with id_table as(\n" +
            "\tinsert into addressee(addressee_email, addressee_name)\n" +
            "\tvalues (?, ?)\n" + // addressee_email, addressee_name
            "\treturning addressee_id\n" +
            ") insert into user_to_addressee(user_id, addressee_id)" +
            " select ?, id_table.addressee_id from id_table;"; // user_id
    private static final String UPDATE_ADDRESSEE_BY_ID = "update addressee set " +
            "addressee_email = ?, addressee_name = ? where addressee_id = ?";
    private static final String DELETE_ADDRESSEE = "delete from addressee where addressee_id = ?";
    private static final String ADDRESSEE_EXISTS_WITH_ATTRIBUTES = "select count(addressee_id) from addressee " +
            "where addressee_email = ? and addressee_name = ?";
    private static final String GET_ALL_BY_USER_ID = "select addressee.addressee_id," +
            " addressee_email, addressee_name from\n" +
            "addressee inner join user_to_addressee as uta\n" +
            "on uta.addressee_id = addressee.addressee_id\n" +
            "inner join user_table as ut\n" +
            "on uta.user_id = ut.user_id \n" +
            "where ut.user_id = ?;";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int update(Addressee addressee) {
        return jdbcTemplate.update(UPDATE_ADDRESSEE_BY_ID,
                addressee.getAddresseeEmail(),
                addressee.getAddresseeName(),
                addressee.getAddresseeId());
    }

    /**
     * non tested method with CTE and insert returning
     * @param userId userId
     * @param addressee addressee entity
     * @return saved?
     */
    @Override
    public boolean saveAddresseeWithUser(Long userId, Addressee addressee) {
        int updatedRow = jdbcTemplate.update(
                SAVE_ADDRESSEE_WITH_USER,
                addressee.getAddresseeEmail(),
                addressee.getAddresseeName(),
                userId);
        return updatedRow != 0;
    }

    @Override
    public List<Addressee> getAllByUserId(long userId) {
        return jdbcTemplate.query(GET_ALL_BY_USER_ID, new AddresseeRowMapper(), userId);
    }

    @Override
    public int delete(long addresseeId) {
        return jdbcTemplate.update(DELETE_ADDRESSEE, addresseeId);
    }

    @Override
    public boolean isAddresseeExists(String name, String email) {
        Integer count = jdbcTemplate.queryForObject(ADDRESSEE_EXISTS_WITH_ATTRIBUTES, Integer.class, email, name);
        return count != 0;
    }

}
