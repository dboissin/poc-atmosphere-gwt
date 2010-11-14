package com.blogspot.dboissin.simplegallery.client.ui;

import java.util.List;

import com.blogspot.dboissin.simplegallery.dto.PhotoDTO;
import com.google.gwt.user.client.ui.IsWidget;

public interface GalleryView extends IsWidget {
	
	void setTitle(String title);
	void setDescription(String Description);
	void setPhotos(List<PhotoDTO> photos);
	
	public interface Presenter {
		void photoClicked();
	}

}
