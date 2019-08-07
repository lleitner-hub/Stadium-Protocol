/*
 * Copyright (c) 1995, 2013, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

package at.fhsalzburg.its.nos.stadium;

import at.fhs.nos.stadium.message.RegisterGateMessage;
import at.fhs.nos.stadium.message.TicketCheckMessage;

import java.io.*;
import java.net.*;


public class EchoClient
{
    public static void main(String[] args) throws IOException
    {
        String hostName = "localhost";
        int portNumber = 5000;
        Socket echoSocket = null; 
        //generate sektorID and gate-Massage
        double randomDouble = Math.random();
		randomDouble = randomDouble * 10 + 1; //Range of sectors: randomDouble*Amount +1
		int sektorID = (int) randomDouble;
		
		RegisterGateMessage gate = new RegisterGateMessage(sektorID);
		Thread t = null, keepalive = null, keepaliveCheck= null;
		
        try
        {
        	echoSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
            //send gate register
            out.println(gate.toJsonString());
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            //define task for thead 
            t = new ServerHandler(in);
            keepalive = new KeepAlive_Send(out);
            keepaliveCheck = new KeepAlive_Validate(in);
            //start thread --> goes to run() function in ServerHandler.java
            t.start();
            keepalive.start();
            keepaliveCheck.start();
     
            
            String userInput;
            while ((userInput = stdIn.readLine()) != null)
            {
            	//send ticket
                TicketCheckMessage ticket = new TicketCheckMessage(userInput);
                out.println(ticket.toJsonString());
            }
        }
        catch (UnknownHostException e)
        {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        }
        catch (IOException e)
        {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
        finally
        {
        	//interruupt --> an threats t, keepalive and keepaliveCheck
        	t.interrupt();
        	keepalive.interrupt();
        	keepaliveCheck.interrupt();
        	echoSocket.close();
		}
    }
}

