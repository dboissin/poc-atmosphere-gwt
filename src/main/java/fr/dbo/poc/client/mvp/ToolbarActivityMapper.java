package fr.dbo.poc.client.mvp;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import fr.dbo.poc.client.activity.ToolbarActivity;
import fr.dbo.poc.client.place.DefaultPlace;

public class ToolbarActivityMapper implements ActivityMapper {

    private final Provider<ToolbarActivity> toolbarActivityProvider;

    @Inject
    public ToolbarActivityMapper(Provider<ToolbarActivity> toolbarActivityProvider) {
        this.toolbarActivityProvider = toolbarActivityProvider;
    }

    public Activity getActivity(Place place) {
        if (place instanceof DefaultPlace) {
            return toolbarActivityProvider.get();
        }
        return null;
    }

}
