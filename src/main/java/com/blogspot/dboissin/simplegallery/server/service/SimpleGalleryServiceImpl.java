package com.blogspot.dboissin.simplegallery.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogspot.dboissin.simplegallery.server.dao.GalleryDAO;
import com.blogspot.dboissin.simplegallery.server.model.Gallery;

@Service
public class SimpleGalleryServiceImpl {

	@Autowired
	private GalleryDAO galleryDAO;
	
	public Long countGallery() {
		//galleryDAO
		return null;
	}
	
	public List<Gallery> getAllGallery() {
		return galleryDAO.findAll();
	}
	
	public Gallery getGalleryById(Long id){
		return galleryDAO.findById(id);
	}
}
