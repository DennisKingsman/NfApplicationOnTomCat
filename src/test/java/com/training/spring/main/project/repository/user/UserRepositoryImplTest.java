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
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.*;
import static com.trainig.spring.main.project.repository.user.UserRepositoryImpl.FIND_BY_NAME;
import static com.training.spring.main.project.utils.ModelUtilForTest.setupUser;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserRepositoryImplTest {

    private static final Logger log = LoggerFactory.getLogger(UserRepositoryImplTest.class);

    @Mock
    JdbcTemplate jdbcTemplate;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findByNameTest() {
        User user = setupUser();
        String userName = user.getUserName();
        UserRepository userRepository = new UserRepositoryImpl();
        ReflectionTestUtils.setField(userRepository, "jdbcTemplate", jdbcTemplate);
        Mockito.when(jdbcTemplate.queryForObject(
                FIND_BY_NAME,
                new ForUnitTestUserRowMapper(),
                userName))
                .thenReturn(user);
        assertEquals(user, userRepository.findByName(userName));
    }

}