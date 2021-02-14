package com.trainig.spring.repository;

import com.trainig.spring.beans.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * The NamedParameterJdbcTemplate adds support for named parameters
 * in steads of classic placeholder ? argument.
 */
@Repository
public class NamedParameterJdbcBookRepository extends JdbcBookRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int update(Book book) {
        return namedParameterJdbcTemplate.update(
                "update books set price = :price where id = :id",
                new BeanPropertySqlParameterSource(book));
    }

    @Override
    public Optional<Book> findById(Long id) {
        return namedParameterJdbcTemplate.queryForObject(
                "select * from books where id = :id",
                new MapSqlParameterSource(ID, id),
                (rs, rowNum) ->
                        Optional.of(new Book(rs.getLong(ID),
                                rs.getString(NAME),
                                rs.getBigDecimal(PRICE))));
    }

    @Override
    public List<Book> findByNameAndPrice(String name, BigDecimal price) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(NAME, "%" + name + "%");
        mapSqlParameterSource.addValue(JdbcBookRepository.PRICE, price);

        return namedParameterJdbcTemplate.query(
                "select * from books where name like :name and price <= :price",
                mapSqlParameterSource,
                (rs, rowNum) ->
                        new Book(rs.getLong(ID),
                                rs.getString(NAME),
                                rs.getBigDecimal(JdbcBookRepository.PRICE)));
    }

}
