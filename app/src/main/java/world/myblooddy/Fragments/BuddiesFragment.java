package world.myblooddy.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import world.myblooddy.DataStore.AppConstants;
import world.myblooddy.Adapters.BlooddiesAdapter;
import world.myblooddy.DataStore.Requests;
import world.myblooddy.R;

import static world.myblooddy.DataStore.AppConstants.SERVER;

/**
 * Created by Jacob Samro on 04-Apr-17.
 */

public class BuddiesFragment extends Fragment {


    BlooddiesAdapter blooddiesAdapter;
    SwipeRefreshLayout swipeHolderHandler;
    ListView listview;


    Context context;
    Activity activity;

    public BuddiesFragment(){}


    public BuddiesFragment(Context context, Activity activity){
        this.context = context;
        this.activity = activity;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("View", "Main View Created !");

        View view = inflater.inflate(R.layout.list_blooddies, container, false);

        listview = (ListView) view.findViewById(R.id.blooddies_list);

        swipeHolderHandler = (SwipeRefreshLayout) view.findViewById(R.id.swipeHolder);


        final ArrayList<String> names = new ArrayList<String>();
        final ArrayList<String> blood_group = new ArrayList<String>();
        final ArrayList<String> last_given = new ArrayList<String>();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(SERVER + "/contacts", new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                try {

                    JSONArray users = response;

                for(int i = 0; i < response.length() ; i ++){

                        JSONObject user = users.getJSONObject(i);

                    names.add(user.getString("name"));
                    blood_group.add(user.getString("group"));
                    last_given.add("few days ago");

                    blooddiesAdapter = new BlooddiesAdapter(context,
                            activity,
                            names,
                            blood_group,
                            last_given);

                    listview.setAdapter(blooddiesAdapter);


                    if(names.size() > 0){
                        swipeHolderHandler.setVisibility(View.VISIBLE);
                    }

                }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseBody, Throwable error) {

            }
        });




        AppConstants.BuddiesViewCreated = true;


        if(names.size() > 0){
            swipeHolderHandler.setVisibility(View.VISIBLE);
        }



        return view;

    }

    public static Fragment getInstance(Context context, Activity activity){

        return new BuddiesFragment(context,activity);

    }



}
