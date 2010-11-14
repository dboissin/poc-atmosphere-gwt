package com.blogspot.dboissin.simplegallery.client.mvp;

import com.blogspot.dboissin.simplegallery.client.ui.GalleryView;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;

public interface ClientFactory {
    EventBus getEventBus();
    PlaceController getPlaceController();
    GalleryView getGalleryView();
}
