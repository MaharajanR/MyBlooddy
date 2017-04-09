package world.myblooddy.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import world.myblooddy.DataStore.AppConstants;
import world.myblooddy.DataStore.Requests;
import world.myblooddy.R;

import static world.myblooddy.DataStore.AppConstants.SERVER;

/**
 * Created by Jacob Samro on 09-Apr-16.
 */
public class SearchAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> blood_group = new ArrayList<String>();
    ArrayList<String> last_given = new ArrayList<String>();
    ArrayList<String> mobile = new ArrayList<String>();


    private static LayoutInflater inflater = null;

    public SearchAdapter(Context context,
                         ArrayList<String> names,
                         ArrayList<String> blood_group,
                         ArrayList<String> last_given,
                         ArrayList<String> mobile) {

        this.context = context;
        this.names = names;
        this.blood_group = blood_group;
        this.last_given = last_given;
        this.mobile = mobile;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);



    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return names.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return names.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public void startIntent(Intent i){
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(i, "Share via"));
    }


    public void startREWSIntent(Intent i){
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    public void moveFragment(){

    }



    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub


        Cursor c;
        View vi = convertView;

        if (vi == null) {

                vi = inflater.inflate(R.layout.item_bloodies_search, null);

        }


        final TextView tv_name = (TextView) vi.findViewById(R.id.blooddy_name);
        final TextView tv_bloodgroup = (TextView) vi.findViewById(R.id.blood_group);
        final TextView tv_lastgiven = (TextView) vi.findViewById(R.id.last_given);


        final TextView tv_blood_req_send = (TextView) vi.findViewById(R.id.blood_req_send);


        tv_blood_req_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                RequestParams params = new RequestParams();

                params.add("from", AppConstants.id);
                params.add("to",tv_blood_req_send.getTag().toString());
                params.add("group",blood_group.get(position));

                Toast.makeText(context,"Sending Please wait..",Toast.LENGTH_LONG).show();

                AsyncHttpClient client = new AsyncHttpClient();

                client.post(SERVER + "/blood/request", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            if(new String(responseBody).matches("1")){
                                Toast.makeText(context,"Request Sent",Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(context,"Unable to send request",Toast.LENGTH_LONG).show();
                            }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                        Toast.makeText(context,"Unable to send request",Toast.LENGTH_LONG).show();
                    }
                });





            }
        });

        tv_name.setText(names.get(position));
        tv_name.setTag(position);


        tv_bloodgroup.setText(blood_group.get(position));

        tv_lastgiven.setText(last_given.get(position));
        tv_blood_req_send.setTag(mobile.get(position));


        return vi;
    }

    public LayoutInflater getLayoutInflater() {
        return getLayoutInflater();
    }


}
