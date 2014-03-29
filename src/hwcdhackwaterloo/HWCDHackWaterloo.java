
package hwcdhackwaterloo;

import static hwcdhackwaterloo.UWAPI.getJSONData;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class HWCDHackWaterloo {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { 
        String json = getJSONData("/buildings/list");
        JSONObject obj = (JSONObject) JSONValue.parse(json);
        JSONArray courses = (JSONArray) obj.get("data");
        HashMap<String, double[]> coords = new HashMap<>();
        for (Object course : courses) {
            JSONObject courseBlock = (JSONObject) course;
            double lat = Double.parseDouble(courseBlock.get("latitude").toString());
            double longitude = Double.parseDouble(courseBlock.get("longitude").toString());
            double[] someshit = {lat, longitude};
            coords.put((String) courseBlock.get("building_code"), someshit);
        }
        
        System.out.println(coords.get("AAC")[0]);
    }
}
