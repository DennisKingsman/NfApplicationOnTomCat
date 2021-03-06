package com.trainig.spring.main.project.repository.user;

import com.trainig.spring.main.project.entity.Role;
import com.trainig.spring.main.project.entity.User;
import com.trainig.spring.main.project.mapper.UserRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Transactional
@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final String SELECT_ALL = "select user_id, user_name, user_password from user_table";
    private static final String FIND_BY_NAME = "select ut.user_id, ut.user_name, ut.user_password," +
            " user_role.role_id, role_name from \n" +
            "user_role inner join user_to_role\n" +
            "on user_role.role_id = user_to_role.role_id \n" +
            "inner join user_table as ut\n" +
            "on ut.user_id = user_to_role.user_id\n" +
            "where ut.user_name = ?";
    private static final String FIND_BY_ID = SELECT_ALL + " where user_id = ?";
    private static final String SAVE_USER_WITH_ROLE = "with id_table as(\n" +
            "insert into user_table(user_name, user_password)\n" +
            "\tvalues (?, ?) returning user_id\n" + //userName userPassword
            ")insert into user_to_role(user_id, role_id)" +
            " select user_id, ? from id_table"; //role_id
    private static final String DELETE_USER = "delete from user_table where user_id = ?";
    private static final String UPDATE_USER = "update user_table set user_name = ?," +
            " user_password = ? where user_id = ?";
    private static final String USER_EXISTS_WITH_NAME = "select count(user_id) from user_table where user_name = ?";
    private static final String SAVE_USER = "insert into user_table(user_name, user_password)" +
            " values (?, ?) returning user_id"; //userName userPassword
    private static final String SET_FOREIGN_KEY = "insert into user_to_role (user_id, role_id) values (?, ?)";

    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User findByName(String userName) {
        logger.info("find by name : {}", userName);
        User user = jdbcTemplate.queryForObject(
                FIND_BY_NAME,
                (rs, rowNum) -> new User(
                        rs.getLong("user_id"),
                        rs.getString("user_name"),
                        rs.getString("user_password"),
                        Collections.singleton(new Role(
                                rs.getLong("role_id"),
                                rs.getString("role_name")
                        ))),
                userName);
        if (Objects.isNull(user)) {
            logger.info("Empty user");
            return new User();
        } else {
            return user;
        }
    }

    @Override
    public User findById(long userId) {
        User user = jdbcTemplate.queryForObject(FIND_BY_ID, new UserRowMapper(), userId);
        return user == null ? new User() : user;
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(SELECT_ALL, new UserRowMapper());
    }

    /**
     * saving new user with use of CTE
     * @apiNote non tested method
     * @param user user entity
     * @return isSuccessful?
     */
    @Override
    public boolean saveUserWithRole(User user) {
        Role role = user.getRoles().iterator().next();
        logger.info("role id is {}", role.getRoleId());
        int updatedRow = jdbcTemplate.update(
                SAVE_USER_WITH_ROLE,
                user.getUsername(),
                user.getPassword(),
                role.getRoleId());
        return updatedRow != 0;
    }

    /**
     * @deprecated
     * (tried to get alternative to saveUserWithRole, but h2 fails on insert returning)
     * @param entity user
     * @return userId
     */
    @Override
    public long save(User entity) {
        return jdbcTemplate.queryForObject(
                SAVE_USER,
                Long.TYPE,
                entity.getUserName(),
                entity.getPassword());
    }

    /**
     * @deprecated
     * (tried to get alternative to saveUserWithRole, but h2 fails on insert returning)
     * @param entity user
     * @return isSuccessful?
     */
    @Deprecated
    @Override
    public int setForeignKey(User entity) {
        Role role = entity.getRoles().iterator().next();
        return jdbcTemplate.update(
                SET_FOREIGN_KEY,
                entity.getUserId(),
                role.getRoleId());
    }

    @Override
    public int delete(long userId) {
        return jdbcTemplate.update(DELETE_USER, userId);
    }

    @Override
    public int update(User user) {
        return jdbcTemplate.update(UPDATE_USER,
                user.getUserName(),
                user.getPassword(),
                user.getUserId());
    }

    @Override
    public boolean isExists(String name) {
        int confirm = jdbcTemplate.queryForObject(USER_EXISTS_WITH_NAME, Integer.TYPE, name);
        return confirm > 0;
    }

}
