package com.andressa.flights.service;

import com.andressa.flights.model.FlightInfo;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class DefaultFlightInfoService implements FlightInfoService {


    @Override
    public List<FlightInfo> getAllFlights() {
        return availableFlights();
    }

    public List<FlightInfo> getAvailableFlights(LocalTime searchTime) {
        final List<FlightInfo> flightInfs = availableFlights();
        final List<FlightInfo> foundFlights = new LinkedList<>();
        for (FlightInfo flightInfo : flightInfs) {
            if (flightInfo.getDeparture().isBefore(searchTime.plusHours(5))) {
                foundFlights.add(flightInfo);
            }
        }
        return foundFlights;
    }

    private List<FlightInfo> availableFlights() {
        final List<FlightInfo> flightInfos = new LinkedList<>();
        flightInfos.add(new FlightInfo("Air Canada 8099", LocalTime.parse("07:30")));
        flightInfos.add(new FlightInfo("United Airline 6115", LocalTime.parse("10:30")));
        flightInfos.add(new FlightInfo("WestJet 6456", LocalTime.parse("12:30")));
        flightInfos.add(new FlightInfo("Delta 3833", LocalTime.parse("15:00")));
        return flightInfos;
    }
}
