package fr.dbo.poc.client.mvp;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

import fr.dbo.poc.client.place.DefaultPlace;


@WithTokenizers({DefaultPlace.Tokenizer.class})
public interface ApplicationPlaceHistoryMapper extends PlaceHistoryMapper {
}
