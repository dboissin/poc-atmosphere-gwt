package fr.dbo.poc.client.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestService;
import org.fusesource.restygwt.client.RestServiceProxy;

import com.google.gwt.core.client.GWT;

import fr.dbo.poc.shared.dto.MessageDTO;
import fr.dbo.poc.shared.dto.RoomInfosDTO;

@Path("/chat")
public interface ChatService extends RestService {

    public static class Util {

        private static final String SERVICE_URI = "async/chat";
        
        /**
         * Get the instance.
         * @return Service Instance.
         */
        public static ChatService getInstance() {
            ChatService instance = GWT.create(ChatService.class);
            Resource resource = new Resource(GWT.getHostPageBaseURL() + SERVICE_URI);
            ((RestServiceProxy) instance).setResource(resource);
            return instance;
        }
    }

    @PUT
    @Path("{room}/{user}")
    //public void subscribe(@PathParam("room") String room, @PathParam("user") String user, MethodCallback<Void> callback);
    public void subscribe(@PathParam("room") String room, @PathParam("user") String user, MethodCallback<RoomInfosDTO> callback);
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void publish(MessageDTO message, MethodCallback<Void> callback);

    @DELETE
    @Path("{room}/{user}")
    public void unsubscribe(@PathParam("user") String user, @PathParam("room") String room, MethodCallback<Void> callback);

}
