package com.trainig.spring.main.project.repository.addressee;

import com.trainig.spring.main.project.entity.Addressee;
import com.trainig.spring.main.project.entity.User;
import com.trainig.spring.main.project.repository.Repository;
import com.trainig.spring.main.project.repository.role.RoleRepository;

import java.util.List;

public interface AddresseeRepository extends Repository<Addressee> {

    boolean isAddresseeExists(String name, String email);

    boolean isUserAddresseeExists(Long userId);

    boolean saveAddresseeWithUser(Long userId, Addressee addressee);

    List<Addressee> getAllByUserId(long userId);

}
