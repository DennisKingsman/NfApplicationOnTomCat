package com.trainig.spring.main.project.service;

import java.util.List;

public interface Service<T> {

    List<T> getAll();

    T findById(long id);

    boolean save(T entity);

    boolean delete(long id);

    boolean update(T entity);

}
