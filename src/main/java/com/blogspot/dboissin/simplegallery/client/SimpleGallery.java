package com.blogspot.dboissin.simplegallery.client;

import com.blogspot.dboissin.simplegallery.client.ui.Thumbnail;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SimpleGallery
    implements EntryPoint
{

	private String PHOTO_PATH = GWT.getModuleBaseURL() + "../photos/";
  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
	 // MyHandler handler = new MyHandler();
	  Thumbnail thumb = new Thumbnail(PHOTO_PATH + "test/" +"test.jpg", 106, 160, "Ann");
	 // thumb.addClickHandler(handler);
	  RootPanel.get("gwt-simplegallery-widget").add(thumb);
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
