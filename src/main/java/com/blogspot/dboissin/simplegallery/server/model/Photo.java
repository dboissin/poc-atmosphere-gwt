package com.blogspot.dboissin.simplegallery.server.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="photos")
public class Photo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String photoname;
	
	@Column(nullable=false)
	private String filename;
	
	private String description;
	
	private Date photodate;
	
	private String camera;
	
	private short focal;
	
	private short iso;
	
	private float aperture;
	
	private String exposure;
	
	private boolean flash;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="photo")
	private Set<Comment> comments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhotoname() {
		return photoname;
	}

	public void setPhotoname(String photoname) {
		this.photoname = photoname;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPhotodate() {
		return photodate;
	}

	public void setPhotodate(Date photodate) {
		this.photodate = photodate;
	}

	public String getCamera() {
		return camera;
	}

	public void setCamera(String camera) {
		this.camera = camera;
	}

	public short getFocal() {
		return focal;
	}

	public void setFocal(short focal) {
		this.focal = focal;
	}

	public short getIso() {
		return iso;
	}

	public void setIso(short iso) {
		this.iso = iso;
	}

	public float getAperture() {
		return aperture;
	}

	public void setAperture(float aperture) {
		this.aperture = aperture;
	}

	public String getExposure() {
		return exposure;
	}

	public void setExposure(String exposure) {
		this.exposure = exposure;
	}

	public boolean isFlash() {
		return flash;
	}

	public void setFlash(boolean flash) {
		this.flash = flash;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
}
