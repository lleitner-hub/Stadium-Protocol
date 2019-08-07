package at.fhs.nos.stadium.message;

/**
 * Enumeration to differentiate between messages of client/server communication
 * 
 * @author Albert Kiefel
 *
 */
public enum MessageType {
	REGISTER, // Registration of a gate at the server at startup
	UNREGISTER, // Unregistration of a gate
	KEEPALIVE, // Keepalive to check if the server is still available
	TICKETCHECK, // Scanning a ticket at a gate
	CHECKRESPONSE // Response that is send after a ticket check
}
