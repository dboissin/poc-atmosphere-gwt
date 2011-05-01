package fr.dbo.poc.client.ioc;

import com.google.inject.Inject;
import com.google.inject.Provider;

import fr.dbo.poc.client.activity.ChatActivity;
import fr.dbo.poc.client.ui.ChatView;

public class ChatActivityProvider implements Provider<ChatActivity> {

    private final ChatView view;

    @Inject
    public ChatActivityProvider(ChatView view) {
        this.view = view;
    }

    public ChatActivity get() {
        return new ChatActivity(view);
    }

}
