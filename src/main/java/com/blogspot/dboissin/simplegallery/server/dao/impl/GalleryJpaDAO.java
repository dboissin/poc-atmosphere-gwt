package com.blogspot.dboissin.simplegallery.server.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blogspot.dboissin.simplegallery.server.dao.GalleryDAO;
import com.blogspot.dboissin.simplegallery.server.model.Gallery;

@Repository
public class GalleryJpaDAO extends GenericJpaDAO<Long, Gallery> implements GalleryDAO {

	@Autowired
	EntityManagerFactory entityManagerFactory;

	@PostConstruct
	public void init() {
		super.setEntityManagerFactory(entityManagerFactory);
	}
}
