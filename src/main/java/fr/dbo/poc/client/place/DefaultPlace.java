package fr.dbo.poc.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class DefaultPlace extends Place {

    @Prefix("")
    public static class Tokenizer implements PlaceTokenizer<DefaultPlace> {

        public DefaultPlace getPlace(String token) {
            return new DefaultPlace();
        }

        public String getToken(DefaultPlace place) {
            return "";
        }

    }
}
