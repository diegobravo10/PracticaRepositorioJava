package ec.edu.ups.practica.business;

import java.util.List;

import ec.edu.ups.practica.DAO.PersonaDAO1;
import ec.edu.ups.practica.prueba2.Persona1;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class PersonaON {
	
	@Inject
	private PersonaDAO1 daoPersona;
	
	
	public void guardarPersonas(Persona1 p) {
		// Buscar persona existente por cédula
		Persona1 pe = daoPersona.read(p.getCedula());

		if (pe == null) {
		    daoPersona.insert(p);
		} else {
		    daoPersona.update(p);
		}

		
	}
	
	public List<Persona1> getContactos(){
		return daoPersona.getAll();
	}
	
	public Persona1 getPersonacedula(String cedula) throws Exception {
		
		if (cedula.length() != 10) 
			 throw new Exception("Ta' loco mi pana");
		
		return daoPersona.readCedula(cedula);
	
		
	}
	
	public void eliminarPersona(String cedula) throws Exception {
		
		
		if (cedula.length() != 10) 
			 throw new Exception("Ta' loco mi pana");
		
		Persona1 pe =  daoPersona.readCedula(cedula);
		if(pe!= null) {
			daoPersona.delete(pe.getCedula());
		}
	}
	

}
