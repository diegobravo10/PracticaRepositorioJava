package ec.edu.ups.practica.services;

import java.util.List;

import ec.edu.ups.practica.business.PersonaON;
import ec.edu.ups.practica.prueba2.Persona1;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/persona")
public class PersonaServices {


	@Inject
	private PersonaON onContactos;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON) 
	public Response addPersona(Persona1 p) {
		try {
			onContactos.guardarPersonas(p);
			MensajeJSON response = new MensajeJSON( "sucess",
		            "Persona agregada exitosamente."
		        );
		        return Response.ok(response).build();
		}catch(Exception e) {
		        
		  return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new MensajeJSON( "Error", e.getMessage())).build();
		}
		
	}	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPersona(){
		List<Persona1> listado = onContactos.getContactos();
		return Response.ok(listado).build();
	}
	
	@GET
	@Path("/{cedula}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerPersona(@PathParam("cedula") String cedula) {
	    try {
	        Persona1 p = onContactos.getPersonacedula(cedula);
	        
	        if (p == null) {
	            return Response.status(Response.Status.NOT_FOUND)
	                           .entity(new MensajeJSON("Error", "Persona no existe"))
	                           .build();
	        }
	        
	        return Response.ok(p).build();
	        
	    } catch (Exception e) {
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	                       .entity(new MensajeJSON("Error", e.getMessage()))
	                       .build();
	    }
	}
	
	@DELETE
	@Path("/{cedula}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarPersona(@PathParam("cedula") String cedula) {
	    try {
	        Persona1 p = onContactos.getPersonacedula(cedula);
	        
	        if (p == null) {
	            return Response.status(Response.Status.NOT_FOUND)
	                           .entity(new MensajeJSON("Error", "Persona no existe"))
	                           .build();
	        }

	        onContactos.eliminarPersona(cedula);

	        return Response.ok(new MensajeJSON("OK", "Persona eliminada")).build();
	        
	    } catch (Exception e) {
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	                       .entity(new MensajeJSON("Error", e.getMessage()))
	                       .build();
	    }
	}

}
