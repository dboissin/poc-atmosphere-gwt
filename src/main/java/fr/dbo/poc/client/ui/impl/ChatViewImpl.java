package fr.dbo.poc.client.ui.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.dbo.poc.client.ui.ChatView;

public class ChatViewImpl implements ChatView {

    private static ChatViewImplUiBinder uiBinder = GWT
    .create(ChatViewImplUiBinder.class);

    interface ChatViewImplUiBinder extends UiBinder<VerticalPanel, ChatViewImpl> {
    }

    private final VerticalPanel panel;
    private Presenter presenter;

    @UiField
    HTML res;
    TabPanel tb;

    public ChatViewImpl() {
        panel = uiBinder.createAndBindUi(this);
    }

    @Override
    public Widget asWidget() {
        return panel;
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @UiHandler("sendMessage")
    protected void doSend(KeyPressEvent event) {
        if (event.getCharCode() == KeyCodes.KEY_ENTER) {
            TextBox msg = (TextBox) event.getSource();
            presenter.sendMessage(msg.getText());
            msg.setText("");
        }
    }

    @Override
    public void appendResMsg(String message) {
        res.setHTML(res.getHTML().concat(message).concat("<br />"));
    }

}
