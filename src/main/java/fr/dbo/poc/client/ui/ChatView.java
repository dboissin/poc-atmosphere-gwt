package fr.dbo.poc.client.ui;

import com.google.gwt.user.client.ui.IsWidget;

public interface ChatView extends IsWidget {

    void setPresenter(Presenter presenter);

    void appendResMsg(String message);

    interface Presenter {

        void sendMessage(String message);

    }
}
