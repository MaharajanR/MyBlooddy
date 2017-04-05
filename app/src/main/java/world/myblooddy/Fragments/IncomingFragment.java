package world.myblooddy.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import world.myblooddy.DataStore.AppConstants;
import world.myblooddy.Adapters.IncomingRequestsAdapter;
import world.myblooddy.R;

/**
 * Created by Jacob Samro on 04-Apr-17.
 */

public class IncomingFragment extends Fragment {

    ListView listview;
    SwipeRefreshLayout swipeHolderHandler;

    Context context;
    Activity activity;




    public IncomingFragment(){
    }

    public IncomingFragment(Context context, Activity activity){
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


        View view = inflater.inflate(R.layout.list_blooddies_incoming, container, false);

        listview = (ListView) view.findViewById(R.id.received_list);

        swipeHolderHandler = (SwipeRefreshLayout) view.findViewById(R.id.swipeHolder);


        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> blood_group = new ArrayList<String>();
        ArrayList<String> received_time = new ArrayList<String>();

        names.add("Arun Kumar");

        blood_group.add("A+");

        received_time.add("a minute ago");


        IncomingRequestsAdapter incomingRequestsAdapter = new IncomingRequestsAdapter(context,
                activity,
                names,
                blood_group,
                received_time);

        listview.setAdapter(incomingRequestsAdapter);

        AppConstants.IncomingViewCreated = true;


        if(names.size() > 0){
            swipeHolderHandler.setVisibility(View.VISIBLE);
            (view.findViewById(R.id.empty_view)).setVisibility(View.GONE);
        }



        return view;
    }

    public static Fragment getInstance(Context context, Activity activity){

        return new IncomingFragment(context,activity);

    }



}
