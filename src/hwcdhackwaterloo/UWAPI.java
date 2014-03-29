
package hwcdhackwaterloo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * Retrieves information from UW API.
 * @author AnthonyCalandra
 */
public class UWAPI {
    
    private static String url = "https://api.uwaterloo.ca/v2{data}.json?key=020abd1d65907c70723816f7b390ab74";
    
    /**
     * Parses the specified data from UW API.
     * 
     * @param data The requested endpoint.
     * @param keys Block keys to extract.
     * @return The value for the last given key in keys.
     */
    public static String get(String data, String[] keys) {
        String json = getJSONData(data);
        System.out.println(json);
        if (json.isEmpty()) {
            return null;
        }
        
        JSONObject obj = (JSONObject) JSONValue.parse(json);
        String retval = null;
        for (int keyIndex = 0; keyIndex < keys.length - 1; keyIndex++) {
            obj = (JSONObject) obj.get(keys[keyIndex]);
            if (obj.isEmpty()) {
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
     * Retrieves the endpoint from data.
     * 
     * @param data Endpoint.
     * @return JSON formatted string.
     */
    private static String getJSONData(String data) {
        url = url.replace("{data}", data);
        String json = "";
        try {
            URL apiRequest = new URL(url);
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
            System.err.println(ex.getMessage());
        }
        
        return json;
    }
}
