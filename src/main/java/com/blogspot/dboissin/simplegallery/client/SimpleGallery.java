package com.blogspot.dboissin.simplegallery.client;

import com.blogspot.dboissin.simplegallery.client.mvp.ClientFactory;
import com.blogspot.dboissin.simplegallery.client.mvp.SimpleGalleryActivityMapper;
import com.blogspot.dboissin.simplegallery.client.mvp.SimpleGalleryPlaceHistoryMapper;
import com.blogspot.dboissin.simplegallery.client.place.GalleryPlace;
import com.blogspot.dboissin.simplegallery.client.ui.impl.Thumbnail;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SimpleGallery
    implements EntryPoint
{

	private String PHOTO_PATH = GWT.getModuleBaseURL() + "../photos/";
	private Place defaultPlace = new GalleryPlace("World!");
    private SimplePanel appWidget = new SimplePanel();

	
  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
	 // MyHandler handler = new MyHandler();
	  Thumbnail thumb = new Thumbnail(PHOTO_PATH + "test/" +"test.jpg", 106, 160, "Ann");
	 // thumb.addClickHandler(handler);
	  RootPanel.get("gwt-simplegallery-widget").add(thumb);
	  
	  
      ClientFactory clientFactory = GWT.create(ClientFactory.class);
      EventBus eventBus = clientFactory.getEventBus();
      PlaceController placeController = clientFactory.getPlaceController();

      // Start ActivityManager for the main widget with our ActivityMapper
      ActivityMapper activityMapper = new SimpleGalleryActivityMapper(clientFactory);
      ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
      activityManager.setDisplay(appWidget);

      // Start PlaceHistoryHandler with our PlaceHistoryMapper
      SimpleGalleryPlaceHistoryMapper historyMapper= GWT.create(SimpleGalleryPlaceHistoryMapper.class);
      PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
      historyHandler.register(placeController, eventBus, defaultPlace);

      RootPanel.get().add(appWidget);
      // Goes to the place represented on URL else default place
      historyHandler.handleCurrentHistory();

  }
  
//  class MyHandler implements ClickHandler {
//	  private Timer waitDblClick ;
//	  private boolean dblclick = false;
//	  private Thumbnail tce;
//	  
//	public void onClick(final ClickEvent e) {
//		if (!dblclick) {
//			dblclick = true;
//			tce = (Thumbnail)e.getSource();
//			waitDblClick = new Timer() {
//				public void run() {
//					dblclick = false;
//					//Window.alert("SimpleClick on " + tce.getImgUrl());
//					tce = null;
//				}
//			};
//			waitDblClick.schedule(300);
//		} else {
//			waitDblClick.cancel();
//			dblclick = false;
//			RootLayoutPanel.get().add(new DisplayBox(PHOTO_PATH + "test.jpg", 106, 160)); 
//			tce = null;
//		}
//		
//	}
//	  
//  }
}
