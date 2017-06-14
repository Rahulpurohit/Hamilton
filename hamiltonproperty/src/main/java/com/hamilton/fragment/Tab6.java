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

import com.hamilton.LoginActivity;
import com.hamilton.R;
import com.hamilton.application.MyApplication;
import com.hamilton.utility.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


//Our class extending fragment
public class Tab6 extends Fragment {

    @BindView(R.id.lblAccountEmail)
    public TextView lblAccountEmail;

    @BindView(R.id.llAccountSettings)
    View llAccountSettings;

    @BindView(R.id.llAccountSettingsLogin)
    View llAccountSettingsLogin;

    @OnClick(R.id.lblAccountLogout)
    public void LogoutButtonClicked() {
        Utils.performLogout(getActivity());
    }

    @OnClick(R.id.btnLoginUser)
    public void LoginButtonClicked() {
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab6, container, false);
        ButterKnife.bind(this, rootView);
        setData();


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        setData();
    }

    private void setData() {
        if (MyApplication.getUserId() != -1) {
            lblAccountEmail.setText(MyApplication.getApplication().getUser().getData().getUserEmail());
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}