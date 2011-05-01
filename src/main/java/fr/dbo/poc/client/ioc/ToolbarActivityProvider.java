package fr.dbo.poc.client.ioc;

import com.google.inject.Inject;
import com.google.inject.Provider;

import fr.dbo.poc.client.activity.ToolbarActivity;
import fr.dbo.poc.client.ui.ToolbarView;

public class ToolbarActivityProvider implements Provider<ToolbarActivity> {

    private final ToolbarView view;

    @Inject
    public ToolbarActivityProvider(ToolbarView view) {
        this.view = view;
    }

    public ToolbarActivity get() {
        return new ToolbarActivity(view);
    }

}
