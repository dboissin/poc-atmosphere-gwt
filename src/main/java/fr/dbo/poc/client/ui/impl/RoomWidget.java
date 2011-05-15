package fr.dbo.poc.client.ui.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class RoomWidget extends Composite {

    private static RoomWidgetUiBinder uiBinder = GWT
    .create(RoomWidgetUiBinder.class);

    interface RoomWidgetUiBinder extends UiBinder<Widget, RoomWidget> {
    }

    @UiField
    TextBox sendMessage;
    @UiField
    HTML room;

    public RoomWidget(String name, int nbUsers, KeyPressHandler handler) {
        initWidget(uiBinder.createAndBindUi(this));
        sendMessage.setTitle(name);
        sendMessage.addKeyPressHandler(handler);
        room.setHTML("You have join room " + name + ".<br />" + nbUsers + " connected.<br />");
    }
    
    public void addMessage(String message) {
        room.setHTML(room.getHTML().concat(message).concat("<br />"));
    }
}
