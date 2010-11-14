package com.blogspot.dboissin.simplegallery.dto;

import java.io.Serializable;

public class GalleryDTO implements Serializable {

	private static final long serialVersionUID = -2194693304133479733L;

	private Long id;
	
	private String title;

	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
