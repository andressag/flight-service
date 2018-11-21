package com.andressa.flights.service;

import com.andressa.flights.model.FlightInfo;

import java.time.LocalTime;
import java.util.List;

public interface FlightInfoService {

    List<FlightInfo> getAllFlights();
    List<FlightInfo> getAvailableFlights(LocalTime searchTime);
}
