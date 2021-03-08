package com.trainig.spring.main.project.service.user;

import com.trainig.spring.main.project.entity.User;
import com.trainig.spring.main.project.service.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService, Service<User> {

    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;

    User getByName(String userName);

}
