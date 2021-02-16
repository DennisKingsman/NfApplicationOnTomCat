package com.trainig.spring.main.project.service;

import com.trainig.spring.main.project.entity.Addressee;
import com.trainig.spring.main.project.repository.AddresseeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddresseeServiceImpl implements AddresseeService{

    @Autowired
    private AddresseeRepository addresseRepository;

    @Override
    public List<Addressee> getAllAddressee() {
        return addresseRepository.getAll();
    }

    @Override
    public Addressee getAddresseeById(long addresseeId) {
        return addresseRepository.getById(addresseeId);
    }

    @Override
    public synchronized boolean addAddressee(Addressee addressee) {
        if (addresseRepository.isAddresseeExists(
                addressee.getAddresseeName(),
                addressee.getAddresseeEmail())) {
            return false;
        } else {
            addresseRepository.createAddressee(addressee);
            return true;
        }
    }

    @Override
    public boolean updateAddressee(Addressee addressee) {
        if (addresseRepository.isAddresseeExists(addressee.getAddresseeId())) {
            return false;
        } else {
            return addresseRepository.updateAddressee(addressee) != 0;
        }
    }

    @Override
    public boolean deleteAddressee(long addresseeId) {
        return addresseRepository.deleteAddressee(addresseeId) != 0;
    }

}
