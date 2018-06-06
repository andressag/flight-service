package com.andressa.flights.controller;

import com.andressa.flights.FlightNotFoundException;
import com.andressa.flights.TimeFormatNotConvertibleException;
import com.andressa.flights.model.FlightInfo;
import com.andressa.flights.service.FlightInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightInfoController {

    private final FlightInfoService service;

    @Autowired
    public FlightInfoController(FlightInfoService service) {
        this.service = service;
    }

    @ResponseBody
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
