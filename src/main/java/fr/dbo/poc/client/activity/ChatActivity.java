package fr.dbo.poc.client.activity;

import java.util.logging.Logger;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.dbo.poc.client.service.ChatService;
import fr.dbo.poc.client.ui.ChatView;
import fr.dbo.poc.client.util.AtmosphereJerseySuspend;
import fr.dbo.poc.client.util.AtmosphereSuspendListener;
import fr.dbo.poc.client.util.MessageDTOCodec;
import fr.dbo.poc.shared.dto.MessageDTO;
import fr.dbo.poc.shared.dto.RoomInfosDTO;

public class ChatActivity extends AbstractActivity implements ChatView.Presenter {

    private static final String USERNAME = "titi";
    //    private static final String CONNECT_URL = GWT.getHostPageBaseURL() + "gwtasync?user=" + USERNAME;
    private static final String LISTEN_URL = GWT.getHostPageBaseURL() + "async/chat/" + USERNAME; 

    private final ChatView view;
    // private AtmosphereClient client;
    private static final Logger LOGGER = Logger.getLogger(ChatActivity.class.getName()); 

    public ChatActivity(ChatView view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        panel.setWidget(view.asWidget());
        //initialize(CONNECT_URL);
        initialize(LISTEN_URL);
        // listen(LISTEN_URL);
        //        initialize(GWT.getHostPageBaseURL() + "async/chat/titiC");
    }

    private void initialize(String url) {
        //        MyCometListener cometListener = new MyCometListener();
        //        AtmosphereGWTSerializer serializer = GWT.create(MessageDTOSerializer.class);
        // set a small length parameter to force refreshes
        // normally you should remove the length parameter
        //        client = new AtmosphereClient(url, serializer, cometListener);
        //        client.start();

        new AtmosphereJerseySuspend(new MyCometListener(), url).listen();

    }

    @Override
    public void joinRoom(String room) {
        ChatService.Util.getInstance().subscribe(room, USERNAME, new MethodCallback<RoomInfosDTO>() {

            @Override
            public void onFailure(Method method, Throwable exception) {
                LOGGER.severe("fail" + exception.getMessage());
            }

            @Override
            public void onSuccess(Method method, RoomInfosDTO response) {
                if (method.getResponse().getStatusCode() == Response.SC_OK) {
                    view.addRoom(response.getRoomName(), response.getNbUsers());
                }
            }
        });    
    }

    @Override
    public void sendMessage(String room, String message) {
        ChatService.Util.getInstance().publish(new MessageDTO(USERNAME, message, room), new MethodCallback<Void>() {

            @Override
            public void onFailure(Method method, Throwable exception) {
                LOGGER.severe(exception.getMessage());
            }

            @Override
            public void onSuccess(Method method, Void response) {
            }
        });
    }

    private class MyCometListener implements AtmosphereSuspendListener {

        private MessageDTOCodec codec;
        private boolean mockInit = false;

        { codec = GWT.create(MessageDTOCodec.class); }

        @Override
        public void onMessage(String message) {
            LOGGER.fine(message);
            if (!mockInit) {
                mockInit = true;
                joinRoom("test");
            } else {
                JSONValue value = JSONParser.parseStrict(message.toString());
                MessageDTO msg = codec.decode(value);
                view.addMessage(msg);
            }
        }

        @Override
        public void onError(String error, int status) {
            LOGGER.severe(status + " : " + error);
        }

    }

    //    private class MyCometListener implements AtmosphereListener {
    //        private MessageDTOCodec codec;
    //        private boolean mockInit = false;
    //
    //        { codec = GWT.create(MessageDTOCodec.class); }
    //
    //        public void onConnected(int heartbeat, int connectionID) {
    //            if (!mockInit) {
    //                mockInit();
    //                mockInit = true;
    //            }
    //        }
    //
    //        public void onBeforeDisconnected() {
    //            // TODO Auto-generated method stub
    //
    //        }
    //
    //        public void onDisconnected() {
    //            // TODO Auto-generated method stub
    //
    //        }
    //
    //        public void onError(Throwable exception, boolean connected) {
    //            // TODO Auto-generated method stub
    //            Window.alert("onError : " + exception.getMessage() + " - " + exception.toString());
    //        }
    //
    //        public void onHeartbeat() {
    //            // TODO Auto-generated method stub
    //
    //        }
    //
    //        public void onRefresh() {
    //            // TODO Auto-generated method stub
    //
    //        }
    //
    //        public void onMessage(List<? extends Serializable> messages) {
    //            for (Serializable message: messages) {
    //                Window.alert(message.toString());
    //                JSONValue value = JSONParser.parseStrict(message.toString());
    //                MessageDTO msg = codec.decode(value);
    //                Window.alert(msg.getMessage());
    //                view.addMessage(msg);//appendResMsg(simple.getContent());
    //            }
    //        }
    //
    //    }

}
