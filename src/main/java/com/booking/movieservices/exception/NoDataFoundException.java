package com.booking.movieservices.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NoDataFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoDataFoundException() {

        super("No data found");
    }
}