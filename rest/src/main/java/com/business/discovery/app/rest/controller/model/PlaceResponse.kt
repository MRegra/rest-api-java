package com.business.discovery.app.rest.controller.model

/**
 * @author MRegra on 10/09/2023
 */
data class PlaceResponse(
        var label: String,
        val location: String,
        val days: ArrayList<OpeningHoursResponse>
){
    fun addDayOpeningHour(openingHoursResponse: OpeningHoursResponse){
        days.add(openingHoursResponse)
    }
}
