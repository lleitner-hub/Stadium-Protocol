package at.fhsalzburg.its.nos.stadium;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class CSVReader {
	
	
	public static Map<String, Ticket> readCSVintoMap() {
		//Source: https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
		    String csvFile = "C:\\FH\\4Semester\\NOS\\LB\\ticket_data.csv";
	        BufferedReader br = null;
	        String line = "";
	      
	        Map<String, Ticket> tickets = new HashMap<>();
	        try {

	            br = new BufferedReader(new FileReader(csvFile));
	       
	            while ((line = br.readLine()) != null) {
	            	//split line - create Ticket and save to temp-whitelist called tickets
	            	String[] ticket = line.split(",");
	            	Ticket tempTicket = new Ticket(ticket[0],ticket[1],ticket[2],ticket[3]);
	            	tickets.put(ticket[0],tempTicket);
	            }

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        return tickets;
	}
}
