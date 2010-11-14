package com.blogspot.dboissin.simplegallery.client.mvp;

import com.blogspot.dboissin.simplegallery.client.ui.GalleryView;
import com.blogspot.dboissin.simplegallery.client.ui.impl.GalleryViewImpl;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;

public class ClientFactoryImpl implements ClientFactory {
	private final EventBus eventBus = new SimpleEventBus();
    private final PlaceController placeController = new PlaceController(eventBus);
    private final GalleryView galleryView = new GalleryViewImpl();
    
	public EventBus getEventBus() {
		return eventBus;
	}
	public PlaceController getPlaceController() {
		return placeController;
	}
	public GalleryView getGalleryView() {
		return galleryView;
	}


}
