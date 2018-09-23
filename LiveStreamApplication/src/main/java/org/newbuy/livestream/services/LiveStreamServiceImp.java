package org.newbuy.livestream.services;

import java.util.List;
import java.util.UUID;

import org.newbuy.livestream.exceptions.LiveStreamException;
import org.newbuy.livestream.model.LiveStreamRequest;
import org.newbuy.livestream.model.LiveStreamResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An implementation of the LiveStreamService Interface.
 * @author Ian van Nieuwkoop
 * @version 1.0
 *
 */
public class LiveStreamServiceImp implements LiveStreamService {
	private final Logger logger = LoggerFactory.getLogger(LiveStreamServiceImp.class);
	private static final int FIRST_INDEX = 0;
	private static final String EMPTY = "";
	private static final String BEARER = "Bearer ";
	private static final String SEPARATOR = ",";
	private static final String EXCEPTION_MESSAGE_INVALID_UUID_PART1 = "UUID in Header: ";
	private static final String EXCEPTION_MESSAGE_INVALID_UUID_PART2 = ", does not match Body: ";
	private static final String EXCEPTION_MISSING_AUTH_HEADER = "Request is missing Authentication Headers, parameter authHeader is NULL";
	private static final String EXCEPTION_SENDERID_INVALID = "Sender_id is NULL and invalid";
	
	/**
	 * Default Constructor.
	 */
	public LiveStreamServiceImp() {
		logger.debug("LiveStreamServiceImp Constructor called");
	}
	
	@Override
	public LiveStreamResponse broadcast(List<String> authHeaders, LiveStreamRequest lsrequest) {
		logger.debug("LiveStreamServiceImp broadcast method called");

		validateParameters(authHeaders, lsrequest);

		//lsrequest.getSender_id() cannot be null, has been validated in ValidateParameters
        return new LiveStreamResponse(lsrequest.getPush_message(), lsrequest.getSender_id().toString());
	}
	
	private void validateUUIDS(List<String> authHeaders, LiveStreamRequest lsrequest) {
		logger.debug("Validating UUIDS");
		UUID body_uuid = lsrequest.getSender_id();
		
		String authHead = authHeaders.get(FIRST_INDEX);
		UUID auth_uuid = null;
		
		//check if authHead contains multiple authentications. Only check first part.
		int separator_index = authHead.length();
		if(authHead.contains(SEPARATOR)) {
			separator_index =  authHead.indexOf(SEPARATOR);
		}
		
		//Get UUID as String from Head.
		if(authHead.startsWith(BEARER)) {
			auth_uuid = UUID.fromString(authHead.substring(BEARER.length(),separator_index));
			if(auth_uuid.equals(body_uuid)) {
				//succesfully validated
				return;
			}
		}
		

		//default
		logger.error("Validating UUIDS");
		
		String auth_uuid_asString;
		if(auth_uuid == null) {
			auth_uuid_asString = "NULL";
		}else {
			auth_uuid_asString = auth_uuid.toString();
		}
		
		String body_uuid_asString = body_uuid.toString();
		StringBuilder sb = new StringBuilder();
		String exception_message = sb.append(EXCEPTION_MESSAGE_INVALID_UUID_PART1).append(auth_uuid_asString).append(EXCEPTION_MESSAGE_INVALID_UUID_PART2).append(body_uuid_asString).toString();
		throw new LiveStreamException(exception_message);
	}
	
	private void validateParameters(List<String> authHeaders, LiveStreamRequest lsrequest) {
		logger.debug("Validating request parameters");
    	if(authHeaders == null || authHeaders.size()< 1|| authHeaders.get(FIRST_INDEX) == null) {
    		logger.error(EXCEPTION_MISSING_AUTH_HEADER);
    		throw new LiveStreamException(EXCEPTION_MISSING_AUTH_HEADER);
    	}
		
    	if(lsrequest.getSender_id() == null) {
    		logger.error(EXCEPTION_SENDERID_INVALID);
    		throw new LiveStreamException(EXCEPTION_SENDERID_INVALID);
    	}
    	validateUUIDS(authHeaders, lsrequest);
	}
	
	@Override
	public String suspend() {
		logger.debug("LiveStreamServiceImp suspend method called");
		return EMPTY;
	}
}
