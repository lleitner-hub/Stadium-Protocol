package at.fhs.nos.stadium.message;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Message for requesting a ticket check
 * 
 * @author Albert Kiefel
 *
 */
public class TicketCheckMessage extends Message {

	@JsonProperty
	private String ticketId;

	/**
	 * Default Constructor
	 */
	public TicketCheckMessage() {
		super(MessageType.TICKETCHECK);
	}

	/**
	 * Construcotr
	 * 
	 * @param ticketId
	 *            The ID of the ticket
	 * @param sectorId
	 *            The sector where the ticket was scanned
	 */
	public TicketCheckMessage(String ticketId) {
		super(MessageType.TICKETCHECK);
		this.ticketId = ticketId;
	}

	public String getTicketId() {
		return ticketId;
	}
}
