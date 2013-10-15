/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.src;

import java.io.Serializable;
import java.util.List;

/**
 * Contiene operaciones comunes de acceso a datos.
 * 
 * @author rodrigo
 * @param <T>
 *            Clase persistente.
 * @param <ID>
 *            Clase de la llave primaria.
 */
public interface GenericDAO<T, ID extends Serializable> {

	T findById(ID id);

	T findById(ID id, boolean lock);

	List<T> findAll();

	List<T> findByExample(T exampleInstance);

	List<T> findByExample(T exampleInstance, String... excludeProperty);

	T makePersistent(T entity);

	void makeTransient(T entity);

	void flush();

	void clear();
}
