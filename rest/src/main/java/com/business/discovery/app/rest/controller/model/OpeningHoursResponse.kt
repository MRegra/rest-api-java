package com.business.discovery.app.rest.controller.model

/**
 * @author MRegra on 10/09/2023
 */
data class OpeningHoursResponse(
        val day: String,
        val openingHours: List<Hours>
)
