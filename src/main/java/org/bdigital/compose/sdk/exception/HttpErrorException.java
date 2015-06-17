package org.bdigital.compose.sdk.exception;

public class HttpErrorException extends Exception{
	
	public HttpErrorException() {
		super();
	}
	
    public HttpErrorException(String message) {
        super(message);
    }
	
    public HttpErrorException(String message, Throwable cause) {
        super(message, cause);
    }
	
}