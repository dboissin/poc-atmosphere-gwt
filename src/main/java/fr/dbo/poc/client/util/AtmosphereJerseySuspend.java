package fr.dbo.poc.client.util;

import java.util.logging.Logger;

import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.http.client.Response;
import com.google.gwt.xhr.client.ReadyStateChangeHandler;
import com.google.gwt.xhr.client.XMLHttpRequest;



public class AtmosphereJerseySuspend {

    private static final Logger LOGGER = Logger.getLogger(AtmosphereJerseySuspend.class.getName());
    private final AtmosphereSuspendListener listener;
    private final String url;
    private int read = 0;

    public AtmosphereJerseySuspend(AtmosphereSuspendListener listener, String url) {
        this.listener = listener;
        this.url = url;
    }

    public void listen() {
        LOGGER.fine("url : " + url);
        read = 0;
        XMLHttpRequest xmlHttpRequest = XMLHttpRequest.create();
        try {
            xmlHttpRequest.open("GET", url);
            xmlHttpRequest.setRequestHeader("Accept", "application/json");
            xmlHttpRequest.setOnReadyStateChange(new ReadyStateChangeHandler() {
                @Override
                public void onReadyStateChange(XMLHttpRequest request) {
                    switch (request.getReadyState()) {
                    case XMLHttpRequest.LOADING:
                        LOGGER.finest("XMLHttpRequest.LOADING:" +
                                request.getStatus() + " : " + request.getResponseText());
                        onReceiving(request.getStatus(), request.getResponseText());
                        break;
                    case XMLHttpRequest.DONE:
                        LOGGER.fine("XMLHttpRequest.DONE:" +
                                request.getStatus() + " : "  + request.getResponseText());
                        onReceiving(request.getStatus(), request.getResponseText());
                        break;
                    }
                }
            });
            xmlHttpRequest.send();
        } catch (JavaScriptException e) {
            LOGGER.severe(e.getCause().getMessage());
            listener.onError(e.getMessage(), -1);
            if (xmlHttpRequest != null) {
                xmlHttpRequest.abort();
                xmlHttpRequest = null;
            }
        }
    }

    private void onReceiving(int status, String responseText) {
        if (status == Response.SC_OK) {
            String delta = responseText.substring(read);
            read = responseText.length();
            listener.onMessage(delta);
        } else {
            listener.onError(responseText.substring(read), status);
        }
    }

}
