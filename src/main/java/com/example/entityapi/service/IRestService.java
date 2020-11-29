package com.example.entityapi.service;

import java.util.Collection;
import java.util.Set;

public interface IRestService<T>
{
	Collection<T> list();

	T get(String id);

	Collection<T> getByIds(Set ids);

	T createUpdate(T entity);

	void delete(String id);
}
