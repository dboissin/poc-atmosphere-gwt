package fr.dbo.poc.client.ioc;


import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

import fr.dbo.poc.client.ApplicationController;


@GinModules(ApplicationGinModule.class)
public interface ApplicationGinjector extends Ginjector {

    ApplicationController getApplicationController();

}
