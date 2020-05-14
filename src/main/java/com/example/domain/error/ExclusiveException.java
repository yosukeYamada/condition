package com.example.domain.error;

import lombok.Getter;

@Getter
public class ExclusiveException extends RuntimeException {

	public ExclusiveException(String message) {
		super(message);
	}

}
