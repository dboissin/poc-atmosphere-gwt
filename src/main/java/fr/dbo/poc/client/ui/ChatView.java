package fr.dbo.poc.client.ui;

import com.google.gwt.user.client.ui.IsWidget;

import fr.dbo.poc.shared.dto.MessageDTO;

public interface ChatView extends IsWidget {

    void setPresenter(Presenter presenter);

    void addRoom(String name, int nbUsers);
    
    void addMessage(MessageDTO message);

    interface Presenter {

        void joinRoom(String room);

        void sendMessage(String room, String message);
        
    }
}
