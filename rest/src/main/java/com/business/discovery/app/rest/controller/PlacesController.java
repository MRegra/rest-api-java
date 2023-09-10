package com.business.discovery.app.rest.controller;

import com.business.discovery.app.rest.controller.model.PlacesInfoResponse;
import com.business.discovery.app.rest.model.PlaceEntity;
import com.business.discovery.app.rest.service.PlacesInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author MRegra on 10/09/2023
 */

@RestController
public class PlacesController {

    @Autowired
    private PlacesInfoService service;

    @GetMapping("/")
    public String welcome(){
        return "Welcome to the Business Discovery App API";
    }

    @GetMapping("/places")
    public List<PlaceEntity> getPlacesInfo(){
        return service.getAllPlacesInfo();
    }

    @GetMapping("/places/info")
    public PlacesInfoResponse getAllPlacesInfoWithOpeningHours(){
        return service.getAllPlacesInfoWithOpeningHours();
    }

    @PostMapping("/places/add")
    public String addPlace(@RequestBody PlaceEntity placeEntity){
        return service.addPlace(placeEntity);
    }

    //Put (Update)

    //Delete (Delete)
    @DeleteMapping("/places/delete/{placeUid}")
    public String deletePlace(@PathVariable("placeUid") long placeUid){
        return service.deletePlace(placeUid);
    }

}
