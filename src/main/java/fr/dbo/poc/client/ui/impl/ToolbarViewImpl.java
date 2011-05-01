package fr.dbo.poc.client.ui.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.dbo.poc.client.ui.ToolbarView;

public class ToolbarViewImpl implements ToolbarView {

    private static ToolbarViewImplUiBinder uiBinder = GWT
            .create(ToolbarViewImplUiBinder.class);

    interface ToolbarViewImplUiBinder extends UiBinder<FlowPanel, ToolbarViewImpl> {
    }

    private final FlowPanel menuPanel;

    private Presenter presenter;

    public ToolbarViewImpl() {
        menuPanel = uiBinder.createAndBindUi(this);
    }

    @Override
    public Widget asWidget() {
        return menuPanel;
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

}
