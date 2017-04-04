package world.myblooddy.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import world.myblooddy.R;

/**
 * Created by Jacob Samro on 04-Apr-17.
 */

public class IncomingFragment extends Fragment {

    static ListView listview;
    static RelativeLayout favoritesEmptyView;
    static boolean hasFavorites = false;

    public IncomingFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.incoming_list, container, false);

        return view;
    }




}
