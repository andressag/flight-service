package com.andressa.flights.controller;

import com.andressa.flights.exception.FlightNotFoundException;
import com.andressa.flights.exception.TimeFormatNotConvertibleException;
import com.andressa.flights.model.FlightInfo;
import com.andressa.flights.service.FlightInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightInfoResource {

    private final FlightInfoService service;

    @Autowired
    public FlightInfoResource(FlightInfoService service) {
        this.service = service;
    }

    @GetMapping
    public List<FlightInfo> getAllFlights(@PathVariable("requestedTime") String time) {
        final LocalTime requestedTime = this.validateRequestedTime(time);
        try {
            return service.getAllFlights();
        } catch (Exception e) {
            throw new FlightNotFoundException();
        }
    }

    @GetMapping(path = "/{requestedTime}")
    public List<FlightInfo> getAvailableFlights(@PathVariable("requestedTime") String time) {
        final LocalTime requestedTime = this.validateRequestedTime(time);
        try {
            return service.getAvailableFlights(requestedTime);
        } catch (Exception e) {
            throw new FlightNotFoundException();
        }
    }

    private LocalTime validateRequestedTime(String time) {
        try {
            return LocalTime.parse(time);
        } catch (Exception e) {
            throw new TimeFormatNotConvertibleException(time);
        }
    }
}
