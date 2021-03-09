package com.trainig.spring.main.project.service.keeper;

import com.trainig.spring.main.project.entity.EmailKeeper;
import com.trainig.spring.main.project.repository.keeper.EmailKeeperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeeperServiceImpl implements KeeperService {

    private EmailKeeperRepository emailKeeperRepository;

    @Autowired
    public void setEmailKeeperRepository(EmailKeeperRepository emailKeeperRepository) {
        this.emailKeeperRepository = emailKeeperRepository;
    }

    @Override
    public EmailKeeper getMailKeeper(String name) {
        return emailKeeperRepository.getSchedulerEmail(name);
    }

}
