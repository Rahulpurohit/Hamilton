package com.hamilton.fragment;

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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Tab1 extends Fragment {

    @BindView(R.id.lblTab1)
    TypefacedTextView lblTab1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1, container, false);
        ButterKnife.bind(this, view);
        setData();

        return view;
    }

    private void setData() {
        lblTab1.setText("");
        if (MyApplication.getApplication().getUser() != null && MyApplication.getApplication().getUser().getResult().getData() != null) {
            List<User.Result.Data.LandInformationDetail> buildingInformationDetails = MyApplication.getApplication().getUser().getResult().getData().getBuildingInformationDetails();

            for (int i = 0; buildingInformationDetails != null && i < buildingInformationDetails.size(); i++) {
                if (!TextUtils.isEmpty(buildingInformationDetails.get(i).getUrl()))
                    Utils.addBulletStyle(lblTab1, buildingInformationDetails.get(i).getTitle(), buildingInformationDetails.get(i).getUrl());
            }

        }
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
