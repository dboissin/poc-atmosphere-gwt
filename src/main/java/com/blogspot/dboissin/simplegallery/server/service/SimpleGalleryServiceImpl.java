package com.blogspot.dboissin.simplegallery.server.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.blogspot.dboissin.simplegallery.server.dao.GalleryDAO;
import com.blogspot.dboissin.simplegallery.server.model.Gallery;

@Service
public class SimpleGalleryServiceImpl {

	private static final Logger LOGGER = Logger.getLogger(SimpleGalleryServiceImpl.class);
	
	@Autowired
	private GalleryDAO galleryDAO;
	
	@Autowired
	private ConversionService converter;
	
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
