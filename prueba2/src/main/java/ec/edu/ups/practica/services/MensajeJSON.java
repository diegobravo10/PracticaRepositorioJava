package ec.edu.ups.practica.services;

import java.time.LocalDateTime;

public class MensajeJSON {
	
	public String status;
	private String message;
	
	
	public MensajeJSON(String status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

	    



}
