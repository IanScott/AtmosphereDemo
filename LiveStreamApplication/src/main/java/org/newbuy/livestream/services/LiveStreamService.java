package org.newbuy.livestream.services;

import java.util.List;

import org.newbuy.livestream.model.LiveStreamRequest;
import org.newbuy.livestream.model.LiveStreamResponse;


/**
 * Interface representing the LiveStream Service. Class containing the main logic of for the controller class.
 * @author Ian van Nieuwkoop
 * @version 1.0
 *
 */
public interface LiveStreamService {
	
	/**
	 * Method which handles the broadcasting of messages.
	 * @param authHeaders the Authentication Headers of the Request.
	 * @param lsrequest the incoming request received from a client.
	 * @return an object representing the response for all connected suspended clients.
	 */
	public abstract LiveStreamResponse broadcast(List<String> authHeaders, LiveStreamRequest lsrequest);
	
	/**
	 * Method used to suspend the connection with clients.
	 * @return an initial response to the client.
	 */
	public abstract String suspend();
}


