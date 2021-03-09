package com.trainig.spring.main.project.service.addressee;

import com.trainig.spring.main.project.entity.Addressee;
import com.trainig.spring.main.project.repository.addressee.AddresseeRepository;
import com.trainig.spring.main.project.service.Service;

import java.util.List;

public interface AddresseeService extends Service<Addressee> {

    boolean saveUserAddressee(long userId, Addressee addressee);

    List<Addressee> getAllByUserId(long userId);

    void setAddresseeRepository(AddresseeRepository addresseeRepository);

}
