package com.hamilton.fragment;

/**
 * Created by HP on 31-05-2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hamilton.R;
import com.hamilton.application.MyApplication;
import com.hamilton.modal.User;
import com.hamilton.utility.Utils;
import com.hamilton.view.TypefacedTextView;

import butterknife.BindView;
import butterknife.ButterKnife;


//Our class extending fragment
public class Tab4 extends Fragment {
    @BindView(R.id.lblTab1)
    TypefacedTextView lblTab1;
    @BindView(R.id.lblSupervisorNameTitle)
    TypefacedTextView lblSupervisorNameTitle;
    @BindView(R.id.lblSupervisorName)
    TypefacedTextView lblSupervisorName;
    @BindView(R.id.lblSupervisorEmailTitle)
    TypefacedTextView lblSupervisorEmailTitle;
    @BindView(R.id.lblSupervisorEmail)
    TypefacedTextView lblSupervisorEmail;
    @BindView(R.id.lblSupervisorPhoneTitle)
    TypefacedTextView lblSupervisorPhoneTitle;
    @BindView(R.id.lblSupervisorPhone)
    TypefacedTextView lblSupervisorPhone;

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab4, container, false);
        ButterKnife.bind(this, view);
        setData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setData();
    }

    private void setData() {

        lblTab1.setText("");

        User user = MyApplication.getApplication().getUser();
        if (MyApplication.getUserId() != -1) {

            if (!TextUtils.isEmpty(user.getData().getContractUpdates()))
                Utils.addBulletStyle(lblTab1, getString(R.string.str_contract_updates), user.getData().getContractUpdates());

            if (!TextUtils.isEmpty(user.getData().getPermitUpdatesCare()))
                Utils.addBulletStyle(lblTab1, getString(R.string.str_permit_update_care), user.getData().getPermitUpdatesCare());

            lblSupervisorName.setText(user.getData().getSupervisorName());
            lblSupervisorEmail.setText(user.getData().getSupervisorEmail());
            lblSupervisorPhone.setText(user.getData().getSupervisorPhone());
        }


    }
}