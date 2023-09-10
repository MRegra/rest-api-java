package com.business.discovery.app.rest.repository;

import com.business.discovery.app.rest.model.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author MRegra on 10/09/2023
 */
public interface IPlacesInfoRepository extends JpaRepository<PlaceEntity, Long> {

    @Query(value = "SELECT * FROM place", nativeQuery = true)
    List<PlaceEntity> getAllPlacesInfo();
}
