package world.myblooddy.DataStore;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Jacob Samro on 10-Mar-17.
 */

public class Requests {
    public static JSONArray sent = new JSONArray();
    public static JSONArray received = new JSONArray();

    public static boolean sendRequest(JSONObject req){
        return true;
    }

    public static boolean getRequests(){
        return true;
    }
}
