package com.hamilton.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hamilton.R;
import com.hamilton.utility.GlideApp;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HP on 04-06-2017.
 */

public class StageProgressPhotosAdapter extends RecyclerView.Adapter<StageProgressPhotosAdapter.ViewHolder> {
    public ArrayList<String> data = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image_progress, parent, false);

        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (!TextUtils.isEmpty(data.get(position))) {
            String url = data.get(position);
            GlideApp.with(holder.imgProperty.getContext())
                    .load(url).placeholder(R.drawable.image)
                    .into(holder.imgProperty);
        } else {
            Glide.with(holder.imgProperty.getContext()).clear(holder.imgProperty);
            holder.imgProperty.setImageDrawable(holder.imgProperty.getContext().getResources().getDrawable(R.drawable.image));
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_property)
        ImageView imgProperty;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
