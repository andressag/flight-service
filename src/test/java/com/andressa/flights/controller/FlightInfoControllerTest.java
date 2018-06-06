package com.andressa.flights.controller;

import com.andressa.flights.FlightNotFoundException;
import com.andressa.flights.TimeFormatNotConvertibleException;
import com.andressa.flights.model.FlightInfo;
import com.andressa.flights.service.FlightInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalTime;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FlightInfoControllerTest {

    @Mock
    private FlightInfoService service;

    @Test
    public void testValidInput() {

        // Given
        final String timeRequested = "06:00";
        final FlightInfo[] flights = mockedFlights();
        when(service.getAvailableFlights(any(LocalTime.class))).thenReturn(asList(flights));

        // When
        final List<FlightInfo> results = new FlightInfoController(service).getAvailableFlights(timeRequested);

        // Then
        verify(service).getAvailableFlights(any(LocalTime.class));
        assertThat(results).containsOnly(flights);
    }

    @Test
    public void testInvalidInput() {

        // Given
        String timeRequested = "invalid";

        // When
        assertThatThrownBy(() -> {
            new FlightInfoController(service).getAvailableFlights(timeRequested);
        }).isInstanceOf(TimeFormatNotConvertibleException.class);

        // Then
        verifyZeroInteractions(service);
    }

    @Test
    public void testFlightNotFound() {

        // Given
        String timeRequested = "06:00";
        when(service.getAvailableFlights(any(LocalTime.class))).thenThrow(new FlightNotFoundException());

        // When
        assertThatThrownBy(() -> {
            new FlightInfoController(service).getAvailableFlights(timeRequested);
        }).isInstanceOf(FlightNotFoundException.class);

        // Then
        verify(service).getAvailableFlights(any(LocalTime.class));
    }

    private FlightInfo[] mockedFlights() {
        return new FlightInfo[] {
                new FlightInfo("Air Canada 8099", LocalTime.parse("07:30")),
                new FlightInfo("United Airline 6115", LocalTime.parse("10:30"))
        };
    }

}