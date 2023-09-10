package com.business.discovery.app.rest.service;

import com.business.discovery.app.rest.controller.model.Hours;
import com.business.discovery.app.rest.controller.model.OpeningHoursResponse;
import com.business.discovery.app.rest.controller.model.PlaceResponse;
import com.business.discovery.app.rest.controller.model.PlacesInfoResponse;
import com.business.discovery.app.rest.model.PlaceEntity;
import com.business.discovery.app.rest.repository.IPlacesInfoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author MRegra on import java.util.List;/09/2023
 */
@Service
public class PlacesInfoService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private IPlacesInfoRepository repository;

    public List<PlaceEntity> getAllPlacesInfo() {
        return repository.getAllPlacesInfo();
    }

    public String addPlace(PlaceEntity placeEntity){
        repository.save(placeEntity);
        return "New place added!";
    }

    public PlacesInfoResponse getAllPlacesInfoWithOpeningHours(){
        String queryString = "SELECT p.uid, p.label, p.location, o.day, o.open_time, o.close_time, o.break_start_time, o.break_end_time FROM place p JOIN opening_hours o ON o.fk_places_uid = p.uid ";
        Query query = em.createNativeQuery(queryString);
        List<Object[]> rawResultList = (List<Object[]>) query.getResultList();
        return parseQueryResult(rawResultList);
    }

    private PlacesInfoResponse parseQueryResult(List<Object[]> rawResultList){
        HashMap<Long, PlaceResponse> placesHashMap = new HashMap<>();
        for(Object[] row : rawResultList) {
            int next = 0;
            Long uid = (Long) row[next++];
            String label = (String) row[next++];
            String location = (String) row[next++];
            String day = (String) row[next++];
            String openTime = (String) row[next++];
            String closeTime = (String) row[next++];
            String breakStartTime = (String) row[next++];
            String breakEndTime = (String) row[next];
            List<Hours> hours = new ArrayList<>();
            hours.add(new Hours(openTime, breakStartTime));
            hours.add(new Hours(breakEndTime, closeTime));
            placesHashMap.computeIfAbsent(uid, p -> new PlaceResponse(label, location, new ArrayList<>()))
                    .addDayOpeningHour(new OpeningHoursResponse(day, hours));
        }
        return new PlacesInfoResponse(placesHashMap.values());
    }
}
