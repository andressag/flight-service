package com.andressa.flights.controller;

import com.andressa.flights.model.FlightInfo;
import com.andressa.flights.service.FlightInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class FlightInfoControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FlightInfoService service;

    @Test
    public void shouldReturnFlightList() throws Exception {

        // Given
        final FlightInfo flightInfoOne = new FlightInfo("Air Canada 8099", LocalTime.parse("07:30"));
        final FlightInfo flightInfoTwo = new FlightInfo("United Airline 6115", LocalTime.parse("10:30"));
        final LocalTime searchTime = LocalTime.parse("06:00");

        // When
        final List<FlightInfo> flightInfos = Arrays.asList(flightInfoOne, flightInfoTwo);
        when(service.getAvailableFlights(searchTime)).thenReturn(flightInfos);

        // Then
        mvc.perform(get("/flights/" + searchTime)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].flight", is(flightInfoOne.getFlight())))
                .andExpect(jsonPath("$[1].flight", is(flightInfoTwo.getFlight())));
        verify(service, VerificationModeFactory.times(1)).getAvailableFlights(searchTime);
        reset(service);
    }

}

