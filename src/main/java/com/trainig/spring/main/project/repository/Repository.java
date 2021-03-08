package com.trainig.spring.main.project.repository;

import java.util.List;

public interface Repository<T> {

    default T findByName(String name) {
        return null;
    }

    default T findById(long id) {
        return null;
    }

    default List<T> getAll() {
        return null;
    }

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
