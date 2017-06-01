package com.hamilton.fragment;

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

public class Tab2 extends Fragment {

    @BindView(R.id.lblTab1)
    TypefacedTextView lblTab1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2, container, false);
        ButterKnife.bind(this, view);
        setData();

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


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
