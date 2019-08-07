package at.fhsalzburg.its.nos.stadium;

import java.io.BufferedReader;


public class KeepAlive_Validate extends Thread {

	public KeepAlive_Validate(BufferedReader in) {
		super();
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				//wait 5 second for respond
				Thread.sleep(5000);
				if((Math.abs(ServerHandler.dateKeepRespond-ServerHandler.dateKeepSend))>5000)
				{
					System.out.println("No connetion to Server - Gate goes to sleepmode.");
					//set isOnline to false cause server is offline.
					ServerHandler.isOnline = false;
				}
				else {
					ServerHandler.isOnline = true;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}
}
