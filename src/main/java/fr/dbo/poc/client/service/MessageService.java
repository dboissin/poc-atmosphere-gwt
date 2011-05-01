package fr.dbo.poc.client.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import fr.dbo.poc.shared.dto.OtherDTO;


@Path("/topic")
public interface MessageService extends RestService {

    @POST
    @Path("{name}")
    void publish(@PathParam("name") String topic, String message, MethodCallback<Void> callback);

    @POST
    @Path("/other/{name}")
    void other(@PathParam("name") String topic, OtherDTO message, MethodCallback<Void> callback);
}
