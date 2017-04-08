package world.myblooddy.DataStore;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Jacob Samro on 10-Mar-17.
 */

public class Requests {

    static AsyncHttpClient client;
    static String SERVER = null;

    static  Activity activity;

    public Requests(Activity activity){
        client = new AsyncHttpClient();
        SERVER = "http://localhost:9090";
        this.activity = activity;
    }

    public static JSONArray sent = new JSONArray();
    public static JSONArray received = new JSONArray();

    public static boolean sendRequest(JSONObject req){
        return true;
    }

    public static boolean getRequests(){
        return true;
    }

    public static boolean acceptRequest(JSONObject req){
        return true;
    }

    public static void register(final JSONObject req){

        try {



        RequestParams params = new RequestParams();
        params.add("id",req.getString("id"));

        client.post(SERVER + "/user/register", params, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                if(response.toString().matches("1")){

                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {

            }

            @Override
            public void onRetry(int retryNo) {
                makeSnackbar("Retrying please wait...");
            }
        });

        }catch (Exception e){

        }

    }

    /* Helper Functions */

    public static void makeSnackbar(String message){
        Snackbar.make(activity.getCurrentFocus(),message,Snackbar.LENGTH_SHORT).show();
    }

    public static void makeSnackbar(View focus, String message){
        Snackbar.make(focus,message,Snackbar.LENGTH_SHORT).show();
    }
}
