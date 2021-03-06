package world.myblooddy.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import world.myblooddy.R;

/**
 * Created by Jacob Samro on 09-Apr-16.
 */
public class BlooddiesAdapter extends BaseAdapter {

    Context context;
    Activity activity;
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> blood_group = new ArrayList<String>();
    ArrayList<String> last_given = new ArrayList<String>();

    private static LayoutInflater inflater = null;

    public BlooddiesAdapter(Context context,
                            Activity activity,
                            ArrayList<String> names,
                            ArrayList<String> blood_group,
                            ArrayList<String> last_given) {

        this.context = context;
        this.names = names;
        this.blood_group = blood_group;
        this.last_given = last_given;
        this.activity = activity;

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

                vi = inflater.inflate(R.layout.item_bloodies, null);

        }


        final TextView tv_name = (TextView) vi.findViewById(R.id.blooddy_name);
        final TextView tv_bloodgroup = (TextView) vi.findViewById(R.id.blood_group);
        final TextView tv_lastgiven = (TextView) vi.findViewById(R.id.last_given);



        tv_name.setText(names.get(position));
        tv_name.setTag(position);


        tv_bloodgroup.setText(blood_group.get(position));

        tv_lastgiven.setText(last_given.get(position));



        return vi;
    }

    public LayoutInflater getLayoutInflater() {
        return getLayoutInflater();
    }


}
