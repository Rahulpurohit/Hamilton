package com.hamilton.fragment;

/**
 * Created by HP on 31-05-2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
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
        List<String> arr = new ArrayList<>();
        arr.add("Pre Agreement");
        arr.add("Pre Agreement");
        arr.add("Pre Agreement");
        arr.add("Pre Agreement");
        arr.add("Pre Agreement");
        arr.add("Pre Agreement");
        arr.add("Pre Agreement");


        lblTab1.setText("");
        for (int i = 0; i < arr.size(); i++) {
            Utils.addBulletStyle(lblTab1, arr.get(i));
        }
    }
}