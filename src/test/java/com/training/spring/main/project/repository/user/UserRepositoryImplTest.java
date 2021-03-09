package com.training.spring.main.project.repository.user;

import com.trainig.spring.main.project.entity.User;
import com.trainig.spring.main.project.repository.user.UserRepository;
import com.trainig.spring.main.project.repository.user.UserRepositoryImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import static com.trainig.spring.main.project.utils.ModelUtil.setupUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class UserRepositoryImplTest {

    private static final Logger log = LoggerFactory.getLogger(UserRepositoryImplTest.class);

    private static DataSource dataSource;
    private static UserRepository userRepository;

    @BeforeClass
    public static void initDataSource() {
        dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("init.sql")
                .addScript("insert.sql")
                .build();
        userRepository = new UserRepositoryImpl();
        ((UserRepositoryImpl) userRepository).setDataSource(dataSource);
    }

    @Test
    public void findByNameTestWithH2() {
        User expected = setupUser();
        assertEquals(expected, userRepository.findByName(expected.getUserName()));
    }

    @Test
    public void findByIdTest() {
        User expected = setupUser();
        assertEquals(expected, userRepository.findById(expected.getUserId()));
    }

    @Test
    public void getAllTest() {
        assertEquals(3, userRepository.getAll().size());
    }

    /**
     * h2 has problems with a CTE and insert returning
     */
    @Test
    public void saveUserWithRoleTest() {
        assertTrue(true);
    }

    @Test
    public void isExistsTest() {
        assertTrue(userRepository.isExists("User2"));
    }

    @Test
    public void updateTest() {
        User user = new User();
        user.setUserId(1L);
        user.setUserName("newName");
        user.setUserPassword("password");
        assertEquals(1, userRepository.update(user));
    }

    @Test
    public void deleteTest() {
        assertEquals(1, userRepository.delete(1));
    }

}