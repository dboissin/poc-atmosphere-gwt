package fr.dbo.poc.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

import fr.dbo.poc.client.ioc.ApplicationGinjector;

public class Application implements EntryPoint {

    private final ApplicationGinjector injector = GWT.create(ApplicationGinjector.class);

    public void onModuleLoad() {
        //RootPanel.get().add(new MessageWidget());

        injector.getApplicationController().start();
    }

}
