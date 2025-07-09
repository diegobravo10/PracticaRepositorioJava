package ec.edu.ups.practica.DAO;

import java.util.List;

import ec.edu.ups.practica.prueba2.Transaccion;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

@Stateless
public class TransaccionDAO {
	
	  @PersistenceContext
	    private EntityManager em;

	    public void guardar(Transaccion transaccion) {
	        em.persist(transaccion);
	    }

	    public Transaccion buscarPorId(Long id) {
	        return em.find(Transaccion.class, id);
	    }

	    public List<Transaccion> listarTodas() {
	        return em.createQuery("SELECT t FROM Transaccion t", Transaccion.class)
	                 .getResultList();
	    }

	    public List<Transaccion> buscarPorEmisor(Long emisorId) {
	        return em.createQuery("SELECT t FROM Transaccion t WHERE t.emisor.id = :emisorId", Transaccion.class)
	                 .setParameter("emisorId", emisorId)
	                 .getResultList();
	    }

}
