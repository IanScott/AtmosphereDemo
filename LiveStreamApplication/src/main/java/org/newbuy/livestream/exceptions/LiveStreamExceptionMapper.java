package org.newbuy.livestream.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * An ExceptionMapper Class for converting a LiveStreamException into a 442 Status code.
 * @author Ian van Nieuwkoop
 * @version 1.0
 *
 */
@Provider
public class LiveStreamExceptionMapper implements ExceptionMapper<LiveStreamException> {
  public Response toResponse(LiveStreamException ex) {
    return Response.status(422).
      entity(ex.getMessage()).
      type("text/plain").
      build();
  }
}