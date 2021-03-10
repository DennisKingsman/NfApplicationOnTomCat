package com.training.spring;

import com.training.spring.main.project.controller.rest.AddresseeRestControllerTest;
import com.training.spring.main.project.controller.rest.MainRestControllerTest;
import com.training.spring.main.project.repository.addressee.AddresseeRepositoryImplTest;
import com.training.spring.main.project.repository.keeper.EmailKeeperRepositoryImplTest;
import com.training.spring.main.project.repository.user.UserRepositoryImplTest;
import com.training.spring.main.project.service.addressee.AddresseeServiceImplTest;
import com.training.spring.main.project.service.keeper.KeeperServiceImplTest;
import com.training.spring.main.project.service.user.UserServiceImplTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

//@RunWith(Suite.class)
//@Suite.SuiteClasses({
//        AddresseeRestControllerTest.class,
//        MainRestControllerTest.class,
//        AddresseeRepositoryImplTest.class,
//        EmailKeeperRepositoryImplTest.class,
//        UserRepositoryImplTest.class,
//        AddresseeServiceImplTest.class,
//        UserServiceImplTest.class,
//        KeeperServiceImplTest.class
//})
public class ApplicationTest {

    @Test
    public void contextLoads() {
    }

}
