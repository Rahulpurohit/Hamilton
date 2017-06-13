package com.hamilton;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hamilton.application.MyApplication;
import com.hamilton.modal.PropertiesList;
import com.hamilton.modal.SearchFilter;
import com.hamilton.modal.error.BaseError;
import com.hamilton.utility.Constants;
import com.hamilton.utility.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFilterActivity extends AppCompatActivity {

    protected static final int colorName = Color.BLUE;
    @BindView(R.id.radio_tab_group)
    RadioGroup radioTabGroup;
    @BindView(R.id.txt_filter_price_range)
    TextView txtFilterPriceRange;
    @BindView(R.id.txt_filter_property_type)
    TextView txtFilterPropertyType;
    @BindView(R.id.txt_filter_property_size)
    TextView getTxtFilterPropertySize;
    @BindView(R.id.llContent)
    LinearLayout llContent;
    @BindView(R.id.txt_search)
    EditText txt_search;
    Dialog mDialog;
    SearchFilter searchFilter;
    String keywords = "", bordering = "", residential = "", alltype = "", minprice = "", maxprice = "", bed = "", bath = "", car = "", toilets = "", landsize = "", type = "rent";
    List<PropertiesList.Datum> arrProperty = new ArrayList<>();
    RadioButton buttonOne;

    @OnClick(R.id.rl_filter_price_range)
    public void selectPriceRange() {
        List<String> actualPriceArray = new ArrayList<>();//searchFilter.getData().getPrices();
        addPriceArray(actualPriceArray);

        ArrayList<String> arrPriceRange = new ArrayList<>();
        for (int k = 0; actualPriceArray != null && k < actualPriceArray.size() - 1; k++) {
            if (TextUtils.isEmpty(actualPriceArray.get(k))) {
                arrPriceRange.add(getString(R.string.str_any));
            } else {
                if (k == 1)
                    arrPriceRange.add(getString(R.string.symbol_currency) + actualPriceArray.get(k) + "-" + getString(R.string.symbol_currency) + actualPriceArray.get(k + 1));
                else
                    arrPriceRange.add(getString(R.string.symbol_currency) + (Integer.parseInt(actualPriceArray.get(k)) + 1) + "-" + getString(R.string.symbol_currency) + actualPriceArray.get(k + 1));
            }
        }
        showDialogAlert(arrPriceRange, 0);

    }

    @OnClick(R.id.rl_filter_property_size)
    public void selectPropertySize() {
        List<String> actualPriceArray = new ArrayList<>();//searchFilter.getData().getPrices();
        addPropertySizeArray(actualPriceArray);

        ArrayList<String> arrPriceRange = new ArrayList<>();
        for (int k = 0; actualPriceArray != null && k < actualPriceArray.size() - 1; k++) {
            if (TextUtils.isEmpty(actualPriceArray.get(k))) {
                arrPriceRange.add(getString(R.string.str_any));
            } else {
                if (k == 1)
                    arrPriceRange.add(actualPriceArray.get(k) + "-" + actualPriceArray.get(k + 1) + getString(R.string.symbol_size));
                else
                    arrPriceRange.add((Integer.parseInt(actualPriceArray.get(k)) + 1) + "-" + actualPriceArray.get(k + 1) + getString(R.string.symbol_size));
            }
        }
        showDialogAlert(arrPriceRange, 2);

    }

    private void addPriceArray(List<String> actualPriceArray) {
        for (int i = 0; i < 601; i++) {
            if (i == 0)
                actualPriceArray.add("");
            else if (i == 600)
                actualPriceArray.add((i * 25000) + "+");
            else
                actualPriceArray.add("" + (i * 25000));
        }
    }

    private void addPropertySizeArray(List<String> actualPriceArray) {
        for (int i = 0; i < 51; i++) {
            if (i == 0)
                actualPriceArray.add("");
            else if (i == 50)
                actualPriceArray.add((i * 100) + "+");
            else
                actualPriceArray.add("" + (i * 100));
        }
    }

    @OnClick(R.id.rl_filter_property_type)
    public void selectPropertyType() {
        showDialogAlert((ArrayList<String>) searchFilter.getData().getPropertyType(), 1);
    }


    @OnClick(R.id.lblSearch)
    public void searchData() {

        for (int i = 0; i < llContent.getChildCount(); i++) {
            View view = llContent.getChildAt(i);
            RadioGroup rdGrp = (RadioGroup) view.findViewById(R.id.radioCellFiltergroup);
            rdGrp.setWeightSum(6);
            TextView lblCellFilterTitle = (TextView) view.findViewById(R.id.lblCellFilterTitle);
            int radioButtonID = rdGrp.getCheckedRadioButtonId();
            RadioButton radioButton = (RadioButton) rdGrp.findViewById(radioButtonID);

            String selectedtext = radioButton.getText().toString();

            keywords = TextUtils.isEmpty(txt_search.getText()) ? "" : txt_search.getText().toString();
            if (txtFilterPriceRange.getText() != null && txtFilterPriceRange.getText().toString().contains("-")) {
                String[] arrPrice = txtFilterPriceRange.getText().toString().split("-");
                minprice = arrPrice[0].replaceAll(getString(R.string.symbol_currency), "");
                maxprice = arrPrice[1].replaceAll(getString(R.string.symbol_currency), "");
            } else {
                minprice = "";
                maxprice = "";

            }

            if (getTxtFilterPropertySize.getText() != null) {
                landsize = getTxtFilterPropertySize.getText().toString().equalsIgnoreCase(getString(R.string.str_any)) ? "" : getTxtFilterPropertySize.getText().toString().replaceAll(getString(R.string.symbol_size), "");
            }

            type = (buttonOne.isChecked() ? "buy" : "rent");

            alltype = txtFilterPropertyType.getText().toString().equalsIgnoreCase(getString(R.string.str_any)) ? "" : txtFilterPropertyType.getText().toString();


            switch (lblCellFilterTitle.getText().toString()) {
                case "Bedrooms":
                    bed = selectedtext;
                    break;
                case "Bathrooms":
                    bath = selectedtext;
                    break;
                case "Car Parks":
                    car = selectedtext;
                    break;
                /*case "Land Size":
                    landsize = selectedtext;
                    break;*/
                case "Toilets":
                    toilets = selectedtext;
                    break;
                default:
                    break;
            }


        }

        getApiFilterData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_filter);
        ButterKnife.bind(this);

        buttonOne = (RadioButton) radioTabGroup.getChildAt(0);
        buttonOne.setChecked(true);

        txt_search.setText(getIntent().getStringExtra(Constants.KEY_SEARCH_KEY));

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


    private void getApiFilterData() {
        mDialog = Utils.getLoadingDialog(this);

        if (!mDialog.isShowing())
            mDialog.show();

        Log.e("getApiData :- ", "" + "getApiData");
        Call<PropertiesList> userCall = MyApplication.getApplication().getClient().getPropertyFilterList(Constants.key, keywords, bordering, residential, alltype, minprice, maxprice, bed, bath, car, toilets, landsize, type);
        userCall.enqueue(new Callback<PropertiesList>() {
            @Override
            public void onResponse(Call<PropertiesList> call, Response<PropertiesList> response) {
                Log.e("res body :- ", "" + response.body());
                if (mDialog.isShowing())
                    mDialog.dismiss();
                if (response.isSuccessful()) {
                    arrProperty = response.body().getData();
                    if (arrProperty.size() == 0) {
                        Toast.makeText(SearchFilterActivity.this, "No Data Found... Try Again", Toast.LENGTH_LONG).show();
                    } else {
                        Intent intent = new Intent();
                        intent.putParcelableArrayListExtra("data", (ArrayList<? extends Parcelable>) arrProperty);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                    // Redirect to Home Fragment
                } else {
                    final String errorResponse = Utils.convertStreamToString(response.errorBody().byteStream());
                    BaseError.ErrorType errorType = BaseError.ErrorType.fromErrorCode(response.code());
                    BaseError baseError = new BaseError(errorResponse, errorType);
                    Toast.makeText(getApplicationContext(), "" + baseError.getErrorModel().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PropertiesList> call, Throwable t) {

                if (mDialog.isShowing())
                    mDialog.dismiss();
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();

                Log.e("User data", "Error");
            }
        });
    }

    private void setFilterLayout() {
        llContent.removeAllViews();

        ArrayList<String> arrayHardCoded = new ArrayList<>();
        for (int k = 0; k < 6; k++) {
            if (k == 0)
                arrayHardCoded.add("");
            else if (k == 5)
                arrayHardCoded.add(k + "+");
            else
                arrayHardCoded.add("" + k);
        }

        ArrayList<String> arrayHardCodedLandSize = new ArrayList<>();
        for (int k = 0; k < 6; k++) {
            if (k == 0)
                arrayHardCodedLandSize.add("");
            else if (k == 5)
                arrayHardCodedLandSize.add((k * 100) + "+");
            else
                arrayHardCodedLandSize.add("" + (k * 100));
        }


        addCellData(arrayHardCoded, R.string.str_bedrooms);
        addCellData(arrayHardCoded, R.string.str_bathrooms);
        addCellData(arrayHardCoded, R.string.str_car_parks);
        //addCellData(arrayHardCodedLandSize, R.string.str_land_size);
        addCellData(arrayHardCoded, R.string.str_toilets);

        /*addCellData(searchFilter.getData().getBadrooms(), R.string.str_bedrooms);
        addCellData(searchFilter.getData().getBathrooms(), R.string.str_bathrooms);
        addCellData(searchFilter.getData().getCars(), R.string.str_car_parks);
        addCellData(searchFilter.getData().getLandsize(), R.string.str_land_size);
        addCellData(searchFilter.getData().getToilet(), R.string.str_toilets);*/

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
        int size = (arr.size() > 6 ? 6 : arr.size());
        for (int i = 0; i < size; i++) {
            radioView = LayoutInflater.from(this).inflate(R.layout.cell_filter_sub, radioGroup, false);
            rBtn = (RadioButton) radioView;
            rBtn.setTag(i);
            rBtn.setText(TextUtils.isEmpty(arr.get(i)) ? getString(R.string.str_any) : arr.get(i));
            if (i == 0) {
                rBtn.setBackgroundResource(R.drawable.bg_filter_first);
            } else if (i == size - 1) {
                rBtn.setChecked(false);
                if (arr.size() > 6)
                    rBtn.append("+");
                rBtn.setBackgroundResource(R.drawable.bg_filter_last);
            } else {
                rBtn.setBackgroundResource(R.drawable.bg_filter_midle);
            }
            radioGroup.addView(radioView);
/*
            radioGroup.addView(radioView, new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1));*/
        }
    }


    private void showDialogAlert(ArrayList<String> arr, final int check) {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(SearchFilterActivity.this);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                SearchFilterActivity.this,
                android.R.layout.select_dialog_singlechoice);

        for (int k = 0; k < arr.size(); k++) {
            arrayAdapter.add(arr.get(k));
        }

        builderSingle.setNegativeButton(
                "cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builderSingle.setAdapter(
                arrayAdapter,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strName = arrayAdapter.getItem(which);
                        switch (check) {
                            case 0:
                                txtFilterPriceRange.setText("" + strName);
                                break;
                            case 1:
                                txtFilterPropertyType.setText("" + strName);
                                break;
                            case 2:
                                getTxtFilterPropertySize.setText("" + strName);
                                break;

                        }
                    }
                });
        builderSingle.show();
    }


    public void clearSearch(View view) {
        txt_search.setText("");
    }
}
