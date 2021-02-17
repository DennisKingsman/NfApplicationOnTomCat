package com.trainig.spring.examples.repository;

import com.trainig.spring.examples.beans.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcBookRepository implements BookRepository {

    static final String PRICE = "price";
    static final String ID = "id";
    static final String NAME = "name";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        Integer result = jdbcTemplate.queryForObject("select count(*) from books", Integer.class);
        return result == null ? 0 : result;
    }

    @Override
    public int save(Book book) {
        return jdbcTemplate.update("insert into books (name, price) values(?,?)",
                book.getName(),
                book.getPrice());
    }

    @Override
    public int update(Book book) {
        return jdbcTemplate.update("update books set price = ? where id = ?",
                book.getPrice(),
                book.getId());
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update(
                "delete books where id = ?",
                id);
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query("select * from books",
                (rs, rowNum) ->
                        new Book(rs.getLong(ID),
                                rs.getString(NAME),
                                rs.getBigDecimal(PRICE)));
    }

    //like sql?
    @Override
    public List<Book> findByNameAndPrice(String name, BigDecimal price) {
        return jdbcTemplate.query("select * from books where name like ? and price <= ?",
                new Object[]{"%" + name + "%" + price},
                (rs, rowNum) ->
                        new Book(rs.getLong(ID),
                                rs.getString(NAME),
                                rs.getBigDecimal(PRICE)));
    }

    @Override
    public Optional<Book> findById(Long id) {
        return jdbcTemplate.queryForObject("select * from books where id = ?",
                new Object[]{id},
                (rs, rowNum) ->
                        Optional.of(new Book(rs.getLong(ID),
                                rs.getString(NAME),
                                rs.getBigDecimal(PRICE))));
    }

    @Override
    public String getNameById(Long id) {
        return jdbcTemplate.queryForObject("select name from books where id = ?",
                new Object[]{id}, String.class);
    }

}
