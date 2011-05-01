package fr.dbo.poc.client.mvp;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import fr.dbo.poc.client.activity.ChatActivity;
import fr.dbo.poc.client.place.DefaultPlace;

public class ChatActivityMapper implements ActivityMapper {

    private final Provider<ChatActivity> chatActivityProvider;

    @Inject
    public ChatActivityMapper(Provider<ChatActivity> chatActivityProvider) {
        this.chatActivityProvider = chatActivityProvider;
    }

    public Activity getActivity(Place place) {
        if (place instanceof DefaultPlace) {
            return chatActivityProvider.get();
        }
        return null;
    }

}
