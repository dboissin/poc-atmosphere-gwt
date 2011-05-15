package fr.dbo.poc.server.util;

import java.io.IOException;

import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.jersey.Broadcastable;
import org.codehaus.jackson.map.ObjectMapper;

public class PocBroadcastable extends Broadcastable {

    private Object callerMessage;

    public PocBroadcastable(Object message, Object callerMessage, Broadcaster b) {
        super(message, callerMessage, b);
        try {
            this.callerMessage = new ObjectMapper().writeValueAsString(callerMessage);
        } catch (IOException e) {
            this.callerMessage = callerMessage.toString();
        }
    }

    @Override
    public Object getResponseMessage() {
        return callerMessage;
    }

}
