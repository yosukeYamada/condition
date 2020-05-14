package com.example.domain.error;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Setter;

@Setter
public class ErrorDetail {
	 @JsonProperty("detailMessage")
	private String detailMessage;
	
}
