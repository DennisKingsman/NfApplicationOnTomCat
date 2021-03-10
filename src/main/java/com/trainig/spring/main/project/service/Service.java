package com.trainig.spring.main.project.service;

import com.trainig.spring.main.project.repository.Repository;

import java.util.List;

public interface Service<T> {

    default List<T> getAll() {
        return null;
    }

    default T findById(long id) {
        return null;
    }

    default boolean save(T entity) {
        return false;
    }

    boolean delete(long id);

    default boolean update(T entity) {
        return false;
    }

    default void setRepository(Repository<T> repository) {

    }

}
