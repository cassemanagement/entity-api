package com.example.entityapi.service;

import java.util.Collection;
import java.util.Set;

/**
 * CRUD Rest Service.
 *
 * @param <T> Object type being handled.
 */
public interface CrudRestService<T>
{
	T get(String id);

	Collection<T> getByIds(Set ids);

	T createUpdate(T entity);

	void delete(String id);
}
