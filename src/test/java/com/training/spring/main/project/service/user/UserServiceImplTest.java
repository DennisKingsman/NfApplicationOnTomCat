package com.training.spring.main.project.service.user;

import com.trainig.spring.main.project.entity.User;
import com.trainig.spring.main.project.repository.user.UserRepository;
import com.trainig.spring.main.project.repository.user.UserRepositoryImpl;
import com.trainig.spring.main.project.service.user.UserService;
import com.trainig.spring.main.project.service.user.UserServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import static com.trainig.spring.main.project.utils.ModelUtil.setupUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    private static UserService userService;

    @BeforeClass
    public static void initDataSource() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("init.sql")
                .addScript("insert.sql")
                .build();
        UserRepository userRepository = new UserRepositoryImpl();
        userRepository.setDataSource(dataSource);
        userService = new UserServiceImpl();
        userService.setUserRepository(userRepository);
    }

    @Test
    public void loadUserByUsernameTest() {
        User expected = setupUser(1);
        String name = expected.getUserName();
        assertEquals(expected, userService.loadUserByUsername(name));
    }

    @Test
    public void getByNameTest() {
        User expected = setupUser(1);
        String name = expected.getUserName();
        assertEquals(expected, userService.getByName(name));
    }

    @Test
    public void getAllTest() {
        assertNotNull(userService.getAll());
    }

    @Test
    public void deleteTrueTest() {
        assertTrue(userService.delete(1));
    }

    @Test
    public void deleteFalseTest() {
        assertFalse(userService.delete(45));
    }

    @Test
    public void updateTrueTest() {
        User user = setupUser(0);
        user.setUserName("newName");
        assertTrue(userService.update(user));
    }

}
