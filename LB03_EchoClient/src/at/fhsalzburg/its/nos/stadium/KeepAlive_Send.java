package at.fhsalzburg.its.nos.stadium;

import java.io.PrintWriter;

import at.fhs.nos.stadium.message.Message;
import at.fhs.nos.stadium.message.MessageType;

public class KeepAlive_Send extends Thread{
	private PrintWriter out;
	public KeepAlive_Send(PrintWriter out) {
		super();
		this.out = out;
	}
	
	@Override
	public void run() {
		try {
				//send keepalive request
				while(true) {
				Thread.sleep(5000); // milliseconds
				Message message = new Message(MessageType.KEEPALIVE);
				this.out.println(message.toJsonString());
				//set the time when the keepalive message was sent to server
				ServerHandler.dateKeepSend = System.currentTimeMillis();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//Handle interrupt from threads. 
				System.out.println("Gate has been closed\n");
				e.printStackTrace();
			}
			
		}
}
