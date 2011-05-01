package fr.dbo.poc.client.ioc;


import javax.inject.Named;
import javax.inject.Singleton;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.inject.Provides;

import fr.dbo.poc.client.ApplicationController;
import fr.dbo.poc.client.ApplicationControllerImpl;
import fr.dbo.poc.client.activity.ChatActivity;
import fr.dbo.poc.client.activity.ToolbarActivity;
import fr.dbo.poc.client.mvp.ChatActivityMapper;
import fr.dbo.poc.client.mvp.ApplicationPlaceHistoryMapper;
import fr.dbo.poc.client.mvp.ToolbarActivityMapper;
import fr.dbo.poc.client.place.DefaultPlace;
import fr.dbo.poc.client.ui.ChatView;
import fr.dbo.poc.client.ui.ApplicationLayout;
import fr.dbo.poc.client.ui.ToolbarView;
import fr.dbo.poc.client.ui.impl.ChatViewImpl;
import fr.dbo.poc.client.ui.impl.ApplicationLayoutImpl;
import fr.dbo.poc.client.ui.impl.ToolbarViewImpl;


public class ApplicationGinModule extends AbstractGinModule {

    @Override
    protected void configure() {


        // Views
        bind(ApplicationLayout.class).to(ApplicationLayoutImpl.class).in(Singleton.class);
        bind(ToolbarView.class).to(ToolbarViewImpl.class).in(Singleton.class);
        bind(ChatView.class).to(ChatViewImpl.class).in(Singleton.class);

        // Activities
        bind(ToolbarActivity.class).toProvider(ToolbarActivityProvider.class);
        bind(ChatActivity.class).toProvider(ChatActivityProvider.class);


        // Places
        bind(PlaceHistoryMapper.class).to(ApplicationPlaceHistoryMapper.class).in(Singleton.class);

        // UIC operator interface EventBus
        bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);

        // Module Controller
        bind(ApplicationController.class).to(ApplicationControllerImpl.class).in(Singleton.class);


    }

    @Provides
    @Singleton
    @Named("ToolbarRegion")
    public ActivityManager getToolbarRegionActivityMapper(
            ToolbarActivityMapper activityMapper, EventBus eventBus) {
        return new ActivityManager(activityMapper, eventBus);
    }

//    @Provides
//    @Singleton
//    @Named("ScreenRegion")
//    public ActivityManager getCallRegionActivityMapper(ScreenActivityMapper activityMapper, EventBus eventBus) {
//        return new ActivityManager(activityMapper, eventBus);
//    }

    @Provides
    @Singleton
    @Named("ChatRegion")
    public ActivityManager getVisioRegionActivityMapper(ChatActivityMapper activityMapper, EventBus eventBus) {
        return new ActivityManager(activityMapper, eventBus);
    }

    @Provides
    @Singleton
    public PlaceController getPlaceController(EventBus eventBus) {
        return new PlaceController(eventBus);
    }

    @Provides
    @Singleton
    public PlaceHistoryHandler getHistoryHandler(PlaceController placeController, PlaceHistoryMapper historyMapper, EventBus eventBus) {
        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController, eventBus, new DefaultPlace());
        return historyHandler;
    }

}
