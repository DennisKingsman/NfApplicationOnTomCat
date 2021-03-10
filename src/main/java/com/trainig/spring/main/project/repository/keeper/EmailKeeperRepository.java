package com.trainig.spring.main.project.repository.keeper;

import com.trainig.spring.main.project.entity.EmailKeeper;

public interface EmailKeeperRepository {

    EmailKeeper getSchedulerEmail(String name);

}
