package com.hamilton.fragment;

/**
 * Created by HP on 31-05-2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hamilton.R;
import com.hamilton.utility.Utils;
import com.hamilton.view.TypefacedTextView;

import java.util.ArrayList;
import java.util.List;

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

    private void setData() {
        List<String> arr = new ArrayList<>();
        arr.add("Pre Agreement");
        arr.add("Pre Agreement");

        lblTab1.setText("");
        for (int i = 0; i < arr.size(); i++) {
            Utils.addBulletStyle(lblTab1, arr.get(i));
        }

        lblSupervisorName.setText("Akshay Sharma");
        lblSupervisorEmail.setText("sharma.akshay5@gmail.com");
        lblSupervisorPhone.setText("+91-9660902992");
    }
}