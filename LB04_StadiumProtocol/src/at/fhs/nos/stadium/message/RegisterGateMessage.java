package at.fhs.nos.stadium.message;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Message for gate registration at the server
 * 
 * @author Albert Kiefel
 *
 */
public class RegisterGateMessage extends Message {

	@JsonProperty
	private int sectorId;

	/**
	 * Default Constructor
	 */
	public RegisterGateMessage() {
		super(MessageType.REGISTER);
	}

	/**
	 * Constructor
	 * 
	 * @param sectorId
	 *            The ID of the sector where the gate is located
	 * @param gateId
	 *            The ID of the gate
	 */
	public RegisterGateMessage(int sectorId) {
		super(MessageType.REGISTER);
		this.sectorId = sectorId;
	}

	public int getSectorId() {
		return sectorId;
	}
}
