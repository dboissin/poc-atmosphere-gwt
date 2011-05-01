package fr.dbo.poc.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.dbo.poc.client.ui.ToolbarView;

public class ToolbarActivity extends AbstractActivity implements ToolbarView.Presenter {

    private final ToolbarView view;

    public ToolbarActivity(ToolbarView view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        panel.setWidget(view.asWidget());
    }

}
