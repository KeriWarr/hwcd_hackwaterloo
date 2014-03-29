package hwcdhackwaterloo;

import static hwcdhackwaterloo.UWAPI.getJSONData;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

//for testing
import java.util.Scanner;

public class TimeAvailability {

	// checkRoomAvailability(String, String, int, int)
	//   FORMAT: time -> "XX:XX", day -> "M", "T", "W", "Th", "F"
	// returns 0 if the room is unavailable
	// otherwise, returns the number of hours said room is available for
	public int checkRoomAvailability(String building, int room_num, String day, int min) {
        //System.out.println("/buildings/" + building + "/" + room_num + "/courses");
        String json1 = getJSONData("/buildings/" + building + "/" + room_num + "/courses");
        JSONObject obj = (JSONObject) JSONValue.parse(json1);
        JSONObject meta = (JSONObject) obj.get("meta");
        JSONArray courses = (JSONArray) obj.get("data");
        ArrayList<TimeBlock> booked = new ArrayList<TimeBlock>();

        if(meta.get("message").toString().equals("No data returned")){
            return 0;
        }
        System.out.println("tits"+json1);
        for (Object course : courses) {
            JSONObject courseBlock = (JSONObject) course;
            //System.out.println("weekdays item:"+courseBlock.get("weekdays").toString());
            if (inDay(day, courseBlock.get("weekdays").toString())) {
                int start_time = time_to_int(courseBlock.get("start_time").toString());
                int end_time = time_to_int(courseBlock.get("end_time").toString());
                TimeBlock item = new TimeBlock(start_time, end_time);
                insert_timeblock(booked, item);
            }
        }

        return 0;
	}
	
	// assuming no overlap in times, unless one time block starts at the same time but ends later
	public void insert_timeblock(ArrayList<TimeBlock> list, TimeBlock item) {
            
		boolean notAdded = true;
                
		if (list.isEmpty()) {
			list.add(item);
                        notAdded = false;                   
			return;
		}
		
		for (int i = 0; i < list.size(); i++) {
			if (item.end_time < list.get(i).start_time) {
				list.add(i, item);
                                notAdded = false;
				return;
			} else if (item.start_time == list.get(i).start_time) {
				if (item.end_time == list.get(i).end_time) {
					return;
				} else {
					list.remove(i);
					list.add(i, item);
                                        notAdded = false;
					return;
				}
			}
		}
                if(notAdded){
                        list.add(item);
                }
	}
	
	public int time_to_int (String time) {
		// change "XX:XX" -> minutes
		return 60*Integer.parseInt(time.substring(0, 2)) + Integer.parseInt(time.substring(3,5));
	}
	
	public static boolean inDay (String day, String lofd) {
		// determine if day is in lofd
                boolean found = false;
		for(int n = 0; n < (lofd.length()-day.length()+1);n++){
                   
                    if(day.equals(lofd.substring(n, n+day.length()))){
                        found = true;
                        
                    }
                    
                    if(n>0 && day.equals("T") && lofd.substring(n-1,n+day.length()).equals("Th")){
                        found = false;
                    }
                    
                    
                }
                return found;
	}
	
	public static void print_time_array (ArrayList<TimeBlock> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println("(" + list.get(i).start_time + ", " + list.get(i).end_time + ")");
		}
	}
	
	public static void main(String[] args) {
                /*        THIS SHIT WORKS NOW
		ArrayList<TimeBlock> blocks = new ArrayList<TimeBlock>();
		insert_timeblock(blocks, new TimeBlock(0,60));
		insert_timeblock(blocks, new TimeBlock(65, 80));
		insert_timeblock(blocks, new TimeBlock(0, 60));
		insert_timeblock(blocks, new TimeBlock(65, 85));
		insert_timeblock(blocks, new TimeBlock(61, 63));
		System.out.println(blocks.size());
		print_time_array(blocks);   */
            //System.out.println("time to int:" + time_to_int("13:30"));
            
            Scanner keyboard = new Scanner(System.in);
            String day;
            String lofd;
            for (int t = 0; t < 3;t++){
                day = keyboard.next();
                lofd = keyboard.next();
                System.out.println("Result:"+inDay(day,lofd));
                
            }
                
                	
}
}
