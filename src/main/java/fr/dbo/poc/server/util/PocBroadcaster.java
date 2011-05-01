package fr.dbo.poc.server.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.cpr.AtmosphereResourceImpl;
import org.atmosphere.cpr.AtmosphereServlet;
import org.atmosphere.cpr.DefaultBroadcaster;
import org.atmosphere.jersey.util.JerseyBroadcasterUtil;
import org.codehaus.jackson.map.ObjectMapper;

import com.sun.jersey.spi.container.ContainerResponse;

public class PocBroadcaster extends DefaultBroadcaster {

    public PocBroadcaster() {
        super();
        setID(PocBroadcaster.class.getSimpleName());
    }

    public PocBroadcaster(String id) {
        super(id);
    }

    @Override
    protected void broadcast(final AtmosphereResource<?,?> r, final AtmosphereResourceEvent e) {
        HttpServletRequest res = (HttpServletRequest) r.getRequest();
        ContainerResponse cr = (ContainerResponse) res.getAttribute(AtmosphereServlet.CONTAINER_RESPONSE);
        if (cr != null) {
            JerseyBroadcasterUtil.broadcast(r, e);
        } else {
            try {
                String message = new ObjectMapper().writeValueAsString(e.getMessage());
                e.setMessage(message);
                r.getAtmosphereConfig().getAtmosphereHandler(this).onStateChange(e);
            } catch (IOException ex) {
                if (AtmosphereResourceImpl.class.isAssignableFrom(r.getClass())) {
                    AtmosphereResourceImpl.class.cast(r).notifyListeners(e);
                }
                onException(ex, r);
            } catch (RuntimeException ex) {
                if (AtmosphereResourceImpl.class.isAssignableFrom(r.getClass())) {
                    AtmosphereResourceImpl.class.cast(r).notifyListeners(e);
                }
                onException(ex, r);
            }
        }
    }

}
