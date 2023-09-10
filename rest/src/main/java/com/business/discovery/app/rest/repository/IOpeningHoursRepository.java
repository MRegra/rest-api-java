package com.business.discovery.app.rest.repository;

import com.business.discovery.app.rest.model.OpeningHoursEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author MRegra on 10/09/2023
 */
public interface IOpeningHoursRepository extends JpaRepository<OpeningHoursEntity, Long> {

    @Query(value = "SELECT * FROM opening_hours ", nativeQuery = true)
    List<OpeningHoursEntity> getAllOpeningHoursInfo();
}
