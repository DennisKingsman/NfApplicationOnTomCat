package com.trainig.spring.main.project.repository.role;

import com.trainig.spring.main.project.entity.Role;
import com.trainig.spring.main.project.repository.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Role findByName(String name) {
        return null;
    }

    @Override
    public Role findById(long id) {
        return null;
    }

    @Override
    public List<Role> getAll() {
        return null;
    }

    @Override
    public long save(Role entity) {
        return 0;
    }

    @Override
    public int delete(long id) {
        return 0;
    }

    @Override
    public int update(Role entity) {
        return 0;
    }

    @Override
    public boolean isExists(long id) {
        return false;
    }

}
