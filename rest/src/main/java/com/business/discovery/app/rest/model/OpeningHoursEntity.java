package com.business.discovery.app.rest.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

/**
 * @author MRegra on 10/09/2023
 */

@Entity
@Table(name  = "opening_hours")
public class OpeningHoursEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column
    // Enum would be better
    private String day;

    @Column
    private String openTime;

    @Column
    private String closeTime;

    @Column
    private String breakStartTime;

    @Column
    private String breakEndTime;

    public OpeningHoursEntity() {
    }

    //For testing
    public OpeningHoursEntity(String day, String openTime, String closeTime, String breakStartTime, String breakEndTime) {
        this.uid = uid;
        this.day = day;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.breakStartTime = breakStartTime;
        this.breakEndTime = breakEndTime;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getBreakStartTime() {
        return breakStartTime;
    }

    public void setBreakStartTime(String breakStartTime) {
        this.breakStartTime = breakStartTime;
    }

    public String getBreakEndTime() {
        return breakEndTime;
    }

    public void setBreakEndTime(String breakEndTime) {
        this.breakEndTime = breakEndTime;
    }
}
