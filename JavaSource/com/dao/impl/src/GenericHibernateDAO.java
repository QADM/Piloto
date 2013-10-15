/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.impl.src;

import com.dao.src.GenericDAO;
import com.main.resources.HibernateUtil;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
//import org.hibernate.exception.ExceptionUtils;


/**
 *
 * @author rodrigo
 */
public abstract class GenericHibernateDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {

    private Class<T> persistentClass;
    private Session session;

    public GenericHibernateDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void setSession(Session s) {
        this.session = s;
    }

    protected Session getSession() {
        if (session == null) {
            session = HibernateUtil.getSessionFactory().openSession();
        }
        return session;
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    @SuppressWarnings("unchecked")
    public T findById(ID id) {
    	return findById(id, false);
    }
    
    @SuppressWarnings("unchecked")
    public T findById(ID id, boolean lock) {
        T entity;
        if (lock) {
            entity = (T) getSession().load(getPersistentClass(), id, LockMode.UPGRADE);
        } else {
            entity = (T) getSession().load(getPersistentClass(), id);
        }
        return entity;
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return findByCriteria();
    }

    
    @SuppressWarnings("unchecked")
    public List<T> findByExample(T exampleInstance) {
        return findByExample(exampleInstance, new String[0]);
    }   
    
    @SuppressWarnings("unchecked")
    public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        Example example = Example.create(exampleInstance);
        example.excludeZeroes();
        crit.add(example);
        return crit.list();
    }

    @SuppressWarnings("unchecked")
    public T makePersistent(T entity) {
        try {
            Transaction transaction = getSession().beginTransaction();
            getSession().saveOrUpdate(entity);
            transaction.commit();
        } catch (HibernateException e) {
            //System.out.println("Algo paso al intentar guardar el objeto");
            e.printStackTrace();
        }

        return entity;

    }

    public void makeTransient(T entity) {
        Transaction transaction = getSession().beginTransaction();
        getSession().delete(entity);
        transaction.commit();
    }

    public void flush() {
        getSession().flush();
    }

    public void clear() {
        getSession().clear();
    }

    /**
     * Use this inside subclasses as a convenience method.
     */
    @SuppressWarnings("unchecked")
    protected List<T> findByCriteria(Criterion... criterion) {
        Criteria crit =
                getSession().createCriteria(getPersistentClass());
        for (Criterion c : criterion) {
            crit.add(c);
        }
        return crit.list();
    }
}
