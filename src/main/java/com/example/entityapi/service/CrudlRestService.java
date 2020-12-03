package com.example.entityapi.service;

import java.util.Collection;

/**
 * CRUDL Rest Service.
 *
 * @param <T> Object type being handled.
 */
public interface CrudlRestService<T>
{
    T get(String id);

    Collection<T> getByIds(Collection ids);

    T createUpdate(T entity);

    void delete(String id);

    Collection<T> list();
}
