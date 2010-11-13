package com.blogspot.dboissin.simplegallery.server.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="galleries")
public class Gallery {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	@Column(length=4000)
	private String description;
	
	@ManyToMany
	@JoinTable(
			name="gals_photos",
			joinColumns=
				@JoinColumn(name="id_gal", referencedColumnName="id"),
			inverseJoinColumns =
				@JoinColumn(name="id_photo", referencedColumnName="id")
	)
	private Set<Photo> photos;

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

	public Set<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}
}
