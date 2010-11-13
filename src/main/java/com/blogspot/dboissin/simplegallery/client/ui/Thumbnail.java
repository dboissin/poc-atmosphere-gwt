package com.blogspot.dboissin.simplegallery.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasDoubleClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class Thumbnail extends Composite implements HasClickHandlers {

	private static ThumbnailUiBinder uiBinder = GWT
	.create(ThumbnailUiBinder.class);

	interface ThumbnailUiBinder extends UiBinder<Widget, Thumbnail> {
	}

	interface MyStyle extends CssResource {
		String selected();
		String unselected();
		//String disabled();
	}

	@UiField
	MyStyle style;
	@UiField
	Image img;
	@UiField
	FocusPanel panel;
	
	
	private boolean enabled = false;

	public Thumbnail(String url, int imgWidth, int imgHeight) {
		initWidget(uiBinder.createAndBindUi(this));
		img.setUrl(url);
		panel.setSize(imgWidth + 10 +"px", imgHeight + 10 + "px");
	}

	public Thumbnail(String url, int imgWidth, int imgHeight, String title) {
		this(url, imgWidth, imgHeight);
		panel.setTitle(title);
	}

	@UiHandler("panel")
	void onClick(ClickEvent e) {
		if (enabled) {
			panel.setStylePrimaryName(style.unselected());
			enabled = false;
		} else {
			panel.setStylePrimaryName(style.selected());
			enabled = true;
		}
	}

	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return addHandler(handler, ClickEvent.getType());
	}

	public String getImgUrl() {
		return img.getUrl();
	}

}
