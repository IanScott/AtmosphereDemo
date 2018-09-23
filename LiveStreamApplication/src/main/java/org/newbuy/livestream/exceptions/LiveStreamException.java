package org.newbuy.livestream.exceptions;

/**
 * An Exception class for handling Application Exceptions.
 * @author Ian van Nieuwkoop
 * @version 1.0
 *
 */
public class LiveStreamException extends RuntimeException{

	private static final long serialVersionUID = -2644042243737688050L;
	
	/**
	 * Constructor with on parameter.
	 * @param message the error message to display.
	 */
	public LiveStreamException(String message) {
		super(message);
	}
}
