package com.trainig.spring.main.project.repository.keeper;

import com.trainig.spring.main.project.entity.EmailKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class EmailKeeperRepositoryImpl implements EmailKeeperRepository {

    private static final String GET_SCHEDULED_PROPERTIES = "select mail, scheduler_password\n" +
            "from email_scheduler where scheduler_name = ?;";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public EmailKeeper getSchedulerEmail(String name) {
        return jdbcTemplate.queryForObject(GET_SCHEDULED_PROPERTIES,
                (rs, i) -> new EmailKeeper(
                        name,
                        rs.getString("mail"),
                        rs.getString("scheduler_password")),
                name);
    }

}
