package com.trainig.spring.main.project.repository;

import com.trainig.spring.main.project.entity.Addressee;

import java.util.List;

public interface AddresseRepository {

    long createAddressee(Addressee addressee);

    int updateAddressee(Addressee addressee);

    int deleteAddressee(long addresseeId);

    boolean isAddresseeExists(String name, String email);

    Addressee getById(Long id);

    List<Addressee> getAll();

}
