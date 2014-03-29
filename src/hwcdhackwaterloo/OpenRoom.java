package hwcdhackwaterloo;

import java.io.*;
import java.util.*;

/**
 * Finds the closest open room from various
 * calls to UWAPI, and computes distances
 * 
 * Back-end of GUI
 * 
 * @author: DanielHopper
 * 
 */

public class OpenRoom {
	
	public static void main(String[] args)
	{
		/*
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		double distance = 0.0;
		double lat1 = 0;
		double lon1 = 0;
		double lat2 = 0;
		double lon2 = 0;
		
		try
		{
			System.out.println("Please enter first latitude:");
			lat1 = Double.parseDouble(br.readLine());
			System.out.println("Please enter first longitude");
			lon1 = Double.parseDouble(br.readLine());
			System.out.println("Please enter second latitude");
			lat2 = Double.parseDouble(br.readLine());
			System.out.println("Please enter second longitude");
			lon2 = Double.parseDouble(br.readLine());
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		
		distance = findDistance(lat1, lon1, lat2, lon2);
		
		System.out.println("\nThe distance between these two points is: " + distance);
		*/
		
		//Commented out block is a Console interface for testing findDistance function

	}
	
	public double findDistance(double lat1, double lon1, double lat2, double lon2)
	{
		int R = 6371; //radius of the earth
		
		double dLat = Math.toRadians(lat2-lat1); //difference in lat
		double dLon = Math.toRadians(lon2-lon1); //difference in lon
		double radLat1 = Math.toRadians(lat1); //convert lat1 to rads
		double radLat2 = Math.toRadians(lat2); //convert lat2 to rads
		
		/*
		 * The following is an implementation of the Haversine function
		 * source: http://www.movable-type.co.uk/scripts/latlong.html
		 * The Haversine function is used to calculate shortest distance
		 * over the earth's surface with given latitude and longitude
		 */
		
		double a  = Math.sin(dLat/2) * Math.sin(dLat/2) + 
		        	Math.sin(dLon/2) * Math.sin(dLon/2) * 
		        	Math.cos(radLat1) * Math.cos(radLat2);
		
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
		
		double distance = R * c;
		
		return distance;
		
	}
	
	public void roomComp()
	{
		
	    
	    
	}

}
