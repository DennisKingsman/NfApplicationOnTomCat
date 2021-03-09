package com.trainig.spring.main.project.utils;

import com.trainig.spring.main.project.entity.Role;
import com.trainig.spring.main.project.entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModelUtil {

    private static final String USER_NAME = "User";
    private static final String PASSWORD = "Password";
    private static final String USER_ROLE = "ROLE_USER";
    private static final String OH = "0";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final long USER_ROLE_ID = 2L;

    public static User setupUser() {
        User user = new User();
        user.setUserId(1L);
        user.setUserName(USER_NAME + OH);
        user.setUserPassword(PASSWORD + OH);
        user.setRoles(Collections.singleton(new Role(USER_ROLE_ID, USER_ROLE)));
        return user;
    }

    public static List<User> setupUsers() {
        List<User> users = new ArrayList<>();
        for (long i = 1; i < 4; i++) {
            User user = new User();
            user.setUserId(i);
            user.setUserName(USER_NAME + (i - 1));
            user.setUserPassword(PASSWORD + (i - 1));
            if (i == 3) {
                user.setRoles(Collections.singleton(new Role(3L, ROLE_ADMIN)));
            } else {
                user.setRoles(Collections.singleton(new Role(USER_ROLE_ID, USER_ROLE)));
            }
            users.add(user);
        }
        return users;
    }

}
