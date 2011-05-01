package fr.dbo.poc.client;


import javax.inject.Inject;
import javax.inject.Named;

import com.google.gwt.activity.shared.ActivityManager;

import fr.dbo.poc.client.ui.ApplicationLayout;


public class ActivityManagerInitializer {

    @Inject
    public ActivityManagerInitializer(ApplicationLayout applicationLayout,
            @Named("ToolbarRegion") ActivityManager toolbarActivityManager,
//            @Named("ScreenRegion") ActivityManager screenActivityManager,
            @Named("ChatRegion") ActivityManager chatActivityManager
            ) {
        toolbarActivityManager.setDisplay(applicationLayout.getToolbarContainer());
        //screenActivityManager.setDisplay(applicationLayout.getScreenContainer());
        chatActivityManager.setDisplay(applicationLayout.getSideContainer());
    }
}
