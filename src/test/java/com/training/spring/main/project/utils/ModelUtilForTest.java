package com.training.spring.main.project.utils;

import com.trainig.spring.main.project.entity.Role;
import com.trainig.spring.main.project.entity.User;

import java.util.Collections;

public class ModelUtilForTest {

    private static final long ID = 1L;
    private static final String USER_NAME = "name";
    private static final String PASSWORD = "password";
    private static final String USER_ROLE = "ROLE";

    public static User setupUser() {
        User user = new User();
        user.setUserId(ID);
        user.setUserName(USER_NAME);
        user.setUserPassword(PASSWORD);
        user.setRoles(Collections.singleton(new Role(ID, USER_ROLE)));
        return user;
    }

}
