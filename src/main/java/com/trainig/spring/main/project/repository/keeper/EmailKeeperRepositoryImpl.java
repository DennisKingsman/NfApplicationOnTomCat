package com.trainig.spring.main.project.repository.keeper;

import com.trainig.spring.main.project.entity.EmailKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmailKeeperRepositoryImpl implements EmailKeeperRepository {

    public static final String GET_SCHEDULED_PROPERTIES = "select mail, scheduler_password\n" +
            "from email_scheduler where scheduler_name = ?;";
    public static final String ADMIN = "admin";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public EmailKeeper getSchedulerEmail() {
        return jdbcTemplate.queryForObject(GET_SCHEDULED_PROPERTIES,
                (rs, i) -> new EmailKeeper(
                        ADMIN,
                        rs.getString("mail"),
                        rs.getString("scheduler_password")),
                ADMIN);
    }

}
