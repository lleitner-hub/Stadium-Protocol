package at.fhsalzburg.its.nos.stadium;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import at.fhs.nos.stadium.message.CheckResult;

public class TicketReader {
	
	public static synchronized CheckResult containsInTicketMap(String ticketId, String sectorId) {
		

		if(EchoServer.ticketsWhitelist.containsKey(ticketId)) {
			//get the right ticket from whitelist
			Ticket tempTicket = (Ticket) EchoServer.ticketsWhitelist.get(ticketId);

			//is it the right sector?
			if(tempTicket.getSektorId().equals(sectorId)) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"); //layout-string form .getDateVon(): 2019-04-17T09:43:38Z
				Date dateVonTicket = null;
				Date dateBisTicket = null;
				try {
					dateVonTicket = dateFormat.parse(tempTicket.getDateVon());
					dateBisTicket = dateFormat.parse(tempTicket.getDateBis());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
				Date dateNow = new Date();
				//is it the right date?
				if(dateVonTicket.before(dateNow) && dateBisTicket.after(dateNow)) {
					//put the ticket in blacklist and remove it from the whitelist
					EchoServer.ticketBlacklist.put(ticketId, tempTicket);
					EchoServer.ticketsWhitelist.remove(ticketId);
					return CheckResult.VALID;
				}
				return CheckResult.OUTOFDATE;
			}
			return CheckResult.WRONGSECTOR;
			
		}
		else if(EchoServer.ticketBlacklist.containsKey(ticketId)) {
			//ticket is in blacklist --> already used
			return CheckResult.USED;
		}
		return CheckResult.INVALID;
	}
}
