package com.andressa.flights;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FlightNotFoundException extends RuntimeException {
    public FlightNotFoundException() {
        super("Could not find flights for the the requested time");
    }
}
