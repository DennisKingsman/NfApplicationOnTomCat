package com.trainig.spring.main.project.repository;

import java.util.List;

public interface Repository<T> {

    T findByName(String name);

    T findById(long id);

    List<T> getAll();

    default long save(T entity) {
        return 1L;
    }

    int delete(long id);

    int update(T entity);

    default boolean isExists(long id) {
        return false;
    }

    default boolean isExists(String name) {
        return false;
    }

}
