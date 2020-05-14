package com.example.domain.error;


import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Setter;

@Setter
public class ErrorResponseBody {

    @JsonProperty("timestamp")
    private ZonedDateTime exceptionOccurrenceTime;
    @JsonProperty("status")
    private int status;
    @JsonProperty("error")
    private String error;
    @JsonProperty("message")
    private String message;
	
	
}
