package com.blogspot.dboissin.simplegallery.server.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blogspot.dboissin.simplegallery.server.dao.PhotoDAO;
import com.blogspot.dboissin.simplegallery.server.model.Photo;

@Repository
public class PhotoJpaDAO extends GenericJpaDAO<Long, Photo> implements PhotoDAO {

	@Autowired
	EntityManagerFactory entityManagerFactory;

	@PostConstruct
	public void init() {
		super.setEntityManagerFactory(entityManagerFactory);
	}

}
