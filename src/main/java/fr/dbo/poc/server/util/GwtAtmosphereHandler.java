package fr.dbo.poc.server.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory;
import org.atmosphere.gwt.server.AtmosphereGwtHandler;
import org.atmosphere.gwt.server.GwtAtmosphereResource;

public class GwtAtmosphereHandler extends AtmosphereGwtHandler {

    @Override
    public int doComet(GwtAtmosphereResource resource) throws ServletException, IOException {
        String user = resource.getRequest().getParameter("user");
        if (user != null && user.trim().length() > 0) {
//            Broadcaster broadcaster = BroadcasterFactory.getDefault().lookup(PocBroadcaster.class, user, true);
            
          //  Broadcaster broadcaster = resource.getBroadcaster();
//            broadcaster.addAtmosphereResource(resource.getAtmosphereResource());
            
//            resource.getAtmosphereResource().setBroadcaster(broadcaster);
            resource.getBroadcaster().setID(user);
            //resource.getBroadcaster().addAtmosphereResource(resource.getAtmosphereResource());
        }
        HttpSession session = resource.getAtmosphereResource().getRequest().getSession(false);
        if (session != null) {
            logger.debug("Got session with id: " + session.getId());
            logger.debug("Time attribute: " + session.getAttribute("time"));
        } else {
            logger.warn("No session");
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Url: " + resource.getAtmosphereResource().getRequest().getRequestURL()
                    + "?" + resource.getAtmosphereResource().getRequest().getQueryString());
        }
        return NO_TIMEOUT;
    }

}
