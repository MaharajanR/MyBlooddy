package world.myblooddy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import world.myblooddy.Fragments.BuddiesFragment;
import world.myblooddy.Fragments.IncomingFragment;
import world.myblooddy.Fragments.SentFragment;
import world.myblooddy.R;

public class MainActivity extends AppCompatActivity {



    private static Activity activity = null;
    static Context context;



    static int lastVisibleItemUID = 0;
    static int _totalItemCount = 0;

    IncomingFragment incomingFragment = null;
    BuddiesFragment buddiesFragment = null;
    ViewPagerAdapter adapter;



    public static Boolean ReceivedViewCreated = false;
    public static Boolean BuddiesViewCreated = false;
    public static Boolean SentViewCreated = false;

    static FragmentManager fragmentManager;


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.ic_incoming,
            R.drawable.ic_buddies ,
            R.drawable.ic_outgoing
    };

    public MainActivity(){



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        context = getApplicationContext();



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setCollapsible(true);

        getSupportActionBar().setDisplayShowTitleEnabled(false);


        activity = MainActivity.this;

        fragmentManager = getSupportFragmentManager();

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void setupTabIcons() {


        TextView favTab = (TextView) LayoutInflater.from(this).inflate(R.layout.tab_title, null);
        favTab.setText(" Incoming"); //tab label txt
        favTab.setCompoundDrawablesWithIntrinsicBounds(tabIcons[0], 0, 0, 0);
        tabLayout.getTabAt(0).setCustomView(favTab);


        TextView feedTab = (TextView) LayoutInflater.from(this).inflate(R.layout.tab_title, null);
        feedTab.setText(" My Blooddies"); //tab label txt
        feedTab.setCompoundDrawablesWithIntrinsicBounds(tabIcons[1], 0, 0, 0);
        tabLayout.getTabAt(1).setCustomView(feedTab);

        TextView trendTab = (TextView) LayoutInflater.from(this).inflate(R.layout.tab_title, null);
        trendTab.setText(" Sent"); //tab label txt
        trendTab.setCompoundDrawablesWithIntrinsicBounds(tabIcons[2], 0, 0, 0);
        tabLayout.getTabAt(2).setCustomView(trendTab);


    }

    private void setupViewPager(final ViewPager viewPager) {

        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        incomingFragment = new IncomingFragment();
        buddiesFragment = new BuddiesFragment(context,getParent(),fragmentManager);

        adapter.addFrag(incomingFragment, "Incoming");
        adapter.addFrag(buddiesFragment, "My Blooddies");
        adapter.addFrag(new SentFragment(), "Sent");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setCurrentItem(1,true);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if(position == 0){
                    if(ReceivedViewCreated){
                        try{


                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                }else if(position == 1){
                    if(BuddiesViewCreated){
                        try {
                            //newsItemAdapter.notifyDataSetChanged();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }else if(position == 2){
                    if(SentViewCreated){
                        try {
                            //newsItemAdapter.notifyDataSetChanged();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }



            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }



    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {

            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);

        }


        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);

        }

        @Override
        public CharSequence getPageTitle(int position) {


            return mFragmentTitleList.get(position);
        }

    }


    @Override
    public void onResume()
    {

        super.onResume();

    }

    @Override
    protected void onStart() {
        SharedPreferences sp = getSharedPreferences("APP_INFO", 0);
        SharedPreferences.Editor ed = sp.edit();
        ed.putBoolean("active", true);
        ed.commit();
        super.onStart();
    }

    @Override
    protected void onStop() {
        SharedPreferences sp = getSharedPreferences("APP_INFO", 0);
        SharedPreferences.Editor ed = sp.edit();
        ed.putBoolean("active", false);
        ed.commit();
        super.onStop();
    }




    void Log(String mes){
        Log.e("ARO LOGGER : ", mes);
    }

    private static Intent getIntent(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        return intent;
    }
}