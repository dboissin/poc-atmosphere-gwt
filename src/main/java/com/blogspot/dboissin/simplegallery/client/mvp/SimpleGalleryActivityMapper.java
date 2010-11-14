package com.blogspot.dboissin.simplegallery.client.mvp;

import com.blogspot.dboissin.simplegallery.client.activity.GalleryActivity;
import com.blogspot.dboissin.simplegallery.client.place.GalleryPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class SimpleGalleryActivityMapper implements ActivityMapper {
    private ClientFactory clientFactory;

    public SimpleGalleryActivityMapper(ClientFactory clientFactory) {
        super();
        this.clientFactory = clientFactory;
    }

    public Activity getActivity(Place place) {
        if (place instanceof GalleryPlace)
            return new GalleryActivity((GalleryPlace) place, clientFactory);
//        else if (place instanceof GoodbyePlace)
//            return new GoodbyeActivity((GoodbyePlace) place, clientFactory);
        return null;
    }
}