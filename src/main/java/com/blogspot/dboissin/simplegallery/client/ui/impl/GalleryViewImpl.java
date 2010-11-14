package com.blogspot.dboissin.simplegallery.client.ui.impl;

import java.util.List;

import com.blogspot.dboissin.simplegallery.client.ui.GalleryView;
import com.blogspot.dboissin.simplegallery.dto.PhotoDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class GalleryViewImpl extends Composite implements GalleryView {

	private static GalleryViewImplUiBinder uiBinder = GWT
			.create(GalleryViewImplUiBinder.class);

	interface GalleryViewImplUiBinder extends UiBinder<Widget, GalleryViewImpl> {
	}

	public GalleryViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setDescription(String Description) {
		// TODO Auto-generated method stub
		
	}

	public void setPhotos(List<PhotoDTO> photos) {
		// TODO Auto-generated method stub
		
	}

}
