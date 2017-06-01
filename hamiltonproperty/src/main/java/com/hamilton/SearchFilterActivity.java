package com.hamilton;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hamilton.application.MyApplication;
import com.hamilton.modal.SearchFilter;
import com.hamilton.modal.error.BaseError;
import com.hamilton.utility.Constants;
import com.hamilton.utility.Utils;
import com.hamilton.view.TwoHintEditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFilterActivity extends AppCompatActivity {

    @BindView(R.id.radio_tab_group)
    RadioGroup radioTabGroup;
    @BindView(R.id.txt_filter_price_range)
    TwoHintEditText txtFilterPriceRange;
    @BindView(R.id.txt_filter_property_type)
    TwoHintEditText txtFilterPropertyType;
    @BindView(R.id.llContent)
    LinearLayout llContent;
    @BindView(R.id.txt_search)
    EditText txt_search;

    @OnClick(R.id.lblSearch)
    public void searchData() {

    }


    Dialog mDialog;
    SearchFilter searchFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_filter);
        ButterKnife.bind(this);

        final RadioButton buttonOne = (RadioButton) radioTabGroup.getChildAt(0);
        final RadioButton buttonTwo = (RadioButton) radioTabGroup.getChildAt(1);
        buttonOne.setChecked(true);

        getApiData();


    }

    private void getApiData() {
        mDialog = Utils.getLoadingDialog(this);

        if (!mDialog.isShowing())
            mDialog.show();

        Log.e("getApiData :- ", "" + "getApiData");
        Call<SearchFilter> userCall = MyApplication.getApplication().getClient().getSearchFilter(Constants.key);
        userCall.enqueue(new Callback<SearchFilter>() {
            @Override
            public void onResponse(Call<SearchFilter> call, Response<SearchFilter> response) {
                Log.e("res body :- ", "" + response.body());
                if (mDialog.isShowing())
                    mDialog.dismiss();
                if (response.isSuccessful()) {
                    searchFilter = response.body();
                    setFilterLayout();
                } else {
                    final String errorResponse = Utils.convertStreamToString(response.errorBody().byteStream());
                    BaseError.ErrorType errorType = BaseError.ErrorType.fromErrorCode(response.code());
                    BaseError baseError = new BaseError(errorResponse, errorType);
                    Toast.makeText(getApplicationContext(), "" + baseError.getErrorModel().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SearchFilter> call, Throwable t) {

                if (mDialog.isShowing())
                    mDialog.dismiss();
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();

                Log.e("User data", "Error");
            }
        });
    }


    private void setFilterLayout() {
        llContent.removeAllViews();

        addCellData(searchFilter.getResult().getData().getBadrooms(), R.string.str_bedrooms);
        addCellData(searchFilter.getResult().getData().getBathrooms(), R.string.str_bathrooms);
        addCellData(searchFilter.getResult().getData().getCars(), R.string.str_car_parks);
        addCellData(searchFilter.getResult().getData().getLandsize(), R.string.str_land_size);
        addCellData(searchFilter.getResult().getData().getToilet(), R.string.str_toilets);

    }

    private void addCellData(List<String> filterList, int titleId) {
        View inflatedView = null;
        if (filterList != null && filterList.size() > 0) {
            inflatedView = LayoutInflater.from(this).inflate(R.layout.cell_filter_main, null);
            String title = getString(titleId);
            ((TextView) inflatedView.findViewById(R.id.lblCellFilterTitle)).setText(title);
            RadioGroup rdGrp = (RadioGroup) inflatedView.findViewById(R.id.radioCellFiltergroup);
            addRadioView(rdGrp, filterList);
            ((RadioButton) rdGrp.getChildAt(0)).setChecked(true);
            llContent.addView(inflatedView);
        }
    }

    private void addRadioView(RadioGroup radioGroup, List<String> arr) {
        View radioView;
        RadioButton rBtn;
        int size = (arr.size() > 5 ? 5 : arr.size());
        for (int i = 0; i < size; i++) {
            radioView = LayoutInflater.from(this).inflate(R.layout.cell_filter_sub, null);
            rBtn = (RadioButton) radioView;
            rBtn.setTag(i);
            rBtn.setText(TextUtils.isEmpty(arr.get(i)) ? getString(R.string.str_any) : arr.get(i));
            if (i == 0) {
                rBtn.setBackgroundResource(R.drawable.bg_filter_first);
            } else if (i == size - 1) {
                rBtn.setChecked(false);
                if (arr.size() > 5)
                    rBtn.append("+");
                rBtn.setBackgroundResource(R.drawable.bg_filter_last);
            } else {
                rBtn.setBackgroundResource(R.drawable.bg_filter_midle);
            }
            radioGroup.addView(radioView);
        }
    }


}
