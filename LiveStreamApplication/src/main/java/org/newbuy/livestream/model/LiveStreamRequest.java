package org.newbuy.livestream.model;

import java.util.UUID;

/**
 * Class representing a Request from a client.
 * @author Ian van Nieuwkoop
 * @version 1.0
 *
 */
public class LiveStreamRequest {
    private String push_message;
	private UUID sender_id;
	
	/**
	 * Default Constructor.
	 */
    public LiveStreamRequest(){
    	super();
    }
    
    /**
     * Constructor with two parameters.
     * @param push_message a String representation of the Message.
     * @param sender_id a String representation of the UUID of the sender.
     */
	public LiveStreamRequest(String push_message, UUID sender_id) {
		this.push_message = push_message;
		this.sender_id = sender_id;
	}
	
	/**
	 * Getter for push_message.
	 * @return the message to send.
	 */
    public String getPush_message() {
		return push_message;
	}
    
    /**
     * Setter for push_message.
     * @param push_message a String representing a message.
     */
	public void setPush_message(String push_message) {
		this.push_message = push_message;
	}
	
	/**
	 * Getter for sender_id.
	 * @return a String representation of the sender's UUID.
	 */
	public UUID getSender_id() {
		return sender_id;
	}
	
	/**
	 * Setter for sender_id.
	 * @param sender_id a String representation of the Sender's UUID.
	 */
	public void setSender_id(UUID sender_id) {
		this.sender_id = sender_id;
	}
}