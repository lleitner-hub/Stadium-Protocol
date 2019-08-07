package at.fhsalzburg.its.nos.stadium;

public class Ticket {
	public String ticketId,sektorId,dateVon,dateBis;

	Ticket(String tId,String sId,String dVon,String dBis){
		this.ticketId = tId;
		this.sektorId = sId;
		this.dateVon = dVon;
		this.dateBis = dBis;
	}
	public  String getTicketId() {
		return ticketId;
	}
	public  String getSektorId() {
		return sektorId;
	}
	public  String getDateVon() {
		return dateVon;
	}
	public  String getDateBis() {
		return dateBis;
	}
	
}
