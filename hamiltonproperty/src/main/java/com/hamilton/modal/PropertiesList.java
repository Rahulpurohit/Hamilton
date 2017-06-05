package com.hamilton.modal;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PropertiesList implements Parcelable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("msg")
    @Expose
    private String msg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class Datum implements Parcelable {


        @SerializedName("PropertyId")
        @Expose
        private Integer propertyId;
        @SerializedName("PropertyImage")
        @Expose
        private String propertyImage;
        @SerializedName("PropertyUrl")
        @Expose
        private String propertyUrl;
        @SerializedName("PropertyDescription")
        @Expose
        private String propertyDescription;
        @SerializedName("PropertyName")
        @Expose
        private String propertyName;
        @SerializedName("Address")
        @Expose
        private Address address;
        @SerializedName("No_of_Bathrooms")
        @Expose
        private String noOfBathrooms;
        @SerializedName("No_of_Badrooms")
        @Expose
        private String noOfBadrooms;
        @SerializedName("No_of_cars")
        @Expose
        private String noOfCars;
        @SerializedName("surrounding")
        @Expose
        private String surrounding;
        @SerializedName("residential")
        @Expose
        private String residential;
        @SerializedName("home_types")
        @Expose
        private String homeTypes;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("toilet")
        @Expose
        private String toilet;
        @SerializedName("Size")
        @Expose
        private String size;
        @SerializedName("islike")
        @Expose
        private boolean islike;

        public Integer getPropertyId() {
            return propertyId;
        }

        public void setPropertyId(Integer propertyId) {
            this.propertyId = propertyId;
        }

        public String getPropertyImage() {
            return propertyImage;
        }

        public void setPropertyImage(String propertyImage) {
            this.propertyImage = propertyImage;
        }

        public String getPropertyUrl() {
            return propertyUrl;
        }

        public void setPropertyUrl(String propertyUrl) {
            this.propertyUrl = propertyUrl;
        }

        public String getPropertyDescription() {
            return propertyDescription;
        }

        public void setPropertyDescription(String propertyDescription) {
            this.propertyDescription = propertyDescription;
        }

        public String getPropertyName() {
            return propertyName;
        }

        public void setPropertyName(String propertyName) {
            this.propertyName = propertyName;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public String getNoOfBathrooms() {
            return noOfBathrooms;
        }

        public void setNoOfBathrooms(String noOfBathrooms) {
            this.noOfBathrooms = noOfBathrooms;
        }

        public String getNoOfBadrooms() {
            return noOfBadrooms;
        }

        public void setNoOfBadrooms(String noOfBadrooms) {
            this.noOfBadrooms = noOfBadrooms;
        }

        public String getNoOfCars() {
            return noOfCars;
        }

        public void setNoOfCars(String noOfCars) {
            this.noOfCars = noOfCars;
        }

        public String getSurrounding() {
            return surrounding;
        }

        public void setSurrounding(String surrounding) {
            this.surrounding = surrounding;
        }

        public String getResidential() {
            return residential;
        }

        public void setResidential(String residential) {
            this.residential = residential;
        }

        public String getHomeTypes() {
            return homeTypes;
        }

        public void setHomeTypes(String homeTypes) {
            this.homeTypes = homeTypes;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getToilet() {
            return toilet;
        }

        public void setToilet(String toilet) {
            this.toilet = toilet;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public boolean getIslike() {
            return islike;
        }

        public void setIslike(boolean islike) {
            this.islike = islike;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(this.propertyId);
            dest.writeString(this.propertyImage);
            dest.writeString(this.propertyUrl);
            dest.writeString(this.propertyDescription);
            dest.writeString(this.propertyName);
            dest.writeParcelable(this.address, flags);
            dest.writeString(this.noOfBathrooms);
            dest.writeString(this.noOfBadrooms);
            dest.writeString(this.noOfCars);
            dest.writeString(this.surrounding);
            dest.writeString(this.residential);
            dest.writeString(this.homeTypes);
            dest.writeString(this.price);
            dest.writeString(this.toilet);
            dest.writeString(this.size);
            dest.writeValue(this.islike);
        }

        public Datum() {
        }

        protected Datum(Parcel in) {
            this.propertyId = (Integer) in.readValue(Integer.class.getClassLoader());
            this.propertyImage = in.readString();
            this.propertyUrl = in.readString();
            this.propertyDescription = in.readString();
            this.propertyName = in.readString();
            this.address = in.readParcelable(Address.class.getClassLoader());
            this.noOfBathrooms = in.readString();
            this.noOfBadrooms = in.readString();
            this.noOfCars = in.readString();
            this.surrounding = in.readString();
            this.residential = in.readString();
            this.homeTypes = in.readString();
            this.price = in.readString();
            this.toilet = in.readString();
            this.size = in.readString();
            this.islike = (Boolean) in.readValue(Boolean.class.getClassLoader());
        }

        public static final Creator<Datum> CREATOR = new Creator<Datum>() {
            @Override
            public Datum createFromParcel(Parcel source) {
                return new Datum(source);
            }

            @Override
            public Datum[] newArray(int size) {
                return new Datum[size];
            }
        };
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status);
        dest.writeList(this.data);
        dest.writeString(this.msg);
    }

    public PropertiesList() {
    }

    protected PropertiesList(Parcel in) {
        this.status = in.readString();
        this.data = new ArrayList<Datum>();
        in.readList(this.data, Datum.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<PropertiesList> CREATOR = new Parcelable.Creator<PropertiesList>() {
        @Override
        public PropertiesList createFromParcel(Parcel source) {
            return new PropertiesList(source);
        }

        @Override
        public PropertiesList[] newArray(int size) {
            return new PropertiesList[size];
        }
    };
}