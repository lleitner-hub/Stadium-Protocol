package at.fhs.nos.stadium.message;

/**
 * Enumeration to differentiate between results of the tick check
 * 
 * @author Albert Kiefel
 *
 */
public enum CheckResult {
	/**
	 * Entry is permitted
	 */
	VALID, // Ticket is valid

	/**
	 * Entry is denied
	 */
	INVALID, // Ticket is unknown
	OUTOFDATE, // Ticket is not yet valid or not valid anymore
	WRONGSECTOR, // Ticket is not valid in this sector
	USED // Ticket was already used
}
