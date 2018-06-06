package com.andressa.flights.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;

public class FlightInfo {

    private String flight;
    private LocalTime departure;

    public FlightInfo(String company, LocalTime departure) {
        this.flight = company;
        this.departure = departure;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm a")
    public LocalTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalTime departure) {
        this.departure = departure;
    }

    @Override
    public String toString() {
        return flight;
    }
}
