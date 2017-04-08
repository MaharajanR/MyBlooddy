package world.myblooddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
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
import world.myblooddy.Adapters.SearchAdapter;

import static world.myblooddy.DataStore.AppConstants.SERVER;

public class SearchActivity extends AppCompatActivity {

    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setVisibility(View.GONE);

        listview = (ListView) findViewById(R.id.listview);

        Intent intent = getIntent();

        final String group = intent.getStringExtra("query");


        Log.d("...", group);


        final ArrayList<String> names = new ArrayList<String>();
        final ArrayList<String> blood_group = new ArrayList<String>();
        final ArrayList<String> last_given = new ArrayList<String>();

        AsyncHttpClient client = new AsyncHttpClient();

        RequestParams params = new RequestParams();
        params.add("group",group);
        client.post(SERVER + "/user/search", params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                try {

                    JSONArray users = response;

                    for(int i = 0; i < response.length() ; i ++){

                        JSONObject user = users.getJSONObject(i);

                        names.add(user.getString("name"));
                        Log.d("...",user.getString("name"));
                        blood_group.add(user.getString("group"));
                        last_given.add("few days ago");

                        SearchAdapter searchAdapter = new SearchAdapter(getApplicationContext(),
                                names,
                                blood_group,
                                last_given);

                        listview.setAdapter(searchAdapter);


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseBody, Throwable error) {

            }
        });






    }

}
