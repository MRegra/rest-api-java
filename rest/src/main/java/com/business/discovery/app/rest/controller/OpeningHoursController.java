package com.business.discovery.app.rest.controller;

import com.business.discovery.app.rest.model.OpeningHoursEntity;
import com.business.discovery.app.rest.service.OpeningHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author MRegra on 10/09/2023
 */
@RestController
public class OpeningHoursController {

    @Autowired
    private OpeningHoursService service;

    @GetMapping("/open/hours")
    public List<OpeningHoursEntity> getPlacesInfo(){
        return service.getAllOpeningHoursInfo();
    }

    @PostMapping("/open/hours/add")
    public String addOpeningHours(@RequestBody OpeningHoursEntity openingHoursEntity){
        return service.addOpeningHour(openingHoursEntity);
    }

    //Put (Update)

    //Delete (Delete)

}
