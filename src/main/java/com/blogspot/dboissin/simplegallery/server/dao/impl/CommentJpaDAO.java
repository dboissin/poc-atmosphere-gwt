package com.blogspot.dboissin.simplegallery.server.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blogspot.dboissin.simplegallery.server.dao.CommentDAO;
import com.blogspot.dboissin.simplegallery.server.model.Comment;

@Repository
public class CommentJpaDAO extends GenericJpaDAO<Long, Comment> implements CommentDAO {

	@Autowired
	EntityManagerFactory entityManagerFactory;

	@PostConstruct
	public void init() {
		super.setEntityManagerFactory(entityManagerFactory);
	}
}
