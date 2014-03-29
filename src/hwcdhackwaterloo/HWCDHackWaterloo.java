
package hwcdhackwaterloo;

import hwcdhackwaterloo.UWAPI.JSONPair;
import java.util.ArrayList;

public class HWCDHackWaterloo {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] keys = new String[] {"data"};
        ArrayList<JSONPair> pairs = UWAPI.getPairs("/buildings/list", keys);
        for (JSONPair pair : pairs) {
            System.out.println("Key=" + pair.key + " Val=" + pair.value);
        }
    }
}
