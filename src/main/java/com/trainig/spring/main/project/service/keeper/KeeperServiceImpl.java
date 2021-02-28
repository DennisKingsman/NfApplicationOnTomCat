package com.trainig.spring.main.project.service.keeper;

import com.trainig.spring.main.project.entity.EmailKeeper;
import com.trainig.spring.main.project.repository.keeper.EmailKeeperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeeperServiceImpl implements KeeperService {

    @Autowired
    private EmailKeeperRepository emailKeeperRepository;

    @Override
    public EmailKeeper getMailKeeper() {
        return emailKeeperRepository.getSchedulerEmail();
    }

}
