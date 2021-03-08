package com.trainig.spring.main.project.repository.user;

import com.trainig.spring.main.project.entity.Role;
import com.trainig.spring.main.project.entity.User;
import com.trainig.spring.main.project.repository.Repository;

import java.util.Set;

public interface UserRepository extends Repository<User> {

    boolean saveUserWithRole(User user);

}
