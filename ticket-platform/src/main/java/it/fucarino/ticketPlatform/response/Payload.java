package it.fucarino.ticketPlatform.response;

import org.springframework.http.HttpStatus;

public class Payload<T> {

	private T payload;
	
	private String errorMessageString;
	
	private HttpStatus status;
	
	public Payload() {}
	
	public Payload(T payload, String errorMessageString, HttpStatus status) {
		this.payload = payload;
		this.errorMessageString = errorMessageString;
		this.status = status;
	}



	public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}

	public String getErrorMessageString() {
		return errorMessageString;
	}

	public void setErrorMessageString(String errorMessageString) {
		this.errorMessageString = errorMessageString;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	
	
	
}
