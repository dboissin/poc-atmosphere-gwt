package fr.dbo.poc.server.resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.cpr.AtmosphereResourceEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventsLogger implements AtmosphereResourceEventListener {

    private static final Logger logger = LoggerFactory.getLogger(EventsLogger.class);

    public void onSuspend(final AtmosphereResourceEvent<HttpServletRequest, HttpServletResponse> event) {
        logger.info("[{}] onSuspend: {}:{}",
                new Object[]{Thread.currentThread().getName(), event.getResource().getRequest().getRemoteAddr(),
                        event.getResource().getRequest().getRemotePort()});
    }

    public void onResume(AtmosphereResourceEvent<HttpServletRequest, HttpServletResponse> event) {
        logger.info("[{}] onResume: {}:{}",
                new Object[]{Thread.currentThread().getName(), event.getResource().getRequest().getRemoteAddr(),
                        event.getResource().getRequest().getRemotePort()});
    }

    public void onDisconnect(AtmosphereResourceEvent<HttpServletRequest, HttpServletResponse> event) {
        logger.info("[{}] onDisconnect: {}:{}",
                new Object[]{Thread.currentThread().getName(), event.getResource().getRequest().getRemoteAddr(),
                        event.getResource().getRequest().getRemotePort()});
    }

    public void onBroadcast(AtmosphereResourceEvent<HttpServletRequest, HttpServletResponse> event) {
        logger.info("[{}] onBroadcast: {}:{}",
                new Object[]{Thread.currentThread().getName(), event.getResource().getRequest().getRemoteAddr(),
                        event.getResource().getRequest().getRemotePort()});
    }

    public void onThrowable(AtmosphereResourceEvent<HttpServletRequest, HttpServletResponse> event) {
        logger.info("[{}] onThrowable: " + event.getResource().getRequest().getRemoteAddr() + ":" +
                event.getResource().getRequest().getRemotePort(), event.throwable());
    }

}
