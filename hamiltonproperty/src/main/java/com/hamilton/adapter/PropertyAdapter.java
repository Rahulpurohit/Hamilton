package com.hamilton.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hamilton.PropertyDetailActivity;
import com.hamilton.R;
import com.hamilton.application.MyApplication;
import com.hamilton.modal.LikeUnlikeProperty;
import com.hamilton.modal.PropertiesList;
import com.hamilton.modal.error.BaseError;
import com.hamilton.utility.Constants;
import com.hamilton.utility.GlideApp;
import com.hamilton.utility.Utils;
import com.hamilton.view.TypefacedButton;
import com.hamilton.view.TypefacedTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.ViewHolder> {
    public ArrayList<PropertiesList.Datum> data = new ArrayList<>();
    boolean isShortList;
    TextView txtTotal;

    public PropertyAdapter(boolean isShortList) {
        this.isShortList = isShortList;
    }

    public PropertyAdapter(boolean isShortList, TextView txtTotal) {
        this.isShortList = isShortList;
        this.txtTotal = txtTotal;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_properties, parent, false);

        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final PropertiesList.Datum datum = data.get(position);
        final String propertyImage = datum.getPropertyImage();
        if (!TextUtils.isEmpty(propertyImage)) {
            String url = propertyImage;
            GlideApp.with(holder.imgProperty.getContext())
                    .load(url).placeholder(R.drawable.image)
                    .into(holder.imgProperty);
        } else {
            Glide.with(holder.imgProperty.getContext()).clear(holder.imgProperty);
            holder.imgProperty.setImageDrawable(holder.imgProperty.getContext().getResources().getDrawable(R.drawable.image));
        }

        holder.txtBathroom.setText(datum.getNoOfBathrooms());
        holder.txtBedrooms.setText(datum.getNoOfBadrooms());
        holder.txtCarParking.setText(datum.getNoOfCars());


        holder.txtOfferOver.setText(Html.fromHtml("Offer Over <b>" + datum.getPrice() + "</b>"));
        holder.txtAddress.setText(datum.getAddress().getAddress());
        holder.txtPropertyName.setText(datum.getPropertyName());
        holder.btnNew.setText(datum.getHomeTypes());

        holder.imgUserLike.setImageResource(datum.getIslike() ? R.drawable.like : R.drawable.icon_favourite_b);

        holder.imgUserLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApplication.getUserId() <= 0) {
                    Toast.makeText(holder.imgUserLike.getContext(), "Please Login to perform this", Toast.LENGTH_LONG).show();
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
                            data.get(holder.getAdapterPosition()).setIslike(!datum.getIslike());
                            if (isShortList) {
                                if (!data.get(holder.getAdapterPosition()).getIslike()) {
                                    try {
                                        txtTotal.setText((data.size() - 1) + " of " + (data.size() - 1) + txtTotal.getContext().getString(R.string.str_properties));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    data.remove(holder.getAdapterPosition());
                                    notifyItemRemoved(holder.getAdapterPosition());
                                    animation.cancel();
                                }
                            } else {
                                notifyItemChanged(holder.getAdapterPosition());
                                animation.cancel();
                            }
                        } else {
                            final String errorResponse = Utils.convertStreamToString(response.errorBody().byteStream());
                            BaseError.ErrorType errorType = BaseError.ErrorType.fromErrorCode(response.code());
                            BaseError baseError = new BaseError(errorResponse, errorType);
                            Toast.makeText(holder.imgUserLike.getContext(), "" + baseError.getErrorModel().getMessage(), Toast.LENGTH_LONG).show();
                            animation.cancel();
                            notifyItemChanged(holder.getAdapterPosition());
                        }
                    }

                    @Override
                    public void onFailure(Call<LikeUnlikeProperty> call, Throwable t) {
                        Toast.makeText(holder.imgUserLike.getContext(), holder.imgUserLike.getContext().getString(R.string.err_internet), Toast.LENGTH_SHORT).show();
                        animation.cancel();
                        notifyItemChanged(holder.getAdapterPosition());

                    }
                });
            }
        });
        holder.mClickView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i = new Intent(v.getContext(), PropertyDetailActivity.class);
                i.putExtra("PROPERTY_DATA", datum);
                v.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_property)
        ImageView imgProperty;
        @BindView(R.id.btn_new)
        TypefacedButton btnNew;
        @BindView(R.id.linearLayout)
        LinearLayout linearLayout;
        @BindView(R.id.txt_bedrooms)
        TypefacedTextView txtBedrooms;
        @BindView(R.id.txt_bathroom)
        TypefacedTextView txtBathroom;
        @BindView(R.id.txt_car_parking)
        TypefacedTextView txtCarParking;
        @BindView(R.id.imageView4)
        ImageView imageView4;
        @BindView(R.id.linearLayout2)
        LinearLayout linearLayout2;
        @BindView(R.id.txt_offer_over)
        TypefacedTextView txtOfferOver;
        @BindView(R.id.txt_address)
        TypefacedTextView txtAddress;
        @BindView(R.id.txt_property_name)
        TypefacedTextView txtPropertyName;
        @BindView(R.id.imageView3)
        Button imageView3;
        @BindView(R.id.img_userLike)
        ImageView imgUserLike;

        View mClickView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mClickView = view;
        }
    }
}
