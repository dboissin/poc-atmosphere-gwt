package fr.dbo.poc.server.resource;

import java.io.IOException;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.atmosphere.annotation.Broadcast;
import org.atmosphere.annotation.Suspend;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory;
import org.atmosphere.jersey.Broadcastable;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.dbo.poc.server.util.PocBroadcastable;
import fr.dbo.poc.server.util.PocBroadcaster;
import fr.dbo.poc.shared.dto.MessageDTO;
import fr.dbo.poc.shared.dto.RoomInfosDTO;
import fr.dbo.poc.shared.dto.SimpleDTO;

@Path("/chat")
@Singleton
@Produces(MediaType.APPLICATION_JSON)
public class ChatResource {
    
    private static final Logger logger = LoggerFactory.getLogger(ChatResource.class);
    
    @GET
    @Path("{user}")
    @Suspend(outputComments = false, resumeOnBroadcast = false, listeners = EventsLogger.class)
    public Broadcastable listen(@PathParam("user") String user) {
        Broadcaster broadcaster = BroadcasterFactory.getDefault().lookup(PocBroadcaster.class, user, true);
        logger.info("thread: {} LISTENING to '{}'", Thread.currentThread().getName(), broadcaster.getID());
        return new Broadcastable(new SimpleDTO("Connected !"), broadcaster);
    }
    
    @PUT
    @Path("{room}/{user}")
    @Broadcast
    public Broadcastable subscribe(@PathParam("room") String room, @PathParam("user") String user) throws JsonGenerationException, JsonMappingException, IOException {
        Broadcaster broadcaster = BroadcasterFactory.getDefault().lookup(PocBroadcaster.class, room, true);
        Broadcaster userBroadcaster = BroadcasterFactory.getDefault().lookup(PocBroadcaster.class, user);
        if (userBroadcaster == null || broadcaster == null) {
            return null; //new Broadcastable(new SimpleDTO("Room doesn't exist."), userBroadcaster);
        }
        int nbResources = userBroadcaster.getAtmosphereResources().size();
        logger.debug("Nb attached resource to userBroadcaster : {}", nbResources);
        if (nbResources < 1) {
            return null; // TODO throw exception
        }
        broadcaster.addAtmosphereResource((AtmosphereResource<?, ?>) userBroadcaster.getAtmosphereResources().toArray()[0]);
        return new PocBroadcastable(new MessageDTO(null, user + " joined " + room + ".", room),
                new RoomInfosDTO(room, broadcaster.getAtmosphereResources().size()), broadcaster);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Broadcast
    public Broadcastable publish(MessageDTO message) {
        Broadcaster broadcaster = BroadcasterFactory.getDefault().lookup(PocBroadcaster.class, message.getRoom());
        if (broadcaster == null) {
            return null; 
        }
        return new Broadcastable(message, "", broadcaster);
    }
    
    @DELETE
    @Path("{room}/{user}")
    @Broadcast
    public Broadcastable unsubscribe(@PathParam("user") String user, @PathParam("room") String room) {
        Broadcaster broadcaster = BroadcasterFactory.getDefault().lookup(PocBroadcaster.class, room);
        Broadcaster userBroadcaster = BroadcasterFactory.getDefault().lookup(PocBroadcaster.class, user);
        if (broadcaster == null || userBroadcaster == null) {
            return null;
        }
        broadcaster.removeAtmosphereResource((AtmosphereResource<?, ?>) userBroadcaster.getAtmosphereResources().toArray()[0]);
        if (broadcaster.getAtmosphereResources().isEmpty()) {
            BroadcasterFactory.getDefault().remove(broadcaster, room);
            return null;
        }
        return new Broadcastable(new SimpleDTO(userBroadcaster.getID() + " has leaved " + room), "", broadcaster);
    }
}
