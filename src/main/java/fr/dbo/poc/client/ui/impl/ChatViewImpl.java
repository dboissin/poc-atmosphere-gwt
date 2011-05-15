package fr.dbo.poc.client.ui.impl;

import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.dbo.poc.client.ui.ChatView;
import fr.dbo.poc.shared.dto.MessageDTO;

public class ChatViewImpl implements ChatView {

    private static ChatViewImplUiBinder uiBinder = GWT
    .create(ChatViewImplUiBinder.class);

    interface ChatViewImplUiBinder extends UiBinder<VerticalPanel, ChatViewImpl> {
    }

    private final VerticalPanel panel;
    private Presenter presenter;
    private final SendMessageHandler handler;
    private final HashMap<String, Integer> roomsIdx;    

    @UiField
    TabLayoutPanel tabPanel;

    public ChatViewImpl() {
        panel = uiBinder.createAndBindUi(this);
        handler = new SendMessageHandler();
        roomsIdx = new HashMap<String, Integer>();
    }

    @Override
    public Widget asWidget() {
        return panel;
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addRoom(String name, int nbUsers) {
        RoomWidget room = new RoomWidget(name, nbUsers, handler);
        tabPanel.add(room, name);
        int idx = tabPanel.getWidgetIndex(room);
        tabPanel.selectTab(idx);
        roomsIdx.put(name, idx);
    }

    @Override
    public void addMessage(MessageDTO message) {
        Integer roomId = roomsIdx.get(message.getRoom());
        if (roomId != null) {
            RoomWidget room = (RoomWidget) tabPanel.getWidget(roomId);
            room.addMessage(
                    (message.getUser() != null ? message.getUser() + " : " : "")
                    + message.getMessage());
        }
    }

    class SendMessageHandler implements KeyPressHandler {

        @Override
        public void onKeyPress(KeyPressEvent event) {
            if (event.getCharCode() == KeyCodes.KEY_ENTER) {
                TextBox msg = (TextBox) event.getSource();
                presenter.sendMessage(msg.getTitle(), msg.getText());
                msg.setText("");
            } 
        }

    }

}
