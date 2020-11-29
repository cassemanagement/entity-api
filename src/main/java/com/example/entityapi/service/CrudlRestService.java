package com.example.entityapi.service;

import java.util.Collection;

/**
 * CRUDL Rest Service.
 *
 * @param <T> Object type being handled.
 */
public interface CrudlRestService<T> extends CrudRestService<T>
{
	Collection<T> list();
}
