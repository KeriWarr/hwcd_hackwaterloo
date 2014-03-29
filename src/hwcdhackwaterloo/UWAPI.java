
package hwcdhackwaterloo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * Retrieves information from UW API.
 * @author AnthonyCalandra
 */
public class UWAPI {
    
    private static String url = "https://api.uwaterloo.ca/v2{data}.json?key=d2c261c316afdccfac2eb9cbd1398d03";
    
    /**
     * Parses the specified data from UW API.
     * 
     * @param data The requested endpoint.
     * @param keys Block keys to extract.
     * @return The value for the last given key in keys.
     */
    public static String get(String data, String[] keys) {
        String json = getJSONData(data);
        if (json.isEmpty()) {
            return null;
        }
        
        JSONObject obj = (JSONObject) JSONValue.parse(json);
        String retval = null;
        for (int keyIndex = 0; keyIndex < keys.length - 1; keyIndex++) {
            try {
                obj = (JSONObject) obj.get(keys[keyIndex]);
            } catch (ClassCastException cce) {
                obj = null;
                break;
            }
        }
        
        String key = keys[keys.length - 1];
        if (obj != null && obj.containsKey(key)) {
            retval = obj.get(key).toString();
        }

        return retval;
    }
    
    /**
     *
     * @param data
     * @param keys
     * @return
     */
    public static ArrayList<JSONPair> getPairs(String data, String[] keys) {
        String json = getJSONData(data);
        if (json.isEmpty()) {
            return null;
        }
        
        JSONObject obj = (JSONObject) JSONValue.parse(json);
        obj.isEmpty();
        for (int keyIndex = 0; keyIndex < keys.length; keyIndex++) {
            try {
                obj = (JSONObject) obj.get(keys[keyIndex]);
            } catch (ClassCastException cce) {
                obj = null;
                break;
            }
        }
        
        ArrayList<JSONPair> pairs = null;
        if (obj != null && !obj.isEmpty()) {
            pairs = new ArrayList<>();
            for (Object key : obj.keySet()) {
                Object val = obj.get((String) key);
                if (val == null) {
                    pairs.add(new JSONPair((String) key, null));
                } else {
                    pairs.add(new JSONPair((String) key, val.toString()));
                }
            }
        }
        
        return pairs;
    }
    
    /**
     * Retrieves the endpoint from data.
     * 
     * @param data Endpoint.
     * @return JSON formatted string.
     */
    public static String getJSONData(String data) {
    	String newURL = url.replace("{data}", data);
    	System.out.println(newURL);
        String json = "";
        try {
            URL apiRequest = new URL(newURL);
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(apiRequest.openStream()));
            String line;
            while ((line = input.readLine()) != null) {
                json += line.trim();
            }
            
            input.close();
        } catch (MalformedURLException ex) {
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            System.err.println(ex.getMessage()) ;
        }
        //System.out.println(json);
        return json;
    }
    
    public static class JSONPair {
        
        public String key;
        public String value;
        
        public JSONPair(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
