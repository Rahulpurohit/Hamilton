package com.hamilton.fragment;

/**
 * Created by HP on 31-05-2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hamilton.HomeActivity;
import com.hamilton.LoginActivity;
import com.hamilton.R;
import com.hamilton.application.MyApplication;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


//Our class extending fragment
public class Tab6 extends Fragment {
    @BindView(R.id.lblAccountEmail)
    TextView lblAccountEmail;

    @BindView(R.id.llAccountSettings)
    View llAccountSettings;

    @BindView(R.id.llAccountSettingsLogin)
    View llAccountSettingsLogin;

    @OnClick(R.id.lblAccountLogout)
    public void LogoutButtonClicked() {
        MyApplication.getApplication().setUser(null);
        startActivity(new Intent(getActivity(), HomeActivity.class));
    }

    @OnClick(R.id.btnLoginUser)
    public void LoginButtonClicked() {
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        View rootView = inflater.inflate(R.layout.fragment_me, container, false);

        ButterKnife.bind(rootView);


        if (MyApplication.getApplication().getUser() != null) {
            lblAccountEmail.setText(MyApplication.getApplication().getUser().getResult().getData().getUserEmail());
            llAccountSettings.setVisibility(View.VISIBLE);
            llAccountSettingsLogin.setVisibility(View.GONE);
        } else {
            llAccountSettings.setVisibility(View.GONE);
            llAccountSettingsLogin.setVisibility(View.VISIBLE);
        }


        return rootView;
    }
}