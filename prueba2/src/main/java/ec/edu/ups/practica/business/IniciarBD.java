package ec.edu.ups.practica.business;
import ec.edu.ups.practica.DAO.PersonaDAO1;
import ec.edu.ups.practica.DAO.TransaccionDAO;
import ec.edu.ups.practica.prueba2.Persona1;
import ec.edu.ups.practica.prueba2.Transaccion;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

@Singleton
@Startup
public class IniciarBD {
	
	@Inject
	private PersonaDAO1 daoPe;
	
	@Inject
	private TransaccionDAO daoTran;
	
	@PostConstruct
	public void init() {
		
		Persona1 pe1 =  new Persona1();
		pe1.setCedula("0702981770");
		pe1.setNombre("Luis");
		pe1.setApellido("Toledo");
		pe1.setSaldo(1500.0);
		daoPe.insert(pe1);
		
		Persona1 pe2 = new Persona1();
		pe2.setCedula("0151273042");
		pe2.setNombre("Diego");
		pe2.setApellido("Bravo");
		pe2.setSaldo(1500.0);
		daoPe.insert(pe2);
		
		Transaccion tr = new Transaccion();
		tr.setMonto(200.0);
		tr.setCedulaEmisor(pe1.getCedula());
		tr.setCedulaReceptor(pe2.getCedula());
		
		daoTran.guardar(tr);
		
		
	}
	

}
