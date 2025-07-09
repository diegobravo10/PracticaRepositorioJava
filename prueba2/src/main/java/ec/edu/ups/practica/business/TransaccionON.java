package ec.edu.ups.practica.business;


import java.util.List;
import java.time.LocalDateTime;

import ec.edu.ups.practica.DAO.PersonaDAO1;
import ec.edu.ups.practica.DAO.TransaccionDAO;
import ec.edu.ups.practica.prueba2.Persona1;
import ec.edu.ups.practica.prueba2.Transaccion;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class TransaccionON {
	
	  @Inject
	    private PersonaDAO1 personaDAO;

	    @Inject
	    private TransaccionDAO transaccionDAO;

	    public void realizarTransferencia(String cedulaEmisor, String cedulaReceptor, double monto) throws Exception {

	        if (monto <= 0) {
	            throw new Exception("Monto inválido");
	        }

	        Persona1 emisor = personaDAO.readCedula(cedulaEmisor);
	        Persona1 receptor = personaDAO.readCedula(cedulaReceptor);

	        if (emisor == null || receptor == null) {
	            throw new Exception("Emisor o receptor no encontrado");
	        }

	        if (emisor.getSaldo() < monto) {
	            throw new Exception("Saldo insuficiente");
	        }

	        // Realizamos la transferencia
	        emisor.setSaldo(emisor.getSaldo() - monto);
	        receptor.setSaldo(receptor.getSaldo() + monto);

	        personaDAO.update(emisor);
	        personaDAO.update(receptor);

	        Transaccion tx = new Transaccion();
	        tx.setCedulaEmisor(cedulaEmisor);
	        tx.setCedulaReceptor(cedulaReceptor);
	        tx.setMonto(monto);
	        tx.setFecha(LocalDateTime.now());

	        transaccionDAO.guardar(tx);
	    }
	
	    public List<Transaccion> listarTodas() {
	        return transaccionDAO.listarTodas();
	    }


}
