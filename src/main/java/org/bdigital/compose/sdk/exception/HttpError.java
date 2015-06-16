package org.bdigital.compose.sdk.exception;

public class HttpError extends Exception{
	
	public HttpError() {
		super();
	}
	
    public HttpError(String message) {
        super(message);
    }
	
    public HttpError(String message, Throwable cause) {
        super(message, cause);
    }
	
}