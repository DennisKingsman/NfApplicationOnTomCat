package com.trainig.spring.main.project.service;

import com.trainig.spring.main.project.entity.Addressee;

import java.util.List;

public interface AddresseeService {


    List<Addressee> getAllAddressee();

    Addressee getAddresseeById(long addresseeId);

    boolean addAddressee(Addressee addressee);

    boolean updateAddressee(Addressee addressee);

    boolean deleteAddressee(long addresseeId);

}
