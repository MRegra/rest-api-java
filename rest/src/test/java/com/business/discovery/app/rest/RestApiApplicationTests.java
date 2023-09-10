package com.business.discovery.app.rest;

import com.business.discovery.app.rest.controller.model.Hours;
import com.business.discovery.app.rest.controller.model.OpeningHoursResponse;
import com.business.discovery.app.rest.controller.model.PlaceResponse;
import com.business.discovery.app.rest.controller.model.PlacesInfoResponse;
import com.business.discovery.app.rest.model.OpeningHoursEntity;
import com.business.discovery.app.rest.model.PlaceEntity;
import com.business.discovery.app.rest.service.PlacesInfoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class RestApiApplicationTests {

	private final String LABEL1 = "McDonalds";
	private final String LABEL2 = "Burger King";
	private final String LOCATION1 = "Coina, Barreiro";
	private final String LOCATION2 = "Zurich, Switzerland";
	private final String MONDAY = "Monday";
    private PlaceEntity placeEntity1;

	@Autowired
	private PlacesInfoService service;

	@Test
	void contextLoads() {
	}

	// In memory database would be a good idea, H2 is an example. I did not have enough time to implement
	@Test
	public void testGetAllPlacesInfo(){
		PlacesInfoResponse places = service.getAllPlacesInfoWithOpeningHours();
		Assertions.assertThat(places.getPlaces().size()).isEqualTo(1);
		Assertions.assertThat(places.getPlaces()).extracting(PlaceResponse::getLabel).containsExactlyInAnyOrder(LABEL2);
		Assertions.assertThat(places.getPlaces()).extracting(PlaceResponse::getLocation).containsExactlyInAnyOrder(LOCATION2);
		Assertions.assertThat(places.getPlaces()).flatExtracting(PlaceResponse::getDays).extracting(OpeningHoursResponse::getDay).contains(MONDAY);
		Assertions.assertThat(places.getPlaces()).flatExtracting(PlaceResponse::getDays).flatExtracting(OpeningHoursResponse::getOpeningHours)
				.extracting(Hours::getStart).contains("09:00");
	}

	@Test
	public void getResultListTest() {
		EntityManager emMock = Mockito.mock(EntityManager.class);

		PlacesInfoService service1 = new PlacesInfoService(emMock);

		Query queryMock = Mockito.mock(Query.class);
		when(queryMock.getResultList()).thenReturn(Arrays.<Object[]>asList(new Object[]{1, LABEL1, LOCATION1, MONDAY, "09:00", "21:00", "11:00", "12:30"}));
		String queryString = "SELECT p.uid, p.label, p.location, o.day, o.open_time, o.close_time, o.break_start_time, o.break_end_time FROM place p JOIN opening_hours o ON o.fk_places_uid = p.uid ";
		when(emMock.createNativeQuery(queryString)).thenReturn(queryMock);
		List<Object[]> queryResult = service1.getResultList(queryString);
		Assertions.assertThat(queryResult.size()).isEqualTo(1);
		Assertions.assertThat((Integer) queryResult.get(0)[0]).isEqualTo(1);
		Assertions.assertThat((String) queryResult.get(0)[1]).isEqualTo(LABEL1);
		Assertions.assertThat((String) queryResult.get(0)[2]).isEqualTo(LOCATION1);
		Assertions.assertThat((String) queryResult.get(0)[3]).isEqualTo(MONDAY);
		Assertions.assertThat((String) queryResult.get(0)[4]).isEqualTo("09:00");
		Assertions.assertThat((String) queryResult.get(0)[5]).isEqualTo("21:00");
		Assertions.assertThat((String) queryResult.get(0)[6]).isEqualTo("11:00");
		Assertions.assertThat((String) queryResult.get(0)[7]).isEqualTo("12:30");
	}

	@BeforeEach
	public void setup(){
		OpeningHoursEntity openingHoursEntity = new OpeningHoursEntity(MONDAY, "09:00", "21:00", "11:00", "12:30");
		placeEntity1 = new PlaceEntity(LABEL2, LOCATION2, new ArrayList<>(Collections.singleton(openingHoursEntity)));
		service.addPlace(placeEntity1);
	}

	@AfterEach
	public void clearDb() {
		service.deletePlace(placeEntity1.getUid());
	}
}