package org.newbuy.livestream.services;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.newbuy.livestream.exceptions.LiveStreamException;
import org.newbuy.livestream.model.LiveStreamRequest;
import org.newbuy.livestream.model.LiveStreamResponse;

public class LiveStreamServiceImpTest {
	private String CORRECT_UUID;
	private String CORRECT_UUID2;
	private String INCORRECT_UUID;
	
	private String TEST_MESSAGE;
	
	private LiveStreamRequest REQUEST_CORRECT;
	private LiveStreamRequest REQUEST_CORRECT_UUID2;
	private LiveStreamRequest REQUEST_EMPTY_UUID;
	
	private String AUTHHEADER_CORRECT_SINGLE;
	private String AUTHHEADER_CORRECT_DOUBLE;
	
	private String AUTHHEADER_INCORRECT_SINGLE;
	
	private String AUTHHEADER_EMPTY;
	private String AUTHHEADER_NULL;
	
	private LiveStreamServiceImp service;
	
	/**
	 * Method to setup the Constants used in Tests.
	 */
	@Before
	public void setupConstants() {
		CORRECT_UUID = "bfef4105-00bd-467d-b534-a5ed1ebe02ed";
		CORRECT_UUID2 = "bfef4105-00bd-467d-b534-a5ed1ebe02ef";
		INCORRECT_UUID = "incorrect";
		
		TEST_MESSAGE = "Hello world";
		
		REQUEST_CORRECT = new LiveStreamRequest(TEST_MESSAGE, UUID.fromString(CORRECT_UUID));
		REQUEST_CORRECT_UUID2 = new LiveStreamRequest(TEST_MESSAGE, UUID.fromString(CORRECT_UUID2));
		REQUEST_EMPTY_UUID = new LiveStreamRequest(TEST_MESSAGE, null);
			
		AUTHHEADER_CORRECT_SINGLE = "Bearer "+CORRECT_UUID;
		AUTHHEADER_CORRECT_DOUBLE = AUTHHEADER_CORRECT_SINGLE +","+CORRECT_UUID;
		
		AUTHHEADER_INCORRECT_SINGLE = "Bearer "+INCORRECT_UUID;
		
		AUTHHEADER_EMPTY = "";
		AUTHHEADER_NULL = null;
		
		service = new LiveStreamServiceImp();
	}
	
	/**
	 * Method to Test happyflow. Correct message and correct single Authentication header.
	 */
	@Test
	public void broadcast_HappyFlow_Test() {
		List<String> authHeaders = new ArrayList<>();
		authHeaders.add(AUTHHEADER_CORRECT_SINGLE);
		LiveStreamResponse response = service.broadcast(authHeaders, REQUEST_CORRECT);
		LiveStreamResponse expectedResponse = new LiveStreamResponse(TEST_MESSAGE,CORRECT_UUID); 
		assertTrue (response.equals(expectedResponse));
	}
	
	/**
	 * Method to Test happyflow. Correct message and correct double Authentication header.
	 */
	@Test
	public void broadcast_HappyFlow_DoubleAuth_Test() {
		List<String> authHeaders = new ArrayList<>();
		authHeaders.add(AUTHHEADER_CORRECT_DOUBLE);
		LiveStreamResponse response = service.broadcast(authHeaders, REQUEST_CORRECT);
		LiveStreamResponse expectedResponse = new LiveStreamResponse(TEST_MESSAGE,CORRECT_UUID); 
		assertTrue (response.equals(expectedResponse));
	}
	
	/**
	 * Method to Test the use of an invalid UUID in Authentication Header.
	 */
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void broadcast_Invalid_UUID_Header_Test() {
		LiveStreamRequest REQUEST_INVALID_UUID = new LiveStreamRequest(TEST_MESSAGE, UUID.fromString(INCORRECT_UUID));
		List<String> authHeaders = new ArrayList<>();
		authHeaders.add(AUTHHEADER_CORRECT_SINGLE);
		service.broadcast(authHeaders, REQUEST_INVALID_UUID);
	}
	
	/**
	 * Method to Test the use of an invalid UUID in message.
	 */
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void broadcast_Invalid_UUID_Request_Test() {
		List<String> authHeaders = new ArrayList<>();
		authHeaders.add(AUTHHEADER_INCORRECT_SINGLE);
		service.broadcast(authHeaders, REQUEST_CORRECT);
	}
	
	/**
	 * Method to Test that UUID's in authHead and body are identical.
	 */
	@Test(expected = LiveStreamException.class)
	public void broadcast_UUID_NOT_IDENTICAL_Test() {
		List<String> authHeaders = new ArrayList<>();
		authHeaders.add(AUTHHEADER_CORRECT_DOUBLE);
		service.broadcast(authHeaders, REQUEST_CORRECT_UUID2);
	}
	
	/**
	 * Method to Test that UUID's in authHead and body are identical.
	 */
	@Test(expected = LiveStreamException.class)
	public void broadcast_Request_Empty_UUID_Test() {
		List<String> authHeaders = new ArrayList<>();
		authHeaders.add(AUTHHEADER_CORRECT_DOUBLE);
		service.broadcast(authHeaders, REQUEST_EMPTY_UUID);
	}
	
	/**
	 * Method to Test that UUID's in authHead and body are identical.
	 */
	@Test(expected = LiveStreamException.class)
	public void broadcast_Request_Authentication_Empty_Test() {
		List<String> authHeaders = new ArrayList<>();
		authHeaders.add(AUTHHEADER_EMPTY);
		service.broadcast(authHeaders, REQUEST_CORRECT);
	}
	
	/**
	 * Method to Test that UUID's in authHead and body are identical.
	 */
	@Test(expected = LiveStreamException.class)
	public void broadcast_Request_Authentication_NULL_Test() {
		List<String> authHeaders = new ArrayList<>();
		authHeaders.add(AUTHHEADER_NULL);
		service.broadcast(authHeaders, REQUEST_CORRECT);
	}
}
