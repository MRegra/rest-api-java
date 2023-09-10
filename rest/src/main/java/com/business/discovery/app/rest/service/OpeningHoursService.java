package com.business.discovery.app.rest.service;

import com.business.discovery.app.rest.model.OpeningHoursEntity;
import com.business.discovery.app.rest.repository.IOpeningHoursRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MRegra on 10/09/2023
 */
@Service
public class OpeningHoursService {

    @Autowired
    private IOpeningHoursRepository repository;

    public List<OpeningHoursEntity> getAllOpeningHoursInfo() {
        return repository.getAllOpeningHoursInfo();
    }

    public String addOpeningHour(OpeningHoursEntity openingHoursEntity){
        repository.save(openingHoursEntity);
        return "New opening hour added!";
    }
}
