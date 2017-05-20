package com.hamilton;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

import com.hamilton.fragment.MeFragment;
import com.hamilton.fragment.PropertiesFragment;
import com.hamilton.fragment.SearchFragment;
import com.hamilton.fragment.ShortlistFragment;
import com.hamilton.view.SwitchViewPager;
import com.hamilton.view.ntb.NavigationTabBar;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private NavigationTabBar navigationTabBar;
    private ArrayList<NavigationTabBar.Model> models;
    SwitchViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        viewPager = (SwitchViewPager) findViewById(R.id.home_pager);
        navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb);
        navigationTabBar.setIsTinted(false);
        viewPager.setAdapter(new CustomPagerAdapter(getSupportFragmentManager()));
        viewPager.setPagingEnabled(true);
        models = new ArrayList<>();

        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.search),
                        getResources().getColor(R.color.transparent))
                        .title(getResources().getString(R.string.str_search))
                        .selectedIcon(getResources().getDrawable(R.drawable.hover_temp))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.properties),
                        getResources().getColor(R.color.transparent))
                        .title(getResources().getString(R.string.str_properties))
                        .selectedIcon(getResources().getDrawable(R.drawable.tab_selected_property))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.shortlist),
                        getResources().getColor(R.color.transparent))
                        .title(getResources().getString(R.string.str_shortlist))
                        .selectedIcon(getResources().getDrawable(R.drawable.tab_selected_shortlist))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.me),
                        getResources().getColor(R.color.transparent))
                        .title(getResources().getString(R.string.str_me))
                        .selectedIcon(getResources().getDrawable(R.drawable.tab_selected_me))
                        .build()
        );

        navigationTabBar.setModels(models);

        viewPager.setOffscreenPageLimit(0);
        navigationTabBar.setViewPager(viewPager, 0);

    }

    private class CustomPagerAdapter extends FragmentPagerAdapter {

        public CustomPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:

                {
                    return new SearchFragment();
                }//CALENDAR
                case 1: {

//Assignment
                    return new PropertiesFragment();
                }
                case 2: {

                    Fragment ff = new ShortlistFragment();
                   /* Bundle bun = new Bundle();
                    ff.setArguments(bun);*/
                    return ff;

                }
                case 3: {
                    return new MeFragment();
                }
                default:
                    return new SearchFragment();
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }


}
