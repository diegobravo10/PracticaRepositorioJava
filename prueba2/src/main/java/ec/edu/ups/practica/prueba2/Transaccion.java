package ec.edu.ups.practica.prueba2;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.*;

@Entity
public class Transaccion implements Serializable {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cedulaEmisor;
    private String cedulaReceptor;

    private Double monto;
    private LocalDateTime fecha;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getCedulaEmisor() {
		return cedulaEmisor;
	}
	public void setCedulaEmisor(String cedulaEmisor) {
		this.cedulaEmisor = cedulaEmisor;
	}
	public String getCedulaReceptor() {
		return cedulaReceptor;
	}
	public void setCedulaReceptor(String cedulaReceptor) {
		this.cedulaReceptor = cedulaReceptor;
	}

	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
    
    
	
	
	

}
