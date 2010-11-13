package com.blogspot.dboissin.simplegallery.server.dao;

import java.util.List;

public interface GenericDAO<K, E> {

	public void persist(E entity);
	
	public void remove(E entity);
	
	public E merge(E entity);
	
	public void refresh(E entity);
	
	public E findById(K id);
	 
	public E flush(E entity);
	
	public List<E> findAll();
	
	public Integer removeAll();
}
