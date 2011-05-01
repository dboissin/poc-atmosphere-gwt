package fr.dbo.poc.client.ui;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.LayoutPanel;


public interface ApplicationLayout {

      LayoutPanel getMainLayoutPanel();

      AcceptsOneWidget getToolbarContainer();

      AcceptsOneWidget getScreenContainer();

      AcceptsOneWidget getSideContainer();

      void setDefaultLayout();

}
