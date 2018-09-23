package org.newbuy.livestream.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.JsonMappingException;

/**
 * An ExceptionMapper Class for converting a JsonMapping Exception into a 442 Status code.
 * @author Ian van Nieuwkoop
 * @version 1.0
 */
@Provider
public class JsonMappingExeptionMapper implements ExceptionMapper<JsonMappingException> {

	@Override
	public Response toResponse(JsonMappingException ex) {
	    return Response.status(422).
	    	      entity(ex.getMessage()).
	    	      type("text/plain").
	    	      build();
	}

}
