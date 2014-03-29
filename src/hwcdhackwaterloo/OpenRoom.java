package hwcdhackwaterloo;

import static hwcdhackwaterloo.UWAPI.getJSONData;

import java.io.*;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import hwcdhackwaterloo.TimeAvailability;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Finds the closest open room from various
 * calls to UWAPI, and computes distances
 * 
 * Back-end of GUI
 * 
 * Coupled with Neil's Time Availability Function
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
	
	public static double findDistance(double lat1, double lon1, double lat2, double lon2)
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
	
	
	//this is an all-around method for doing all the room business
	//it only needs one parameter, a string with the *ABBREVIATED*
	//version of a building, and from that it will give the closest
	//building, and how long an empty room in said building will
	//be available for
	public String roomComp(String bldg, int desiredTime, int month, int day)
	{
		String json = getJSONData("/buildings/list");
        JSONObject obj = (JSONObject) JSONValue.parse(json);
        JSONArray courses = (JSONArray) obj.get("data");
        HashMap<String, double[]> roomCoords = new HashMap<>();
        for (Object course : courses) {
            JSONObject courseBlock = (JSONObject) course;
            double lat = Double.parseDouble(courseBlock.get("latitude").toString());
            double lon = Double.parseDouble(courseBlock.get("longitude").toString());
            double[] coordPair = {lat, lon};
            roomCoords.put((String) courseBlock.get("building_code"), coordPair);
        }
        
        String[] bldgsWithRooms = {"AL","B1","B2","BMH","C2","CPH","DC","DWE","E2","E3","E6","EV1","EV2","EV3","ESC","HH","M3","MC","ML","OPT","PAS","PHY","QNC","RCH"};
        int bldgCount  = bldgsWithRooms.length;
        double[] curCoords = roomCoords.get(bldg);
        double[] cpair;
        double roomDist = 0.0;
        double closestDist = 9999999; //random high value
        String closestBldg = "";
        String[] ignoreRooms = new String[24];
        int ignoreIndex = 0;
        boolean roomFound = false;
        
        // Begin room scan / iteration to neil's function
 	   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

 	   
        Calendar cal = Calendar.getInstance();
        cal.set(2014, month, day);
 	   int hour = cal.get(Calendar.HOUR_OF_DAY);
 	   int min = cal.get(Calendar.MINUTE);
 	   int totalMins = (60*hour) + min;
 	   
 	   int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
 	   String dayOfWeek;
 	   if(dayWeek==Calendar.MONDAY) dayOfWeek = "M";
 	   else if(dayWeek==Calendar.TUESDAY) dayOfWeek = "T";
 	   else if(dayWeek==Calendar.WEDNESDAY) dayOfWeek = "W";
 	   else if(dayWeek==Calendar.THURSDAY) dayOfWeek = "Th";
 	   else if(dayWeek==Calendar.FRIDAY) dayOfWeek = "F";
 	   else dayOfWeek = "S";
 	  TimeAvailability cra = new TimeAvailability();
 	   int freeMins = 0;
 	   
        
        while(!roomFound)
        {
        	for(int i = 0; i < bldgCount; i++)
        	{
        		if(Arrays.asList(ignoreRooms).contains(bldgsWithRooms[i]))
        		{
        			//this room has already been used
        			//ignore and move onwards
        		}
        		else
        		{
        			cpair = roomCoords.get(bldgsWithRooms[i]);
        			roomDist = findDistance(curCoords[0], curCoords[1], cpair[0], cpair[1]);
        			if(roomDist < closestDist)
        			{
        				closestDist = roomDist;
        				closestBldg = bldgsWithRooms[i];
        			}
        		}
        	}
        	
        	ignoreRooms[ignoreIndex] = closestBldg; //if a re-iteration is necessary, ignore this room
        	ignoreIndex++;
        	
        	for(int i = 99; i < 9001 ; i++)
      	   	{
      		   freeMins = cra.checkRoomAvailability(closestBldg, i, dayOfWeek, totalMins);
      		   if(freeMins==-1)//end of day case
      		   {
      			   roomFound = true;
      			   return closestBldg + " room " + i + " will be open for the rest of the day";
      			 
      		   }
      		   if(freeMins==-2)//weekend case
      		   {
      			   roomFound = true;
      			   return "It's the weekend, everything is open!";
      			 
      		   }
      		   if(freeMins>desiredTime)//everything else
      		   {
      			   roomFound = true;
      			   return closestBldg + " room " + i + " will be open for " + freeMins + " Minutes ";
      			   
      		   }
      	   }
        }
        
        return "NULL";
		
 	   
	}
	
	

}
