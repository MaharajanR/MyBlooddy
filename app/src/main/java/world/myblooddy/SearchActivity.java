package world.myblooddy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import world.myblooddy.DataStore.BlooddiesAdapter;
import world.myblooddy.DataStore.SearchAdapter;

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


        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> blood_group = new ArrayList<String>();
        ArrayList<String> last_given = new ArrayList<String>();

        names.add("Kruthika");
        names.add("Shanmathi");
        names.add("Balaji");

        blood_group.add("A+");
        blood_group.add("A+");
        blood_group.add("O+");


        last_given.add("a day ago");
        last_given.add("6 months ago");
        last_given.add("not given yet");


        SearchAdapter searchAdapter = new SearchAdapter(getApplicationContext(),
                names,
                blood_group,
                last_given);

        listview.setAdapter(searchAdapter);

    }

}
