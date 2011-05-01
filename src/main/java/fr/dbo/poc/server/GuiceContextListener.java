package fr.dbo.poc.server;

import java.util.HashMap;

import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

import org.atmosphere.guice.GuiceManagedAtmosphereServlet;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import fr.dbo.poc.server.resource.MessageResource;
import fr.dbo.poc.server.service.NoobService;
import fr.dbo.poc.server.service.impl.NoobServiceImpl;

public final class GuiceContextListener extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets() {
                HashMap<String, String> init = new HashMap<String, String>();
                init.put("org.atmosphere.cpr.broadcasterClass", "fr.dbo.poc.server.util.PocBroadcaster");
                init.put("com.sun.jersey.config.property.packages", MessageResource.class.getPackage().getName());
                init.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
                
                bind(MessageBodyReader.class).to(JacksonJsonProvider.class);
                bind(MessageBodyWriter.class).to(JacksonJsonProvider.class);
                
                bind(MessageResource.class);
                bind(NoobService.class).to(NoobServiceImpl.class);
                serve("/async/*").with(GuiceManagedAtmosphereServlet.class, init);

                // serve("/gwtasync").with(AtmosphereGuiceServlet.class, init);
                serve("/rest/*").with(GuiceContainer.class, init);
            }
        });
    }
}
