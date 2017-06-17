package com.hamilton.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hamilton.HomeActivity;
import com.hamilton.R;
import com.hamilton.SearchFilterActivity;
import com.hamilton.modal.PropertiesList;
import com.hamilton.utility.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SearchFragment extends Fragment {
    @BindView(R.id.imageView)
    public ImageView imageView;

    @BindView(R.id.txt_search)
    public EditText txtSearch;

    @OnClick(R.id.lblSearch)
    public void searchData() {
        startActivityForResult(new Intent(getActivity(), SearchFilterActivity.class)
                .putExtra(Constants.KEY_SEARCH_KEY, TextUtils.isEmpty(txtSearch.getText()) ? "" : txtSearch.getText().toString()), 8888);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, rootView);
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

        txtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    startActivityForResult(new Intent(getActivity(), SearchFilterActivity.class)
                            .putExtra(Constants.KEY_SEARCH_KEY, TextUtils.isEmpty(txtSearch.getText()) ? "" : txtSearch.getText().toString()), 8888);
                    return true;
                }
                return false;
            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            if (requestCode == 8888) {
                ((HomeActivity) getActivity()).callPropertyListFragment(data.<PropertiesList.Datum>getParcelableArrayListExtra("data"));
            }
        }

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

}
