package com.trainig.spring.main.project.repository;

import com.trainig.spring.main.project.entity.Addressee;

import java.util.List;

public interface AddresseeRepository {

    long createAddressee(Addressee addressee);

    int updateAddressee(Addressee addressee);

    int deleteAddressee(long addresseeId);

    boolean isAddresseeExists(String name, String email);

    boolean isAddresseeExists(long addresseeId);

    Addressee getById(Long id);

    List<Addressee> getAll();

}
