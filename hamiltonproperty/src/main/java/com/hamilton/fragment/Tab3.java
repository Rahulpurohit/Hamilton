package com.hamilton.fragment;

/**
 * Created by HP on 31-05-2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hamilton.R;
import com.hamilton.adapter.StageProgressPhotosAdapter;
import com.hamilton.application.MyApplication;
import com.hamilton.modal.User;
import com.hamilton.utility.Utils;
import com.hamilton.view.TypefacedTextView;

import java.util.ArrayList;
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

    StageProgressPhotosAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3, container, false);
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
        if (MyApplication.getUserId() != -1) {
            List<User.Data.LandInformationDetail> buildingInformationDetails = MyApplication.getApplication().getUser()
                    .getData().getBuildingUpdates();

            for (int i = 0; buildingInformationDetails != null && i < buildingInformationDetails.size(); i++) {
                if (!TextUtils.isEmpty(buildingInformationDetails.get(i).getUrl()))
                    Utils.addBulletStyle(lblTab1, buildingInformationDetails.get(i).getTitle(), buildingInformationDetails.get(i).getUrl());
            }


            if (!TextUtils.isEmpty(MyApplication.getApplication().getUser().getData().getSupervisorPhone())) {
                lblSupervisorNumber.setText(MyApplication.getApplication().getUser()
                        .getData().getSupervisorPhone());
                lblSupervisorNumberTitle.setVisibility(View.VISIBLE);
                lblSupervisorNumber.setVisibility(View.VISIBLE);
            } else {
                lblSupervisorNumberTitle.setVisibility(View.GONE);
                lblSupervisorNumber.setVisibility(View.GONE);
            }

            mAdapter = new StageProgressPhotosAdapter();
            //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);


            rvList.setLayoutManager(mLayoutManager);
            rvList.setHasFixedSize(true);
            rvList.setItemAnimator(new DefaultItemAnimator());
            rvList.setAdapter(mAdapter);

            if (MyApplication.getApplication().getUser() != null &&
                    MyApplication.getApplication().getUser().getData() != null
                    && MyApplication.getApplication().getUser().getData().getStageProgressPhotos() != null) {
                lblSupervisorBuildingPhotoTitle.setVisibility(View.VISIBLE);
                mAdapter.data = (ArrayList<String>) MyApplication.getApplication().getUser().getData().getStageProgressPhotos();
                mAdapter.notifyDataSetChanged();

            } else {
                lblSupervisorBuildingPhotoTitle.setVisibility(View.GONE);
            }

            // TODO: 04-06-2017 Image Loading in stage progress photo


        }
    }
}