package world.myblooddy.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import world.myblooddy.Adapters.BlooddiesAdapter;
import world.myblooddy.DataStore.AppConstants;
import world.myblooddy.Adapters.SentRequestsAdapter;
import world.myblooddy.R;

import static world.myblooddy.DataStore.AppConstants.SERVER;

/**
 * Created by Jacob Samro on 04-Apr-17.
 */

public class SentFragment extends Fragment {

    ListView listview;

    SwipeRefreshLayout swipeHolderHandler;

    Context context;
    Activity activity;

    public SentFragment(){
    }

    public SentFragment(Context context, Activity activity){
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.list_blooddies_sent, container, false);

        listview = (ListView) view.findViewById(R.id.sent_list);

        swipeHolderHandler = (SwipeRefreshLayout) view.findViewById(R.id.swipeHolder);


        final ArrayList<String> names = new ArrayList<String>();
        final ArrayList<String> sent_blood_group = new ArrayList<String>();
        final ArrayList<String> sent_time = new ArrayList<String>();

        AsyncHttpClient client = new AsyncHttpClient();

        RequestParams params = new RequestParams();
        params.add("id",AppConstants.id);

        client.post(SERVER + "/blood/requests/sent", params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                try {

                    JSONArray users = response;

                    for(int i = 0; i < response.length() ; i ++){

                        JSONObject user = users.getJSONObject(i);

                        Log.d("...",user.toString());

                        names.add(user.getString("name"));
                        sent_blood_group.add(user.getString("type"));
                        sent_time.add(user.getString("time"));

                        SentRequestsAdapter sentRequestsAdapter = new SentRequestsAdapter(context,
                                activity,
                                names,
                                sent_blood_group,
                                sent_time);


                        listview.setAdapter(sentRequestsAdapter);

                        AppConstants.SentViewCreated = true;


                        if(names.size() > 0){
                            swipeHolderHandler.setVisibility(View.VISIBLE);
                            (view.findViewById(R.id.empty_view)).setVisibility(View.GONE);
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





        return view;
    }

    public static Fragment getInstance(Context context, Activity activity){

        return new SentFragment(context,activity);

    }

}