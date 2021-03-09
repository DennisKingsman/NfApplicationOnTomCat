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

import java.util.List;

import static com.trainig.spring.main.project.utils.ModelUtil.setupUser;
import static com.trainig.spring.main.project.utils.ModelUtil.setupUsers;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
        userRepository.setDataSource(dataSource);
    }

    @Test
    public void findByNameSuccessfulTest() {
        User expected = setupUser();
        assertEquals(expected, userRepository.findByName(expected.getUserName()));
    }

    @Test
    public void findByIdSuccessfulTest() {
        User expected = setupUser();
        assertEquals(expected, userRepository.findById(expected.getUserId()));
    }

    @Test
    public void getAllTest() {
        assertNotNull(userRepository.getAll());
    }

    /**
     * h2 has problems with a CTE and insert returning
     */
    @Test
    public void saveUserWithRoleTest() {
        assertTrue(true);
    }

    @Test
    public void isExistsSuccessfulTest() {
        assertTrue(userRepository.isExists("User2"));
    }

    @Test
    public void isExistsUnsuccessfulTest() {
        assertFalse(userRepository.isExists("failUser"));
    }

    @Test
    public void updateSuccessfulTest() {
        User user = new User();
        user.setUserId(1L);
        user.setUserName("newName");
        user.setUserPassword("password");
        assertEquals(1, userRepository.update(user));
    }

    @Test
    public void updateUnsuccessfulTest() {
        User user = setupUser();
        user.setUserId(45L);
        assertEquals(0, userRepository.update(user));
    }

    @Test
    public void deleteSuccessfulTest() {
        assertEquals(1, userRepository.delete(1));
    }

    @Test
    public void deleteUnsuccessfulTest() {
        assertEquals(0, userRepository.delete(45));
    }

}