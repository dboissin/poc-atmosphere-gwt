package fr.dbo.poc.server.resource;


import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.atmosphere.annotation.Broadcast;
import org.atmosphere.annotation.Suspend;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory;
import org.atmosphere.jersey.Broadcastable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.core.InjectParam;

import fr.dbo.poc.server.service.NoobService;
import fr.dbo.poc.server.util.PocBroadcaster;
import fr.dbo.poc.shared.dto.OtherDTO;
import fr.dbo.poc.shared.dto.SimpleDTO;

@Path("/topic")
@Singleton
@Produces("application/json")
public class MessageResource {

    private static final Logger logger = LoggerFactory.getLogger(MessageResource.class);

    @InjectParam("noobService")
    private NoobService noobService;

    @GET
    @Path("{name}")
    @Suspend(outputComments = false, resumeOnBroadcast = false, listeners = EventsLogger.class)
    public Broadcastable listen(@PathParam("name") String topic) {
        Broadcaster broadcaster = BroadcasterFactory.getDefault().lookup(PocBroadcaster.class, topic, true);
        logger.info("thread: {} LISTENING to '{}'", Thread.currentThread().getName(), broadcaster.getID());
        if (noobService == null) {
            throw new AssertionError();
        }
       return new Broadcastable(new SimpleDTO("Connected !"), broadcaster);
    }

    @POST
    @Path("{name}")
    @Broadcast
    @Produces(MediaType.APPLICATION_JSON)
    public Broadcastable publish(@PathParam("name") String topic, String message) {
        Broadcaster broadcaster = BroadcasterFactory.getDefault().lookup(PocBroadcaster.class, topic, true);
        logger.info("thread: {} PUBLISH to '{}' from {}: {}",
                new Object[]{Thread.currentThread().getName(), broadcaster.getID(), message});
        if (noobService == null) {
            throw new AssertionError();
        }
        return new Broadcastable(new SimpleDTO(message), "", broadcaster);
    }

    @POST
    @Path("/other/{name}")
    @Broadcast
    @Produces(MediaType.APPLICATION_JSON)
    public Broadcastable other(@PathParam("name") String topic, OtherDTO message) {
        Broadcaster broadcaster = BroadcasterFactory.getDefault().lookup(PocBroadcaster.class, topic, true);
        logger.info("thread: {} PUBLISH to '{}' from {}: {}",
                new Object[]{Thread.currentThread().getName(), broadcaster.getID(), message});
        if (noobService == null) {
            throw new AssertionError();
        }
        return new Broadcastable(new SimpleDTO(message.getOtherInteger() +
                " " + message.getOtherString()), "", broadcaster);
    }

    public NoobService getNoobService() {
        return noobService;
    }

    public void setNoobService(NoobService noobService) {
        this.noobService = noobService;
    }

}
