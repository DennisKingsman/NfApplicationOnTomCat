package com.trainig.spring.main.project.service.user;

import com.trainig.spring.main.project.entity.Role;
import com.trainig.spring.main.project.entity.User;
import com.trainig.spring.main.project.repository.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.trainig.spring.main.project.utils.Constants.ROLE_ADMIN;
import static com.trainig.spring.main.project.utils.Constants.USER_ROLE;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByName(userName);
        return checkUserWithException(user);
    }

    @Override
    public User getByName(String userName) {
        return userRepository.findByName(userName);
    }

    private User checkUserWithException(User user) {
        log.debug("check user");
        if (Objects.isNull(user.getUserId())) {
            throw new UsernameNotFoundException("User not found");
        } else {
            return user;
        }
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public boolean save(User user) {
        boolean confirm = userRepository.isExists(user.getUserName());
        log.info("does this user already registered: {}", confirm);
        if (confirm) {
            return false;
        } else if (user.getUserName().equals("admin")) {
            user.setRoles(Collections.singleton(new Role(2L, ROLE_ADMIN)));
        } else {
            user.setRoles(Collections.singleton(new Role(1L, USER_ROLE)));
        }
        User userToDB = new User(user);
        userToDB.setUserPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        log.info("userToDB: {}", userToDB);
        userRepository.saveUserWithRole(userToDB);
        return true;
    }

    @Override
    public boolean delete(long userId) {
        return userRepository.delete(userId) == 1;
    }

    @Override
    public boolean update(User user) {
        User userFromDB = userRepository.findById(user.getUserId());
        User userToDB = new User(user);
        log.info("user From DB : {}", userFromDB);
        log.info("user To DB : {}", userToDB);
        if (Objects.isNull(userFromDB.getUserId())) {
            return false;
        } else if (!userFromDB.getPassword().equals(userToDB.getPassword())) {
            log.info("New Password settled");
            userToDB.setUserPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        return userRepository.update(userToDB) == 1;
    }

}
