package com.hamilton.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.hamilton.R;
import com.hamilton.modal.PropertiesList;
import com.hamilton.utility.GlideApp;
import com.hamilton.view.TypefacedButton;
import com.hamilton.view.TypefacedTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rahul Purohit on 6/3/2017.
 */

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.ViewHolder> {
    public ArrayList<PropertiesList.Datum> data = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_properties, parent, false);

        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PropertiesList.Datum datum = data.get(position);
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
        holder.txtAddress.setText(datum.getPropertyName());
        holder.btnNew.setText(datum.getHomeTypes());

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
        @BindView(R.id.imageView3)
        Button imageView3;
        @BindView(R.id.imageView5)
        ImageView imageView5;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
