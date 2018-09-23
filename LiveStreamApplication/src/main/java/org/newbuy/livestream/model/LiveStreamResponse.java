package org.newbuy.livestream.model;

/**
 * Class representing a LiveStream Response from the Application.
 * @author Ian van Nieuwkoop
 * @version 1.0
 *
 */
public class LiveStreamResponse {
	private static final String SEPERATOR = ":";
	private static final String START_TAG = "<p>";
	private static final String END_TAG = "</p>";
	private String innerText;
	private String html;
	
	/**
	 * Constructor with two parameters.
	 * @param message_text a String representation of a Message.
	 * @param uuid a String representation of a UUID.
	 */
	public LiveStreamResponse(String message_text, String uuid) {
		StringBuilder sb = new StringBuilder();
		this.innerText = sb.append(uuid).append(SEPERATOR).append(message_text).toString();
		this.toHtml();
	}
	
	/**
	 * Method for returning the innertext of a html Node.
	 * @return the innerText for a html node.
	 */
	public String getInnerText() {
		return innerText;
	}
	
	/**
	 * Method which converts object into an html paragraph node.
	 * @return a String representation of an html paragraph nod with innerText.
	 */
	public String toHtml() {
		if(html == null) {
			StringBuilder sb = new StringBuilder();
			html =  sb.append(START_TAG).append(innerText).append(END_TAG).toString();
		}
		return html;
	}
	
	/**
	 * Overloaded equals method.
	 * @param lsr the LiveStreamResponse to compare
	 * @return true if equal, else false
	 */
	public boolean equals(LiveStreamResponse lsr) {
		if(lsr != null && this.html.equals(lsr.html)) {
			return true;
		}
		//default
		return false;
	}
}
