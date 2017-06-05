package com.hamilton;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hamilton.application.MyApplication;
import com.hamilton.modal.LikeUnlikeProperty;
import com.hamilton.modal.PropertiesList;
import com.hamilton.modal.error.BaseError;
import com.hamilton.utility.Constants;
import com.hamilton.utility.Utils;
import com.hamilton.view.TypefacedButton;
import com.hamilton.view.TypefacedTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropertyDetailActivity extends AppCompatActivity {

    @BindView(R.id.btn_back)
    View mBackBtn;
    @BindView(R.id.img_userLike)
    AppCompatImageView imgUserLike;
    @BindView(R.id.img_openBrowser)
    AppCompatImageView imgOpenBrowser;
    @BindView(R.id.img_property)
    AppCompatImageView imgProperty;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.txt_bedrooms)
    TypefacedTextView txtBedrooms;
    @BindView(R.id.txt_bathroom)
    TypefacedTextView txtBathroom;
    @BindView(R.id.txt_car_parking)
    TypefacedTextView txtCarParking;
    @BindView(R.id.imageView3)
    TypefacedButton imageView3;
    @BindView(R.id.img_userLike_2)
    AppCompatImageView imgUserLike2;
    @BindView(R.id.linearLayout2)
    LinearLayout linearLayout2;
    @BindView(R.id.txt_offer_over)
    TypefacedTextView txtOfferOver;
    @BindView(R.id.txt_address)
    TypefacedTextView txtAddress;
    @BindView(R.id.txt_details)
    TypefacedTextView txtDetails;
    @BindView(R.id.btn_call)
    TextView btnCall;
    @BindView(R.id.btn_appointment)
    TextView btnAppointment;
    @BindView(R.id.btn_email)
    TextView btnEmail;

    private PropertiesList.Datum datum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_detail);
        ButterKnife.bind(this);
        datum = getIntent().getParcelableExtra("PROPERTY_DATA");
        imgUserLike.setImageResource(datum.getIslike() ? R.drawable.like : R.drawable.icon_favourite_b);
        imgUserLike2.setImageResource(datum.getIslike() ? R.drawable.like : R.drawable.icon_favourite_b);
        txtBathroom.setText(datum.getNoOfBathrooms());
        txtBedrooms.setText(datum.getNoOfBadrooms());
        txtCarParking.setText(datum.getNoOfCars());

        txtOfferOver.setText(Html.fromHtml("Offer Over <b>" + datum.getPrice() + "</b>"));
        txtAddress.setText(datum.getPropertyName());

        imgOpenBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(datum.getPropertyUrl()) && datum.getPropertyUrl().contains("http://")) {
                    Uri uri = Uri.parse(datum.getPropertyUrl()); // missing 'http://' will cause crashed
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            }
        });

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txtDetails.setText(Html.fromHtml(datum.getPropertyDescription() + datum.getPropertyDescription()));

        final View.OnClickListener l = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApplication.getUserId() <= 0) {
                    Toast.makeText(imgUserLike.getContext(), "Please Login to perform this", Toast.LENGTH_LONG).show();
                    return;
                }
                ImageView img = (ImageView) v;
                ((ImageView) v).setImageResource(R.drawable.loading_img);
                final Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.rotationanim);
                v.setAnimation(animation);

                Call<LikeUnlikeProperty> t;
                if (!datum.getIslike()) {
                    t = MyApplication.getApplication().getClient().likeProperty(Constants.key, MyApplication.getUserId(), datum.getPropertyId());
                } else {
                    t = MyApplication.getApplication().getClient().unlikeProperty(Constants.key, MyApplication.getUserId(), datum.getPropertyId());

                }
                t.enqueue(new Callback<LikeUnlikeProperty>() {
                    @Override
                    public void onResponse(Call<LikeUnlikeProperty> call, Response<LikeUnlikeProperty> response) {
                        Log.e("res body :- ", "" + response.body());
                        if (response.isSuccessful()) {
                            datum.setIslike(!datum.getIslike());
                            imgUserLike.setImageResource(datum.getIslike() ? R.drawable.like : R.drawable.icon_favourite_b);
                            imgUserLike2.setImageResource(datum.getIslike() ? R.drawable.like : R.drawable.icon_favourite_b);
                            animation.cancel();
                        } else {
                            final String errorResponse = Utils.convertStreamToString(response.errorBody().byteStream());
                            BaseError.ErrorType errorType = BaseError.ErrorType.fromErrorCode(response.code());
                            BaseError baseError = new BaseError(errorResponse, errorType);
                            Toast.makeText(getBaseContext(), "" + baseError.getErrorModel().getMessage(), Toast.LENGTH_LONG).show();
                            animation.cancel();
                            imgUserLike.setImageResource(datum.getIslike() ? R.drawable.like : R.drawable.icon_favourite_b);
                            imgUserLike2.setImageResource(datum.getIslike() ? R.drawable.like : R.drawable.icon_favourite_b);
                        }
                    }

                    @Override
                    public void onFailure(Call<LikeUnlikeProperty> call, Throwable t) {
                        Toast.makeText(getBaseContext(), getString(R.string.err_internet), Toast.LENGTH_SHORT).show();
                        animation.cancel();
                        imgUserLike.setImageResource(datum.getIslike() ? R.drawable.like : R.drawable.icon_favourite_b);
                        imgUserLike2.setImageResource(datum.getIslike() ? R.drawable.like : R.drawable.icon_favourite_b);
                    }
                });
            }
        };
        imgUserLike.setOnClickListener(l);
        imgUserLike2.setOnClickListener(l);

        btnAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PropertyDetailActivity.this,AppointmentActivity.class);
                i.putExtra("PROPERTY_DATA",datum);
                startActivity(i);
            }
        });
    }
}
