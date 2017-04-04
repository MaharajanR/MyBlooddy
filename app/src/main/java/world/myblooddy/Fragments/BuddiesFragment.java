package world.myblooddy.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import world.myblooddy.DataStore.BlooddiesAdapter;
import world.myblooddy.R;

/**
 * Created by Jacob Samro on 04-Apr-17.
 */

public class BuddiesFragment extends Fragment {




    static Boolean isLoadMoreLoading = false;

    boolean hasFeeds = false;

    static SwipeRefreshLayout swipeNewsHolderHandler;
    static ListView listview;

    static RelativeLayout feedProcessView;

    Context context;
    Activity activity;
    FragmentManager fragmentManager;

    public BuddiesFragment(){

    }


    public BuddiesFragment(Context context, Activity activity,FragmentManager fragmentManager){
        this.context = context;
        this.activity = activity;
        this.fragmentManager = fragmentManager;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("View", "Main View Created !");

        View view = inflater.inflate(R.layout.blooddies_list, container, false);

        listview = (ListView) view.findViewById(R.id.blooddies_list);

        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> blood_group = new ArrayList<String>();
        ArrayList<String> last_given = new ArrayList<String>();

        names.add("Jinitha");
        names.add("Sariga");
        names.add("Maharanjan");

        blood_group.add("A+");
        blood_group.add("B+");
        blood_group.add("AB+");

        last_given.add("not given yet");
        last_given.add("6 months ago");
        last_given.add("a day ago");


        BlooddiesAdapter blooddiesAdapter = new BlooddiesAdapter(context,
                activity,
                fragmentManager,
                names,
                blood_group,
                last_given);

        listview.setAdapter(blooddiesAdapter);

        //BuddiesViewCreated = true;
        return view;

    }



}
