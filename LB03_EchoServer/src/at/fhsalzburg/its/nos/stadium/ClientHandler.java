package at.fhsalzburg.its.nos.stadium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import at.fhs.nos.stadium.message.CheckResult;
import at.fhs.nos.stadium.message.Message;
import at.fhs.nos.stadium.message.MessageType;
import at.fhs.nos.stadium.message.RegisterGateMessage;
import at.fhs.nos.stadium.message.TicketCheckMessage;
import at.fhs.nos.stadium.message.TicketCheckResponseMessage;

public class ClientHandler extends Thread {
	private BufferedReader reader;
	private PrintWriter out;
	private String secotrId;

	public ClientHandler(PrintWriter out, BufferedReader reader) {
		this.reader = reader;
		this.out = out;
	}
	
	@Override
	public void run() {
		while (true) {
            String ticket;
            try {
				while ((ticket = reader.readLine()) != null)
				{
					
	                //getting message
					
					Message message = Message.parse(ticket);
					
					//check messagetype
	                if(message.getType().equals(MessageType.REGISTER)) {
	                	RegisterGateMessage rgm = (RegisterGateMessage) message;
	                	this.secotrId = Integer.toString(rgm.getSectorId());
	                	System.out.println(message.getType()+" Message - Gate: SectorId: "+rgm.getSectorId());
	                	//out.println(rgm.toJsonString());
	                }
	                else if(message.getType().equals(MessageType.TICKETCHECK)) {
	                	TicketCheckMessage tcm = (TicketCheckMessage) message;
	                	
	                	//split message into ticketID and sectorID for function 'containsInTicketMap'
	                	String ticketID = tcm.getTicketId();
	                	System.out.println(message.getType() +" with ID: "+ticketID+" from Sektor: "+secotrId); 
	                	//check if ticket is in Whitelist
						CheckResult respondType = TicketReader.containsInTicketMap(ticketID, secotrId);
						TicketCheckResponseMessage trm = new TicketCheckResponseMessage (respondType);
						//send back a invalid statement
						out.println(trm.toJsonString());
						System.out.println("TicketID :" +ticketID +" "+trm.getCheckResult());
	                }
	                else if(message.getType().equals(MessageType.KEEPALIVE)) {
	                	//Send KeepAlive back and print on Server-Console info out
	                	Message kmsg = new Message(MessageType.KEEPALIVE);
						out.println(kmsg.toJsonString());
	                	System.out.println(message.getType()+" - sending respond to Gate...");
	                }
	                	
						
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
