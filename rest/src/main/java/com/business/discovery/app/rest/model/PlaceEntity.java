package com.business.discovery.app.rest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.util.List;

/**
 * @author MRegra on 10/09/2023
 */

@Entity
@Table(name  = "place")
public class PlaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column
    private String label;

    @Column
    private String location;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_places_uid", nullable = false, referencedColumnName = "uid")
    private List<OpeningHoursEntity> openingHourEntities;

    public PlaceEntity() {
    }

    //For testing
    public PlaceEntity(String label, String location, List<OpeningHoursEntity> openingHourEntities) {
        this.label = label;
        this.location = location;
        this.openingHourEntities = openingHourEntities;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<OpeningHoursEntity> getOpeningHours() {
        return openingHourEntities;
    }

    public void setOpeningHours(List<OpeningHoursEntity> openingHourEntities) {
        this.openingHourEntities = openingHourEntities;
    }
}
