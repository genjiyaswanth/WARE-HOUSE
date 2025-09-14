package com.genji.yaswanth.exceptions;

public class PurchaseDetailsNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 public PurchaseDetailsNotFoundException() {
		 super();
	 }
	 
	 public PurchaseDetailsNotFoundException(String message) {
		 super(message);
	 }

}
