package ec.edu.ups.practica.prueba2;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
public class Persona1 implements Serializable{
	 @Id
	private String cedula;
	private String apellido; 
    private String nombre;
    private Double saldo;

	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
    
    

	
	
	
	
	

}
