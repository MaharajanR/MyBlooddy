package world.myblooddy.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.FragmentManager;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import world.myblooddy.DataStore.Requests;
import world.myblooddy.R;

/**
 * Created by Jacob Samro on 09-Apr-16.
 */
public class IncomingRequestsAdapter extends BaseAdapter {

    Context context;
    Activity activity;
    ArrayList<String> received_time = new ArrayList<String>();
    ArrayList<String> blood_group = new ArrayList<String>();
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> id = new ArrayList<String>();

    FragmentManager fragmentManager;

    private static LayoutInflater inflater = null;

    public IncomingRequestsAdapter(Context context,
                                   Activity activity,
                                   ArrayList<String> names,
                                   ArrayList<String> blood_group,
                                   ArrayList<String> received_time,
                                   ArrayList<String> id) {

        this.context = context;
        this.names = names;
        this.blood_group = blood_group;
        this.received_time = received_time;
        this.activity = activity;
        this.id = id;

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
        activity.startActivity(Intent.createChooser(i, "Share via"));
    }


    public void startREWSIntent(Intent i){
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(i);
    }

    public void moveFragment(){

    }



    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub


        Cursor c;
        View vi = convertView;

        if (vi == null) {

                vi = inflater.inflate(R.layout.item_bloodies_received, null);

        }


        final TextView tv_name = (TextView) vi.findViewById(R.id.blooddy_name);
        final TextView tv_bloodgroup = (TextView) vi.findViewById(R.id.received_blood_group);
        final TextView tv_received_time = (TextView) vi.findViewById(R.id.request_received_time);

        try {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            Date dt = formatter.parse(received_time.get(position));

            CharSequence  output = DateUtils.getRelativeTimeSpanString (dt.getTime());

            tv_received_time.setText(blood_group.get(position));

            tv_received_time.setText(output.toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        final TextView tv_blood_req_accept = (TextView) vi.findViewById(R.id.blood_req_accept);

        tv_blood_req_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context,"Request Accepted, Contact the person via "  + id.get(position),Toast.LENGTH_LONG).show();

                tv_blood_req_accept.setText("accepted");

                try {





                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        tv_name.setText(names.get(position));
        tv_name.setTag(position);

        tv_bloodgroup.setText(blood_group.get(position));



        return vi;
    }

    public LayoutInflater getLayoutInflater() {
        return getLayoutInflater();
    }


}
