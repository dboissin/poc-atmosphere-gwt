package com.blogspot.dboissin.simplegallery.client.mvp;

import com.blogspot.dboissin.simplegallery.client.place.GalleryPlace;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({GalleryPlace.Tokenizer.class})
public interface SimpleGalleryPlaceHistoryMapper extends PlaceHistoryMapper
{
}
