package com.training.spring.main.project.service.user;

import com.trainig.spring.main.project.repository.user.UserRepository;
import com.trainig.spring.main.project.service.user.UserService;
import com.trainig.spring.main.project.service.user.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertEquals;


public class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testValidDelete() {
        long validUserId = 1;
        UserService userService = new UserServiceImpl();
        ReflectionTestUtils.setField(userService, "userRepository", userRepository);
        Mockito.when(userRepository.delete(validUserId)).thenReturn(1);
        assertEquals(1, userRepository.delete(validUserId));
    }

}
