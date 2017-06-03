package com.hamilton.fragment;

/**
 * Created by HP on 31-05-2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
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

public class Tab3 extends Fragment {
    @BindView(R.id.lblTab1)
    TypefacedTextView lblTab1;
    @BindView(R.id.lblSupervisorNumberTitle)
    TypefacedTextView lblSupervisorNumberTitle;
    @BindView(R.id.lblSupervisorNumber)
    TypefacedTextView lblSupervisorNumber;
    @BindView(R.id.lblSupervisorBuildingPhotoTitle)
    TypefacedTextView lblSupervisorBuildingPhotoTitle;
    @BindView(R.id.rvList)
    RecyclerView rvList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3, container, false);
        ButterKnife.bind(this, view);
        setData();
        lblSupervisorNumber.setText("+91 9660902992");
        return view;
    }

    private void setData() {

        lblTab1.setText("");
        if (MyApplication.getApplication().getUser() != null && MyApplication.getApplication().getUser().getResult().getData() != null) {
            List<User.Result.Data.LandInformationDetail> buildingInformationDetails = MyApplication.getApplication().getUser().getResult()
                    .getData().getBuildingUpdates();

            for (int i = 0; buildingInformationDetails != null && i < buildingInformationDetails.size(); i++) {
                if (!TextUtils.isEmpty(buildingInformationDetails.get(i).getUrl()))
                    Utils.addBulletStyle(lblTab1, buildingInformationDetails.get(i).getTitle(), buildingInformationDetails.get(i).getUrl());
            }


            if (!TextUtils.isEmpty(MyApplication.getApplication().getUser().getResult().getData().getSupervisorPhone())) {
                lblSupervisorNumber.setText(MyApplication.getApplication().getUser().getResult()
                        .getData().getSupervisorPhone());
                lblSupervisorNumberTitle.setVisibility(View.VISIBLE);
                lblSupervisorNumber.setVisibility(View.VISIBLE);
            } else {
                lblSupervisorNumberTitle.setVisibility(View.GONE);
                lblSupervisorNumber.setVisibility(View.GONE);
            }


            // TODO: 04-06-2017 Image Loading in stage progress photo


        }
    }
}