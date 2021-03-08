package com.trainig.spring.main.project.repository.user;

import com.trainig.spring.main.project.entity.User;
import com.trainig.spring.main.project.repository.Repository;

public interface UserRepository extends Repository<User> {

    boolean saveUserWithRole(User user);

}
