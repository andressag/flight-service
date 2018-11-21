package com.andressa.flights.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class TimeFormatNotConvertibleException extends RuntimeException{
    public TimeFormatNotConvertibleException(String time) {
        super("Time requested: " + time + ", is not in the right format. Please try: HH:MM ex: 06:00 or 15:00");
    }
}
