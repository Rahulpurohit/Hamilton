package com.hamilton.fragment;

/**
 * Created by HP on 31-05-2017.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hamilton.R;
import com.hamilton.application.MyApplication;
import com.hamilton.view.TypefacedTextView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Tab5 extends Fragment {
    @BindView(R.id.lblTab1)
    TypefacedTextView lblTab1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab5, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setData();
    }

    private void setData() {
        lblTab1.setText("");
        if (MyApplication.getUserId() != -1) {
            lblTab1.setText(MyApplication.getApplication().getUser().getData().getFeedback());
        }
    }
}