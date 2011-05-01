package fr.dbo.poc.client;

import javax.inject.Inject;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import fr.dbo.poc.client.place.DefaultPlace;
import fr.dbo.poc.client.ui.ApplicationLayout;

public class ApplicationControllerImpl implements ApplicationController {

    private final PlaceController placeController;
    private final PlaceHistoryHandler placeHistoryHandler;
    private final ApplicationLayout applicationLayout;
    private Place currentPlace;

    @Inject
    public ApplicationControllerImpl(PlaceController placeController,
            EventBus eventBus,
            PlaceHistoryHandler placeHistoryHandler,
            ApplicationLayout applicationLayout,
            ActivityManagerInitializer activityManagerInitializer) {
        this.placeController = placeController;
        this.placeHistoryHandler = placeHistoryHandler;
        this.applicationLayout = applicationLayout;
        eventBus.addHandler(PlaceChangeEvent.TYPE, new PlaceLayoutHandler());
    }

    @Override
    public void start() {
        //RootPanel.get("uic_portal").add(portalView.getUICPortal());
        RootLayoutPanel.get().add(applicationLayout.getMainLayoutPanel());
        placeHistoryHandler.handleCurrentHistory();
    }

    @Override
    public void gotoDefaultPlace() {
        placeController.goTo(new DefaultPlace());
    }

    private final class PlaceLayoutHandler implements PlaceChangeEvent.Handler {

        public void onPlaceChange(PlaceChangeEvent event) {
            Place newPlace = event.getNewPlace();
            if (newPlace != currentPlace) {
                applicationLayout.setDefaultLayout();
                currentPlace = newPlace;
            }
        }
    }


}
