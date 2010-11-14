package com.blogspot.dboissin.simplegallery.client.activity;

import com.blogspot.dboissin.simplegallery.client.mvp.ClientFactory;
import com.blogspot.dboissin.simplegallery.client.place.GalleryPlace;
import com.blogspot.dboissin.simplegallery.client.ui.GalleryView.Presenter;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class GalleryActivity extends AbstractActivity implements Presenter {
	
	private ClientFactory clientFactory;
	private GalleryPlace place;
	
	public GalleryActivity(GalleryPlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.place = place;
	}

	public void start(AcceptsOneWidget arg0, EventBus arg1) {
		// TODO Auto-generated method stub

	}

	public void photoClicked() {
		// TODO Auto-generated method stub

	}

}
