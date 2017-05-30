package com.hamilton.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hamilton.R;


public class SearchFragment extends Fragment {
    public SearchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        RadioGroup topTabBar = (RadioGroup) rootView.findViewById(R.id.radio_tab_group);

        final RadioButton buttonOne = (RadioButton) topTabBar.getChildAt(0);
        final RadioButton buttonTwo = (RadioButton) topTabBar.getChildAt(1);

        buttonOne.setText(R.string.str_buy_cap);
        buttonTwo.setText(R.string.str_rent_cap);
        buttonOne.setChecked(true);

        topTabBar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedId = group.getCheckedRadioButtonId();
                RadioButton radioSexButton = (RadioButton) group.findViewById(selectedId);
                if (radioSexButton.getText().toString().equalsIgnoreCase(getString(R.string.str_buy_cap))) {
                    buttonOne.setChecked(true);
                } else {
                    buttonTwo.setChecked(true);
                }

            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
