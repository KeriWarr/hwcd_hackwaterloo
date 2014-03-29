
package hwcdhackwaterloo;

public class HWCDHackWaterloo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { 
        String[] keys = new String[] {"data", "temperature_current_c"};
        String ret = UWAPI.get("/weather/current", keys);
        System.out.println(ret);
        
        //This comment added for your viewing pleasure by Daniel
        //PS: I'm in.
        //PPS: Eclipse is a pain in the bum
    }
}
