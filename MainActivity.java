package com.emapel.seeyou.seeyou;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.emapel.seeyou.seeyou.classes.User;
import com.emapel.seeyou.seeyou.otherviews.HomeFragment;
import com.emapel.seeyou.seeyou.otherviews.PageAdapter;
import com.emapel.seeyou.seeyou.otherviews.SeeFragment;
import com.emapel.seeyou.seeyou.otherviews.SettingsFragment;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener, SeeFragment.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener {
    private User user = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout tabLayout = findViewById(R.id.tabs);

        user.setName("OlaCoecoe");

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_place);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_remove_red_eye_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_settings_black_24dp);

        final ViewPager viewPager = findViewById(R.id.viewpager);
        final PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void userPostion(LatLng pos){
        this.user.setActual_location(pos);
    }
}
