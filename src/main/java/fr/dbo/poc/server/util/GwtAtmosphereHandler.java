package fr.dbo.poc.server.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.atmosphere.gwt.server.AtmosphereGwtHandler;
import org.atmosphere.gwt.server.GwtAtmosphereResource;

public class GwtAtmosphereHandler extends AtmosphereGwtHandler {

    @Override
    public int doComet(GwtAtmosphereResource resource) throws ServletException, IOException {
        String topicName = resource.getRequest().getParameter("topicname");
        if (topicName != null && topicName.trim().length() > 0) {
            resource.getBroadcaster().setID(topicName);
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
