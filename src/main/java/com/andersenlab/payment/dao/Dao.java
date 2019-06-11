package com.andersenlab.payment.dao;

import java.util.List;

public interface Dao<E> {
    List<E> findAll();

    E findById(long id);

    E insert(E entity);

    void update(E entity);

    void deleteById(long id);
}
