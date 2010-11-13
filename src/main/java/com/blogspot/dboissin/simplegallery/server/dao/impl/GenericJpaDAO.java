package com.blogspot.dboissin.simplegallery.server.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import com.blogspot.dboissin.simplegallery.server.dao.GenericDAO;

public abstract class GenericJpaDAO<K, E> extends JpaDaoSupport implements GenericDAO<K, E>{ 
    
	protected Class<E> entityClass; 

    @SuppressWarnings("unchecked") 
    public GenericJpaDAO() { 
        ParameterizedType genericSuperclass = (ParameterizedType) getClass() 
                .getGenericSuperclass(); 
        this.entityClass = (Class<E>) genericSuperclass 
                .getActualTypeArguments()[1]; 
    } 

    public void persist(E entity) { 
        getJpaTemplate().persist(entity); 
    } 

    public void remove(E entity) { 
        getJpaTemplate().remove(entity); 
    } 
    
    public E merge(E entity) { 
        return getJpaTemplate().merge(entity); 
    } 
    
    public void refresh(E entity) { 
        getJpaTemplate().refresh(entity); 
    } 

    public E findById(K id) { 
        return getJpaTemplate().find(entityClass, id); 
    } 
    
    public E flush(E entity) { 
        getJpaTemplate().flush(); 
        return entity; 
    } 
    
    @SuppressWarnings("unchecked") 
    public List<E> findAll() { 
        Object res = getJpaTemplate().execute(new JpaCallback() { 

            public Object doInJpa(EntityManager em) throws PersistenceException { 
                Query q = em.createQuery("SELECT h FROM " + 
                        entityClass.getName() + " h"); 
                return q.getResultList(); 
            } 
            
        }); 
        
        return (List<E>) res; 
    } 

    @SuppressWarnings("unchecked") 
    public Integer removeAll() { 
        return (Integer) getJpaTemplate().execute(new JpaCallback() { 

            public Object doInJpa(EntityManager em) throws PersistenceException { 
                Query q = em.createQuery("DELETE FROM " + 
                        entityClass.getName() + " h"); 
                return q.executeUpdate(); 
            } 
            
        }); 
    }
    
}
