package hwcdhackwaterloo;

import static hwcdhackwaterloo.UWAPI.getJSONData;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import java.io.*;

public class RoomDataCrunch {

	public static boolean validRoomCheck(String building, int room_num) {
		
		// to track current progress
		System.out.println("Checking room " + room_num + " in " + building);
		
		// get data for room
		String json1 = getJSONData("/buildings/" + building + "/" + room_num + "/courses");
        JSONObject obj = (JSONObject) JSONValue.parse(json1);
        JSONObject meta = (JSONObject) obj.get("meta");
        
        // check the returned data
        if(meta.get("message").toString().equals("No data returned")){
            return false;
        }
		return true;
	}
	
	public static void main(String[] args) {
		
		final int num_buildings = 24;
		String[] bldgsWithRooms = {"AL","B1","B2","BMH","C2","CPH","DC","DWE","E2","E3","E6","EV1","EV2","EV3",
								   "ESC","HH","M3","MC" //17
								   ,"ML","OPT","PAS","PHY","QNC","RCH"};
		
		try { // attempt to write to a text file
			BufferedWriter out = new BufferedWriter(new FileWriter("PAS_data.txt"));
			int i = 20;
			out.write(bldgsWithRooms[i]+":");
			
			// Iterate possible rooms
	        for (int j = 0; j <= 4500; j++) {
				if (validRoomCheck(bldgsWithRooms[i], j)) {
					out.write(j+",");
				}
	        }
	        
	        out.close();
	        } catch (IOException e) {}
	}
}
