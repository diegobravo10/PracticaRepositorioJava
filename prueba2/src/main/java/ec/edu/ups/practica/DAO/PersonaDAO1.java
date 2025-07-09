package ec.edu.ups.practica.DAO;

import java.util.List;

import ec.edu.ups.practica.prueba2.Persona1;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.*;

@Stateless
public class PersonaDAO1 {

	@PersistenceContext
	private EntityManager em;
	
	public void insert(Persona1 u) {
		em.persist(u);
	}

	public void update(Persona1 u) {
		em.merge(u);
			
		}
	
	public Persona1 read(String cedula ) {
		Persona1 u = em.find(Persona1.class, cedula);
		 return u;	
		}
	
	public void delete(String cedula) {
		Persona1 u = em.find(Persona1.class, cedula);
		 em.remove(u);	
		}
	
	public List<Persona1>  getAll (){
		String sql =  "SELECT p FROM Persona1 p";
		Query q = em.createQuery(sql, Persona1.class);
		return q.getResultList();
	}
	
	public Persona1 readCedula(String cedula) {
	    String jpql = "SELECT u FROM Persona1 u WHERE u.cedula = :cedula";
	    TypedQuery<Persona1> query = em.createQuery(jpql, Persona1.class);
	    query.setParameter("cedula", cedula);
	    List<Persona1> resultado = query.getResultList();
	    return resultado.isEmpty() ? null : resultado.get(0);
	}

}
