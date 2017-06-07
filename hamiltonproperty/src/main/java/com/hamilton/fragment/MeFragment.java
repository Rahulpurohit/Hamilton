package com.hamilton.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hamilton.LoginActivity;
import com.hamilton.R;
import com.hamilton.adapter.Pager;
import com.hamilton.application.MyApplication;


public class MeFragment extends Fragment implements TabLayout.OnTabSelectedListener {
    private static final String TAG = "tab";
    boolean isVisibleToUser = false;
    //This is our tablayout
    private TabLayout tabLayout;
    //This is our viewPager
    private ViewPager viewPager;

    public MeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_me, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View rootView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);

        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setVisibility(View.GONE);
        //Initializing the tablayout

        tabLayout = (TabLayout) rootView.findViewById(R.id.tabLayout);


        //Adding the tabs using addTab() method

        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.str_account_setting)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.str_building_info)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.str_land_info)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.str_building_updates)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.str_customer_care)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.str_feedback)));


        tabLayout.setTabGravity(TabLayout.MODE_SCROLLABLE);
        tabLayout.setSmoothScrollingEnabled(true);

        //Initializing viewPager
        viewPager = (ViewPager) rootView.findViewById(R.id.pager);

        //Creating our pager adapter
        Pager adapter = new Pager(getChildFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(6);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (positionOffset == 0f && positionOffsetPixels == 0) {
                    tabLayout.setScrollPosition(position, 0f, true);
                }
                Log.d(TAG, "onPageScrolled() called with: position = [" + position + "], positionOffset = [" + positionOffset + "], positionOffsetPixels = [" + positionOffsetPixels + "]");
            }

            @Override
            public void onPageSelected(int position) {

                Log.d(TAG, "onPageSelected() called with: position = [" + position + "]");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(TAG, "onPageScrollStateChanged() called with: state = [" + state + "]");
            }
        });

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition(), true);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser && getActivity() != null) {
            if (MyApplication.getUserId() == -1 && getActivity() != null) {
                startActivity(new Intent(getActivity(), LoginActivity.class)
                        .putExtra("fromtab", 3));
                getActivity().finish();
            }
        }
    }
}
