package com.trainig.spring.main.project.utils;

import com.trainig.spring.main.project.entity.Addressee;
import com.trainig.spring.main.project.entity.EmailKeeper;
import com.trainig.spring.main.project.entity.Role;
import com.trainig.spring.main.project.entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModelUtil {

    private ModelUtil() { }

    private static final String USER_NAME = "User";
    private static final String PASSWORD = "Password";
    private static final String USER_ROLE = "ROLE_USER";
    private static final long USER_ROLE_ID = 2L;

    public static User setupUser(long i) {
        User user = new User();
        user.setUserId(i + 1);
        user.setUserName(USER_NAME + i);
        user.setUserPassword(PASSWORD + i);
        user.setRoles(Collections.singleton(new Role(USER_ROLE_ID, USER_ROLE)));
        return user;
    }

    public static Addressee setupAddressee() {
        Addressee addressee = new Addressee();
        addressee.setAddresseeName("Name");
        addressee.setAddresseeEmail("Email");
        addressee.setAddresseeId(1);
        return addressee;
    }

    public static EmailKeeper setupEmailKeeper() {
        EmailKeeper emailKeeper = new EmailKeeper();
        emailKeeper.setKeeperName("scheduledName");
        emailKeeper.setKeeperEmail("scheduledMail");
        emailKeeper.setKeeperPassword("scheduledPassword");
        return emailKeeper;
    }

}
