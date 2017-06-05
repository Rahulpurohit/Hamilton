package com.hamilton.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hamilton.R;
import com.hamilton.adapter.PropertyAdapter;
import com.hamilton.application.MyApplication;
import com.hamilton.modal.PropertiesList;
import com.hamilton.modal.ShortListedProperties;
import com.hamilton.modal.error.BaseError;
import com.hamilton.utility.Constants;
import com.hamilton.utility.Utils;
import com.hamilton.view.TypefacedTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ShortlistFragment extends Fragment {
    public ShortlistFragment() {
    }

    @BindView(R.id.txt_header)
    TextView txtHeader;
    @BindView(R.id.txt_total)
    TypefacedTextView txtTotal;
    @BindView(R.id.txt_sort)
    TypefacedTextView txtSort;
    @BindView(R.id.recycler_property)
    RecyclerView recyclerProperty;
    @BindView(R.id.txt_login)
    TextView txtLogin;
    PropertyAdapter mAdapter;
    private Dialog mDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_properties, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mAdapter = new PropertyAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerProperty.setLayoutManager(mLayoutManager);
        recyclerProperty.setItemAnimator(new DefaultItemAnimator());
        recyclerProperty.setAdapter(mAdapter);
        txtHeader.setText(R.string.str_shortlist);
        txtLogin.setVisibility(MyApplication.getUserId() == -1 ? View.VISIBLE : View.GONE);
        recyclerProperty.setVisibility(MyApplication.getUserId() == -1 ? View.GONE : View.VISIBLE);
        txtTotal.setVisibility(MyApplication.getUserId() == -1 ? View.GONE : View.VISIBLE);
        txtSort.setVisibility(MyApplication.getUserId() == -1 ? View.GONE : View.VISIBLE);
        if (MyApplication.getUserId() != -1)
        getApiDataProperties();
    }

    private void getApiDataProperties() {
        mDialog = Utils.getLoadingDialog(getActivity());

        mDialog.show();

        Log.e("getApiData :- ", "" + "getApiData");
        Call<ShortListedProperties> userCall = MyApplication.getApplication().getClient().getShortlistedPropertiesList(Constants.key, MyApplication.getUserId());
        userCall.enqueue(new Callback<ShortListedProperties>() {
            @Override
            public void onResponse(Call<ShortListedProperties> call, Response<ShortListedProperties> response) {
                Log.e("res body :- ", "" + response.body());
                mDialog.dismiss();
                if (response.isSuccessful()) {
                    final ShortListedProperties body = response.body();
                    if (body.getResult().getData()!= null &&body.getResult().getData().size() > 0) {
                        txtTotal.setText(body.getResult().getData().size() + getString(R.string.str_properties));
                        mAdapter.data = (ArrayList<PropertiesList.Datum>) body.getResult().getData();
                        mAdapter.notifyDataSetChanged();
                    } else {
                        txtLogin.setVisibility(MyApplication.getUserId() == -1 ? View.VISIBLE : View.GONE);
                        recyclerProperty.setVisibility(MyApplication.getUserId() == -1 ? View.GONE : View.VISIBLE);
                        txtTotal.setVisibility(MyApplication.getUserId() == -1 ? View.GONE : View.VISIBLE);
                        txtSort.setVisibility(MyApplication.getUserId() == -1 ? View.GONE : View.VISIBLE);
                    }
                } else {
                    final String errorResponse = Utils.convertStreamToString(response.errorBody().byteStream());
                    BaseError.ErrorType errorType = BaseError.ErrorType.fromErrorCode(response.code());
                    BaseError baseError = new BaseError(errorResponse, errorType);
                    Toast.makeText(getActivity(), "" + baseError.getErrorModel().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ShortListedProperties> call, Throwable t) {

                if (mDialog.isShowing())
                    mDialog.dismiss();
                Toast.makeText(getActivity(), getString(R.string.err_internet), Toast.LENGTH_SHORT).show();

                Log.e("User data", "Error");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
