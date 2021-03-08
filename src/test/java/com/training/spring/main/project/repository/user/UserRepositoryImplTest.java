package com.training.spring.main.project.repository.user;

import com.trainig.spring.main.project.entity.User;
import com.trainig.spring.main.project.mapper.ForUnitTestUserRowMapper;
import com.trainig.spring.main.project.repository.user.UserRepository;
import com.trainig.spring.main.project.repository.user.UserRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.util.ReflectionTestUtils;

import javax.sql.DataSource;

import static org.mockito.Mockito.*;
import static com.trainig.spring.main.project.repository.user.UserRepositoryImpl.FIND_BY_NAME;
import static com.training.spring.main.project.utils.ModelUtilForTest.setupUser;
import static org.junit.Assert.assertEquals;

@SpringBootTest
public class UserRepositoryImplTest {

    private static final Logger log = LoggerFactory.getLogger(UserRepositoryImplTest.class);

    private DataSource dataSource;

    @Before
    public void initDataSource(){
        dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("init.sql")
                .addScript("insert.sql")
                .build();
    }

    @Test
    public void findByNameTestWithH2() {
        UserRepository userRepository = new UserRepositoryImpl();
        ((UserRepositoryImpl) userRepository).setDataSource(dataSource);
        User expected = setupUser();
        assertEquals(expected, userRepository.findByName(expected.getUserName()));
    }

}