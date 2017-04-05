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
import world.myblooddy.Adapters.SentRequestsAdapter;
import world.myblooddy.R;

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


        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> sent_blood_group = new ArrayList<String>();
        ArrayList<String> sent_time = new ArrayList<String>();

        names.add("Manooj");
        names.add("Femy");
        names.add("Ilango");
        names.add("Nagaraj");
        names.add("Praveen");
        names.add("Santhosh");

        sent_blood_group.add("A+");
        sent_blood_group.add("B+");
        sent_blood_group.add("AB+");
        sent_blood_group.add("A+");
        sent_blood_group.add("B+");
        sent_blood_group.add("AB+");

        sent_time.add("a minute ago");
        sent_time.add("a minute ago");
        sent_time.add("a minute ago");
        sent_time.add("a minute ago");
        sent_time.add("a minute ago");
        sent_time.add("a minute ago");

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

        return view;
    }

    public static Fragment getInstance(Context context, Activity activity){

        return new SentFragment(context,activity);

    }

}