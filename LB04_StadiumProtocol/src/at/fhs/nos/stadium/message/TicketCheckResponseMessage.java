package at.fhs.nos.stadium.message;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Message for telling the gate the result of the ticket check
 * 
 * @author Albert Kiefel
 *
 */
public class TicketCheckResponseMessage extends Message {

	@JsonProperty
	private CheckResult checkResult;

	/**
	 * Default Constructor
	 */
	public TicketCheckResponseMessage() {
		super(MessageType.CHECKRESPONSE);
	}

	/**
	 * Constructor
	 * 
	 * @param checkResult
	 *            the {@link CheckResult} that determines if a ticket is valid / not
	 *            valid and the reason if it was denied
	 */
	public TicketCheckResponseMessage(CheckResult checkResult) {
		super(MessageType.CHECKRESPONSE);
		this.checkResult = checkResult;
	}

	public CheckResult getCheckResult() {
		return checkResult;
	}
}
