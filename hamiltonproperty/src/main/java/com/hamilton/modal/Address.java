package com.hamilton.modal;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rahul Purohit on 6/4/2017.
 */

public class Address implements Parcelable {

    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("zoom")
    @Expose
    private String zoom;
    @SerializedName("center_lat")
    @Expose
    private String centerLat;
    @SerializedName("center_lng")
    @Expose
    private String centerLng;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getZoom() {
        return zoom;
    }

    public void setZoom(String zoom) {
        this.zoom = zoom;
    }

    public String getCenterLat() {
        return centerLat;
    }

    public void setCenterLat(String centerLat) {
        this.centerLat = centerLat;
    }

    public String getCenterLng() {
        return centerLng;
    }

    public void setCenterLng(String centerLng) {
        this.centerLng = centerLng;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.address);
        dest.writeString(this.lat);
        dest.writeString(this.lng);
        dest.writeString(this.zoom);
        dest.writeString(this.centerLat);
        dest.writeString(this.centerLng);
    }

    public Address() {
    }

    protected Address(Parcel in) {
        this.address = in.readString();
        this.lat = in.readString();
        this.lng = in.readString();
        this.zoom = in.readString();
        this.centerLat = in.readString();
        this.centerLng = in.readString();
    }

    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel source) {
            return new Address(source);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
}
