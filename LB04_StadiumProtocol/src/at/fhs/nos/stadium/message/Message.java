package at.fhs.nos.stadium.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Base class of messages for communication between server and client
 * 
 * @author Albert Kiefel
 *
 */
@JsonSubTypes({ @Type(value = RegisterGateMessage.class), @Type(value = TicketCheckMessage.class),
		@Type(value = TicketCheckResponseMessage.class), })
public class Message {

	@JsonProperty
	private MessageType type;

	public Message() {
	}

	/**
	 * Constructor
	 * @param type the {@link MessageType}
	 */
	public Message(MessageType type) {
		this.type = type;
	}

	public MessageType getType() {
		return type;
	}

	/**
	 * Parses a JSON into a Message object
	 * 
	 * @param json
	 *            The JSON string to be parsed
	 * @return a Message object or a sub type of Message
	 */
	public static Message parse(String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, Message.class);
		} catch (Exception e) {
		}

		try {
			return mapper.readValue(json, RegisterGateMessage.class);
		} catch (Exception e) {
		}

		try {
			return mapper.readValue(json, TicketCheckMessage.class);
		} catch (Exception e) {
		}

		try {
			return mapper.readValue(json, TicketCheckResponseMessage.class);
		} catch (Exception e) {
		}

		throw new RuntimeException("Not able to parse json into a known message class");
	}

	/**
	 * @return the object as a JSON string
	 */
	public String toJsonString() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return null;
		}
	}
}
