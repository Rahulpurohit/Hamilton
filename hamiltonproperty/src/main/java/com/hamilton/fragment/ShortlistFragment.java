package com.hamilton.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hamilton.LoginActivity;
import com.hamilton.R;
import com.hamilton.adapter.PropertyAdapter;
import com.hamilton.application.MyApplication;
import com.hamilton.modal.PropertiesList;
import com.hamilton.modal.error.BaseError;
import com.hamilton.utility.Constants;
import com.hamilton.utility.Utils;
import com.hamilton.view.TypefacedTextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ShortlistFragment extends Fragment {
    @BindView(R.id.txt_header)
    TextView txtHeader;
    @BindView(R.id.txt_total)
    TypefacedTextView txtTotal;
    @BindView(R.id.txt_sort)
    Button txtSort;
    @BindView(R.id.recycler_property)
    RecyclerView recyclerProperty;
    @BindView(R.id.txt_login)
    TextView txtLogin;
    PropertyAdapter mAdapter;
    private Dialog mDialog;
    private ArrayList<PropertiesList.Datum> arrProperties;

    public ShortlistFragment() {
    }

    @OnClick(R.id.txt_login)
    public void gotoLogin() {
        startActivity(new Intent(getActivity(), LoginActivity.class).putExtra("fromtab", 2));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_properties, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mAdapter = new PropertyAdapter(true, txtTotal);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerProperty.setLayoutManager(mLayoutManager);
        recyclerProperty.setItemAnimator(new DefaultItemAnimator());
        recyclerProperty.setAdapter(mAdapter);
        txtHeader.setText(R.string.str_shortlist);
        txtLogin.setText(Html.fromHtml(getString(R.string.str_have_to_login)));
        txtLogin.setVisibility(MyApplication.getUserId() == -1 ? View.VISIBLE : View.GONE);
        recyclerProperty.setVisibility(MyApplication.getUserId() == -1 ? View.GONE : View.VISIBLE);
        txtTotal.setVisibility(MyApplication.getUserId() == -1 ? View.GONE : View.VISIBLE);
        txtSort.setVisibility(MyApplication.getUserId() == -1 ? View.GONE : View.VISIBLE);
        if (MyApplication.getUserId() != -1)
            getApiDataProperties();

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_to_refresh_home);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (swipeRefreshLayout.isRefreshing())
                    swipeRefreshLayout.setRefreshing(false);
                getApiDataProperties();
            }
        });
    }

    private void getApiDataProperties() {
        mDialog = Utils.getLoadingDialog(getActivity());

        mDialog.show();

        Log.e("getApiData :- ", "" + "getApiData");
        Call<PropertiesList> userCall = MyApplication.getApplication().getClient().getShortlistedPropertiesList(Constants.key, MyApplication.getUserId());
        userCall.enqueue(new Callback<PropertiesList>() {
            @Override
            public void onResponse(Call<PropertiesList> call, Response<PropertiesList> response) {
                Log.e("res body :- ", "" + response.body());
                mDialog.dismiss();
                if (response.isSuccessful()) {
                    final PropertiesList body = response.body();
                    if (body.getData() != null && body.getData().size() > 0) {
                        txtTotal.setText(body.getData().size() + getString(R.string.str_properties));
                        mAdapter.data = arrProperties =  (ArrayList<PropertiesList.Datum>) body.getData();

                        txtSort.setVisibility(View.VISIBLE);
                        txtSort.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //Creating the instance of PopupMenu
                                PopupMenu popup = new PopupMenu(getActivity(), v);
                                //Inflating the Popup using xml file
                                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                                //registering popup with OnMenuItemClickListener
                                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                    public boolean onMenuItemClick(MenuItem item) {

                                        ArrayList<PropertiesList.Datum> listSort = arrProperties;
                                        Collections.sort(listSort, new Comparator<PropertiesList.Datum>() {
                                            @Override
                                            public int compare(PropertiesList.Datum o1, PropertiesList.Datum o2) {
                                                if (TextUtils.isEmpty(o1.getPrice())) {
                                                    o1.setPrice("0");
                                                }
                                                if (TextUtils.isEmpty(o2.getPrice())) {
                                                    o2.setPrice("0");
                                                }
                                                float aFloat = Float.parseFloat(o1.getPrice());
                                                float bFloat = Float.parseFloat(o2.getPrice());

                                                return Float.compare(aFloat, bFloat);
                                            }
                                        });


                                        switch (item.getItemId()) {
                                            case R.id.low_high:
                                                mAdapter.data = listSort;
                                                mAdapter.notifyDataSetChanged();
                                                break;
                                            case R.id.high_low:
                                                Collections.reverse(listSort);
                                                mAdapter.data = listSort;
                                                mAdapter.notifyDataSetChanged();
                                                break;
                                            default:
                                                mAdapter.data = arrProperties;
                                                mAdapter.notifyDataSetChanged();
                                        }

                                        return true;
                                    }
                                });

                                popup.show();//showing popup menu
                            }
                        });
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

}
