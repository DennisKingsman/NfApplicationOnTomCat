package com.trainig.spring.main.project.service.addressee;

import com.trainig.spring.main.project.entity.Addressee;
import com.trainig.spring.main.project.repository.addressee.AddresseeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddresseeServiceImpl implements AddresseeService {

    private static final Logger log = LoggerFactory.getLogger(AddresseeServiceImpl.class);

    @Autowired
    private AddresseeRepository addresseRepository;

    @Override
    public List<Addressee> getAll() {
        return addresseRepository.getAll();
    }

    @Override
    public Addressee findById(long addresseeId) {
        return addresseRepository.findById(addresseeId);
    }

    @Override
    public boolean saveUserAddressee(long userId, Addressee addressee) {
        if (addresseRepository.isAddresseeExists(
                addressee.getAddresseeName(),
                addressee.getAddresseeEmail())) {
            log.info("Addressee exists");
            return false;
        } else {
            return addresseRepository.saveAddresseeWithUser(userId, addressee);
        }
    }

    @Override
    public List<Addressee> getAllByUserId(long userId) {
        return addresseRepository.getAllByUserId(userId);
    }

    @Override
    public synchronized boolean save(Addressee addressee) {
        if (addresseRepository.isAddresseeExists(
                addressee.getAddresseeName(),
                addressee.getAddresseeEmail())) {
            return false;
        } else {
            long addresseeId = addresseRepository.save(addressee);
            addressee.setAddresseeId(addresseeId);
            return true;
        }
    }

    @Override
    public boolean delete(long addresseeId) {
        return addresseRepository.delete(addresseeId) != 0;
    }

    @Override
    public boolean update(Addressee addressee) {
        if (addresseRepository.isExists(addressee.getAddresseeId())) {
            return false;
        } else {
            return addresseRepository.update(addressee) != 0;
        }
    }

}
