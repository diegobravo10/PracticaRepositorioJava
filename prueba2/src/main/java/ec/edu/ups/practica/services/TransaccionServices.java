package ec.edu.ups.practica.services;

import java.util.List;

import ec.edu.ups.practica.business.TransaccionON;
import ec.edu.ups.practica.prueba2.Transaccion;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/transaccion")
public class TransaccionServices {
	
	
	 @Inject
	    private TransaccionON transaccionON;

	    @POST
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response realizarTransferencia(Transaccion dto) {
	        try {
	            transaccionON.realizarTransferencia(
	                dto.getCedulaEmisor(),
	                dto.getCedulaReceptor(),
	                dto.getMonto()
	            );

	            return Response.ok(new MensajeJSON("success", "Transferencia realizada exitosamente")).build();

	        } catch (Exception e) {
	            return Response.status(Response.Status.BAD_REQUEST)
	                           .entity(new MensajeJSON("error", e.getMessage()))
	                           .build();
	        }
	    }
	    
	    @GET
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response getTodasLasTransacciones() {
	        try {
	            List<Transaccion> lista = transaccionON.listarTodas();
	            
	            if (lista.isEmpty()) {
	                return Response.status(Response.Status.NOT_FOUND)
	                               .entity(new MensajeJSON("info", "No hay transacciones registradas"))
	                               .build();
	            }

	            return Response.ok(lista).build();

	        } catch (Exception e) {
	            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	                           .entity(new MensajeJSON("error", e.getMessage()))
	                           .build();
	        }
	    }

}
