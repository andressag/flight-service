package com.andressa.flights.service;

import com.andressa.flights.model.FlightInfo;
import org.junit.Test;

import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class FlightInfoServiceTest {

    @Test
    public void shouldReturnFlightInfo() {

        // Given
        final LocalTime searchTime = LocalTime.parse("06:30");
        final FlightInfo found = new FlightInfo("Air Canada 8099", LocalTime.parse("07:30"));

        // When
        final List<FlightInfo> results = new FlightInfoService().getAvailableFlights(searchTime);

        // Then
        assertNotNull(results);
        FlightInfo first = results.get(0);
        assertEquals(first.getFlight(), found.getFlight());
        assertEquals(first.getDeparture(), found.getDeparture());
    }

    @Test
    public void shouldReturnOnlyTwoFlights() {

        // Given
        final LocalTime searchTime = LocalTime.parse("06:30");
        final FlightInfo flightInfoOne = new FlightInfo("Air Canada 8099", LocalTime.parse("07:30"));
        final FlightInfo flightInfoTwo = new FlightInfo("United Airline 6115", LocalTime.parse("10:30"));
        final FlightInfo flightInfoThree = new FlightInfo("WestJet 6456", LocalTime.parse("12:30"));
        final FlightInfo flightInfoFour = new FlightInfo("Delta 3833", LocalTime.parse("15:00"));

        // When
        final List<FlightInfo> results = new FlightInfoService().getAvailableFlights(searchTime);

        // Then
        assertThat(results)
                .extracting("flight")
                .containsOnly(flightInfoOne.getFlight(), flightInfoTwo.getFlight())
                .doesNotContain(flightInfoThree.getFlight(), flightInfoFour.getFlight());
    }

    @Test
    public void shouldReturnEmptyList() {

        // Given
        final LocalTime searchTime = LocalTime.parse("19:30");

        // When
        final List<FlightInfo> results = new FlightInfoService().getAvailableFlights(searchTime);

        // Then
        assertThat(results).isEmpty();
    }
}