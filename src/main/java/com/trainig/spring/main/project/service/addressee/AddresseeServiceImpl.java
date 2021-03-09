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

    private AddresseeRepository addresseeRepository;

    @Autowired
    public void setAddresseeRepository(AddresseeRepository addresseeRepository) {
        this.addresseeRepository = addresseeRepository;
    }

    @Override
    public boolean saveUserAddressee(long userId, Addressee addressee) {
        if (addresseeRepository.isAddresseeExists(
                addressee.getAddresseeName(),
                addressee.getAddresseeEmail())) {
            log.info("Addressee exists");
            return false;
        } else {
            return addresseeRepository.saveAddresseeWithUser(userId, addressee);
        }
    }

    @Override
    public List<Addressee> getAllByUserId(long userId) {
        return addresseeRepository.getAllByUserId(userId);
    }

    @Override
    public boolean delete(long addresseeId) {
        return addresseeRepository.delete(addresseeId) != 0;
    }

}
