package com.genji.yaswanth.exceptions;

@SuppressWarnings("serial")
public class ShipmentTypeNotFoundException extends RuntimeException {

	public ShipmentTypeNotFoundException() {
		super();
	}

	public ShipmentTypeNotFoundException(String message) {
		super(message);
	}

}
