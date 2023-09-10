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

import java.util.List;
import java.util.Optional;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * @author MRegra on import java.util.List;/09/2023
 */
@Service
public class PlacesInfoService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private IPlacesInfoRepository repository;

    public PlacesInfoService(EntityManager em) {
        this.em = em;
    }

    public List<PlaceEntity> getAllPlacesInfo() {
        return repository.getAllPlacesInfo();
    }

    public String addPlace(PlaceEntity placeEntity){
        repository.save(placeEntity);
        return "New place added!";
    }

    public String deletePlace(long placeUid){
        Optional<PlaceEntity> placeEntity = repository.findById(placeUid);
        if(placeEntity.isPresent()){
            repository.delete(placeEntity.get());
            return "Place deleted!";
        }
        return "Nothing to be deleted";
    }

    public PlacesInfoResponse getAllPlacesInfoWithOpeningHours(){
        String queryString = "SELECT p.uid, p.label, p.location, o.day, o.open_time, o.close_time, o.break_start_time, o.break_end_time " +
                "FROM place p " +
                "JOIN opening_hours o ON o.fk_places_uid = p.uid ";
        return parseQueryResult(getResultList(queryString));
    }

    public List<Object[]> getResultList(String queryString){
        Query query = em.createNativeQuery(queryString);
        return (List<Object[]>) query.getResultList();
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
