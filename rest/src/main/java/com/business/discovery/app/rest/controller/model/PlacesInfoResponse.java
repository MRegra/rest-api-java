package com.business.discovery.app.rest.controller.model;

import java.util.Collection;

/**
 * @author MRegra on 10/09/2023
 */
public class PlacesInfoResponse {

    private Collection<PlaceResponse> places;

    public PlacesInfoResponse(Collection<PlaceResponse> places) {
        this.places = places;
    }

    public Collection<PlaceResponse> getPlaces() {
        return places;
    }

    public void setPlaces(Collection<PlaceResponse> places) {
        this.places = places;
    }
}
