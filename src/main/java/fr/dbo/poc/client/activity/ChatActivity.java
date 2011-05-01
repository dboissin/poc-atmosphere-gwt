package fr.dbo.poc.client.activity;

import java.io.Serializable;
import java.util.List;

import org.atmosphere.gwt.client.AtmosphereClient;
import org.atmosphere.gwt.client.AtmosphereGWTSerializer;
import org.atmosphere.gwt.client.AtmosphereListener;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestServiceProxy;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.dbo.poc.client.service.MessageService;
import fr.dbo.poc.client.ui.ChatView;
import fr.dbo.poc.client.util.SimpleDTOCodec;
import fr.dbo.poc.client.util.SimpleDTOSerializer;
import fr.dbo.poc.shared.dto.OtherDTO;
import fr.dbo.poc.shared.dto.SimpleDTO;

public class ChatActivity extends AbstractActivity implements ChatView.Presenter {

    private static final String TOPIC_NAME = "titi";
    private static final String BLIP_URL = GWT.getHostPageBaseURL() + "gwtasync?topicname=" + TOPIC_NAME;
    private static final String TOPIC_URL = GWT.getHostPageBaseURL() + "async/topic";
    private final ChatView view;
    private AtmosphereClient client;

    public ChatActivity(ChatView view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        panel.setWidget(view.asWidget());
        initialize(BLIP_URL);
    }

    private void initialize(String url) {
        MyCometListener cometListener = new MyCometListener();
        AtmosphereGWTSerializer serializer = GWT.create(SimpleDTOSerializer.class);
        // set a small length parameter to force refreshes
        // normally you should remove the length parameter
        client = new AtmosphereClient(url, serializer, cometListener);
        client.start();
    }

    @Override
    public void sendMessage(String message) {
        MessageService service = GWT.create(MessageService.class);
        Resource resource = new Resource(TOPIC_URL);
        ((RestServiceProxy) service).setResource(resource);

        service.other(TOPIC_NAME, new OtherDTO(message, 42), new MethodCallback<Void>() {
            public void onSuccess(Method method, Void response) {

            }

            public void onFailure(Method method, Throwable exception) {
                Window.alert(exception.getMessage());
            }
        });
    }


    private class MyCometListener implements AtmosphereListener {
        private SimpleDTOCodec codec;

        { codec = GWT.create(SimpleDTOCodec.class); }

        public void onConnected(int heartbeat, int connectionID) {

        }

        public void onBeforeDisconnected() {
            // TODO Auto-generated method stub

        }

        public void onDisconnected() {
            // TODO Auto-generated method stub

        }

        public void onError(Throwable exception, boolean connected) {
            // TODO Auto-generated method stub

        }

        public void onHeartbeat() {
            // TODO Auto-generated method stub

        }

        public void onRefresh() {
            // TODO Auto-generated method stub

        }

        public void onMessage(List<? extends Serializable> messages) {
            for (Serializable message: messages) {
                JSONValue value = JSONParser.parseStrict(message.toString());
                SimpleDTO simple = codec.decode(value);
                view.appendResMsg(simple.getContent());
            }
        }

    }


}
