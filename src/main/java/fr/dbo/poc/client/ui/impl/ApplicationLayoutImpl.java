package fr.dbo.poc.client.ui.impl;

import static com.google.gwt.dom.client.Style.Unit.PCT;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import fr.dbo.poc.client.ui.ApplicationLayout;

public class ApplicationLayoutImpl implements ApplicationLayout {

    private static final int MAINMENU_HEIGHT = 10;
//    private static final int HMASTER_HEIGHT = 30;
    private static final int ASIDE_WIDTH = 25;
//    private static final int VMASTER_WIDTH = 20;

    private static ApplicationLayoutImplUiBinder uiBinder = GWT
    .create(ApplicationLayoutImplUiBinder.class);

    interface ApplicationLayoutImplUiBinder extends
    UiBinder<LayoutPanel, ApplicationLayoutImpl> {
    }

    private final LayoutPanel mainLayoutPanel;

    @UiField
    SimplePanel menuPanel;

    @UiField
    SimplePanel screenPanel;

    @UiField
    SimplePanel sidePanel;


    public ApplicationLayoutImpl() {
        mainLayoutPanel = uiBinder.createAndBindUi(this);
        setDefaultLayout();
    }

    @Override
    public LayoutPanel getMainLayoutPanel() {
        return mainLayoutPanel;
    }

    @Override
    public AcceptsOneWidget getToolbarContainer() {
        return new AcceptsOneWidget() {
            @Override
            public void setWidget(IsWidget w) {
                Widget widget = Widget.asWidgetOrNull(w);
                menuPanel.setWidget(widget);
            }
        };
    }

    @Override
    public AcceptsOneWidget getScreenContainer() {
        return new AcceptsOneWidget() {
            @Override
            public void setWidget(IsWidget w) {
                Widget widget = Widget.asWidgetOrNull(w);
                screenPanel.setWidget(widget);
            }
        };
    }

    @Override
    public void setDefaultLayout() {
        int height = 100 - MAINMENU_HEIGHT;
        mainLayoutPanel.setWidgetTopHeight(menuPanel, 0, PCT, MAINMENU_HEIGHT, PCT);
//        mainLayoutPanel.setWidgetRightWidth(sidePanel, 0, PCT, 0, PCT);
//        mainLayoutPanel.setWidgetTopHeight(screenPanel, MAINMENU_HEIGHT, PCT, height, PCT);
//        mainLayoutPanel.setWidgetLeftWidth(screenPanel, 0, PCT, 100, PCT);
        mainLayoutPanel.setWidgetLeftWidth(screenPanel, 0, PCT, 100 - ASIDE_WIDTH, PCT);
        mainLayoutPanel.setWidgetTopHeight(screenPanel, MAINMENU_HEIGHT, PCT, height, PCT);
        mainLayoutPanel.setWidgetTopHeight(sidePanel, MAINMENU_HEIGHT, PCT, height, PCT);
        mainLayoutPanel.setWidgetRightWidth(sidePanel, 0, PCT, ASIDE_WIDTH, PCT);
        mainLayoutPanel.animate(500);
    }

    @Override
    public AcceptsOneWidget getSideContainer() {
        return new AcceptsOneWidget() {
            @Override
            public void setWidget(IsWidget w) {
                Widget widget = Widget.asWidgetOrNull(w);
                sidePanel.setWidget(widget);
            }
        };
    }

}
