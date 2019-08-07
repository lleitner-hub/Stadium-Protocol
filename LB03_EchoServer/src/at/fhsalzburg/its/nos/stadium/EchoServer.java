/*
 * Copyright (c) 2013, Oracle and/or its affiliates. All rights reserved. Redistribution and use in source and binary
 * forms, with or without modification, are permitted provided that the following conditions are met: - Redistributions
 * of source code must retain the above copyright notice, this list of conditions and the following disclaimer. -
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following
 * disclaimer in the documentation and/or other materials provided with the distribution. - Neither the name of Oracle
 * or the names of its contributors may be used to endorse or promote products derived from this software without
 * specific prior written permission. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package at.fhsalzburg.its.nos.stadium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class EchoServer
{
	//load tickets into whitelist
	public static Map<String, Ticket> ticketsWhitelist = CSVReader.readCSVintoMap();
	public static Map<String, Ticket> ticketBlacklist = new HashMap<String, Ticket>();

    public static void main(String[] args) throws IOException
    {
    
    	EchoServer server = new EchoServer();
    	server.start();
    }
    
    private ArrayList<PrintWriter> writers = new ArrayList<>();
    
    public void start() {
        ServerSocket serverSocket = null;

        try
        {
            serverSocket = new ServerSocket(5000);
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                
                writers.add(out);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             
                //give thread write and reader- write to respond and reader to read the message
                Thread t = new ClientHandler(out,in);
                t.start();
            }
        }
        catch (IOException e)
        {
            System.out.println("Exception caught when trying to listen on port 5000 or listening for a connection");
            System.out.println(e.getMessage());
        }
        finally
        {
            try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }
    
    
	public synchronized void broadcast(String inputLine) {
		for (PrintWriter writer : writers) {
			writer.println(inputLine);
		}
	}
	
}
