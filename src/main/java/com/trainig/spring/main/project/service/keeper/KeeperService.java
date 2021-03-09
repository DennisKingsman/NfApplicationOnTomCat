package com.trainig.spring.main.project.service.keeper;

import com.trainig.spring.main.project.entity.EmailKeeper;
import com.trainig.spring.main.project.repository.keeper.EmailKeeperRepository;

public interface KeeperService {

    EmailKeeper getMailKeeper(String name);

    void setEmailKeeperRepository(EmailKeeperRepository emailKeeperRepository);

}
