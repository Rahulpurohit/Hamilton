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
import android.widget.Toast;

import com.hamilton.R;
import com.hamilton.adapter.PropertyAdapter;
import com.hamilton.application.MyApplication;
import com.hamilton.modal.PropertiesList;
import com.hamilton.modal.error.BaseError;
import com.hamilton.utility.Constants;
import com.hamilton.utility.Utils;
import com.hamilton.view.TypefacedTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PropertiesFragment extends Fragment {


    @BindView(R.id.txt_total)
    TypefacedTextView txtTotal;
    @BindView(R.id.txt_sort)
    TypefacedTextView txtSort;
    @BindView(R.id.recycler_property)
    RecyclerView recyclerProperty;

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


        getApiDataProperties();
    }

    private void getApiDataProperties() {
        mDialog = Utils.getLoadingDialog(getActivity());

        mDialog.show();

        Log.e("getApiData :- ", "" + "getApiData");
        Call<PropertiesList> userCall = MyApplication.getApplication().getClient().getPropertiesList(Constants.key, MyApplication.getUserId());
        userCall.enqueue(new Callback<PropertiesList>() {
            @Override
            public void onResponse(Call<PropertiesList> call, Response<PropertiesList> response) {
                Log.e("res body :- ", "" + response.body());
                mDialog.dismiss();
                if (response.isSuccessful()) {
                    final PropertiesList body = response.body();
                    txtTotal.setText(body.getData().size() + getString(R.string.str_properties));
                    mAdapter.data = (ArrayList<PropertiesList.Datum>) body.getData();
                    mAdapter.notifyDataSetChanged();
                } else {
                    final String errorResponse = Utils.convertStreamToString(response.errorBody().byteStream());
                    BaseError.ErrorType errorType = BaseError.ErrorType.fromErrorCode(response.code());
                    BaseError baseError = new BaseError(errorResponse, errorType);
                    Toast.makeText(getActivity(), "" + baseError.getErrorModel().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PropertiesList> call, Throwable t) {

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


    public void setDatumList(List<PropertiesList.Datum> datumList) {
        if (mAdapter != null) {
            mAdapter.data = (ArrayList<PropertiesList.Datum>) datumList;
            txtTotal.setText(datumList.size() + getString(R.string.str_properties));
            mAdapter.notifyDataSetChanged();
        }
    }
}
