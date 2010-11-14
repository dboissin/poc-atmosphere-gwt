package com.blogspot.dboissin.simplegallery.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class GalleryPlace extends Place {

	private String galleryName;

	public GalleryPlace(String token) {
		this.galleryName = token;
	}

	public String getGalleryName() {
		return galleryName;
	}

	@Prefix("gallery")
	public static class Tokenizer implements PlaceTokenizer<GalleryPlace> {

		public String getToken(GalleryPlace place) {
			return place.getGalleryName();
		}

		public GalleryPlace getPlace(String token) {
			return new GalleryPlace(token);
		}
	}

}
