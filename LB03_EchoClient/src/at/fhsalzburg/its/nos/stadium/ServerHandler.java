package at.fhsalzburg.its.nos.stadium;

import java.io.BufferedReader;
import java.io.IOException;
import at.fhs.nos.stadium.message.Message;
import at.fhs.nos.stadium.message.MessageType;
import at.fhs.nos.stadium.message.TicketCheckResponseMessage;

public class ServerHandler extends Thread {
	private BufferedReader reader;
	public static long dateKeepSend, dateKeepRespond = 0L;
	public static boolean isOnline = true;
	
	public ServerHandler(BufferedReader reader) {
		super();
		this.reader = reader;
	}
	
	@Override
	public void run() {
		String readLine;
		try {
				//check respond from server
				while((readLine = reader.readLine()) != null) {
				
					Message message = Message.parse(readLine);	
					//was it a normal response or a keepalive message?
					if(message.getType().equals(MessageType.CHECKRESPONSE)) {
						if(isOnline == true) {
							TicketCheckResponseMessage trm = (TicketCheckResponseMessage) message;
							System.out.println("Ticket: "+trm.getCheckResult());
						}else {
							System.out.println("Gate is in sleepmode.");
						}
					}else if(message.getType().equals(MessageType.KEEPALIVE)) {
						//set the time when the response of gate-keepalive came back
						dateKeepRespond = System.currentTimeMillis();
					}else {
						System.out.println("Unknown Protocoll Type: "+readLine);
					}
					
				}			
					
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}


