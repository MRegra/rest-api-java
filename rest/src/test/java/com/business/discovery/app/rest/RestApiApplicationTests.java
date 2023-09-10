package com.business.discovery.app.rest;

import com.business.discovery.app.rest.controller.model.Hours;
import com.business.discovery.app.rest.controller.model.OpeningHoursResponse;
import com.business.discovery.app.rest.controller.model.PlaceResponse;
import com.business.discovery.app.rest.controller.model.PlacesInfoResponse;
import com.business.discovery.app.rest.model.OpeningHoursEntity;
import com.business.discovery.app.rest.model.PlaceEntity;
import com.business.discovery.app.rest.service.PlacesInfoService;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;

@SpringBootTest
class RestApiApplicationTests {

	private final String LABEL1 = "McDonalds";
	private final String LOCATION1 = "Coina, Barreiro";
	private final String MONDAY = "Monday";

	@Autowired
	private PlacesInfoService service;

	@Mock
	private EntityManager em;

	@Test
	void contextLoads() {
	}

	@Test
	public void testGetAllPlacesInfo(){
		PlacesInfoResponse places = service.getAllPlacesInfoWithOpeningHours();
		Assertions.assertThat(places.getPlaces().size()).isEqualTo(2);
		Assertions.assertThat(places.getPlaces()).extracting(PlaceResponse::getLabel).containsExactlyInAnyOrder(LABEL1, LABEL1);
		Assertions.assertThat(places.getPlaces()).extracting(PlaceResponse::getLocation).containsExactlyInAnyOrder(LOCATION1, LOCATION1);
		Assertions.assertThat(places.getPlaces()).flatExtracting(PlaceResponse::getDays).extracting(OpeningHoursResponse::getDay).contains(MONDAY);
		Assertions.assertThat(places.getPlaces()).flatExtracting(PlaceResponse::getDays).flatExtracting(OpeningHoursResponse::getOpeningHours)
				.extracting(Hours::getStart).contains("09:00");
	}

	@Test
	public void testGetAllPlacesInfoNoOpeningHours(){
		PlacesInfoResponse places = service.getAllPlacesInfoWithOpeningHours();
		Assertions.assertThat(places.getPlaces().size()).isEqualTo(2);
		Assertions.assertThat(places.getPlaces()).extracting(PlaceResponse::getLabel).containsExactlyInAnyOrder(LABEL1, LABEL1);
		Assertions.assertThat(places.getPlaces()).extracting(PlaceResponse::getLocation).containsExactlyInAnyOrder(LOCATION1, LOCATION1);
	}

	@BeforeEach
	public void setup(){
		OpeningHoursEntity openingHoursEntity = new OpeningHoursEntity(1, MONDAY, "09:00", "21:00", "11:00", "12:30");
		PlaceEntity placeEntity1 = new PlaceEntity(1, LABEL1, LOCATION1, new ArrayList<>());
		PlaceEntity placeEntity2 = new PlaceEntity(2, LABEL1, LOCATION1, new ArrayList<>(Collections.singleton(openingHoursEntity)));
		em.persist(placeEntity1);
		em.persist(placeEntity2);
	}
}
