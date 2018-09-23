package org.newbuy.livestream;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.atmosphere.annotation.Broadcast;
import org.atmosphere.annotation.Suspend;
import org.atmosphere.config.service.AtmosphereService;
import org.atmosphere.jersey.JerseyBroadcaster;
import org.newbuy.livestream.eventlisteneradapters.LiveStreamEventListenerAdapter;
import org.newbuy.livestream.model.LiveStreamRequest;
import org.newbuy.livestream.services.LiveStreamService;
import org.newbuy.livestream.services.LiveStreamServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class representing a Websocket application, consisting of two endpoints. The first endpoint allows a
 * browser to connect using websockets. The second endpoint allows a client to connect using
 * HTTP and POST a message. The message is then send to all the browser that have a
 * websocket connections.
 * @author Ian van Nieuwkoop
 * @version 1.0
 *
 */
@Path("/")
@AtmosphereService (broadcaster = JerseyBroadcaster.class)
public class LiveStreamWebsocket {
	private final Logger logger = LoggerFactory.getLogger(LiveStreamWebsocket.class);
	
	private final LiveStreamService liveStreamService = new LiveStreamServiceImp();
	
	/**
	 * Default constructor.
	 */
	public LiveStreamWebsocket() {
		logger.debug("WebsocketController Constructor called");
	}
	
	/**
	 * The Get method for suspending a client.Method produces a html String.
	 * An AtmosphereEventListenerAdapter class is added to the broadcast object.
	 * @return Html String.
	 */
	@GET
	@Suspend(listeners = {LiveStreamEventListenerAdapter.class})
	@Produces(MediaType.TEXT_HTML)
	public String suspend() {
		logger.info("suspend method called");
		return liveStreamService.suspend();
	}
	
	/**
	 * Method validates and broadcasts the received message to all suspended response. Method consumes JSON and produces an HTML String.
	 * JSON message must have the following structure:
	 * {"push_message":<message>, "sender_id": <valide_UUID>}
	 * And the request must have an �Authentication� header set to the value �Bearer <UUID>�.
	 * The sender_id and the UUID from the header must be both valid and identical.
	 * 
	 * Example JSON:
	 * {"push_message":"Hello World",
	 * "sender_id": "bfef4105-00bd-467d-b534-a5ed1ebe02ed"}
	 * 
	 * @param headers the HttpHeader of the Request.
	 * @param lsrequest the json request received from a client.
	 * @return Html String containing the users UUID and message.
	 */
	@POST
	@Broadcast(writeEntity = false)
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public String broadcast(@Context HttpHeaders headers, LiveStreamRequest lsrequest) {
		logger.info("broadcast method called");
		List<String> authHeaders = headers.getRequestHeader(HttpHeaders.AUTHORIZATION);
		return this.liveStreamService.broadcast(authHeaders, lsrequest).toHtml();
	}
}
