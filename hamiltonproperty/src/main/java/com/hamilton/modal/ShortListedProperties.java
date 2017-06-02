package com.hamilton.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HP on 02-06-2017.
 */

public class ShortListedProperties {

    @SerializedName("result")
    @Expose
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result {

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


        public class Datum {

            @SerializedName("propertyId")
            @Expose
            private String propertyId;
            @SerializedName("PropertyImage")
            @Expose
            private String propertyImage;
            @SerializedName("PropertyUrl")
            @Expose
            private String propertyUrl;
            @SerializedName("PropertyName")
            @Expose
            private String propertyName;
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

            public String getPropertyId() {
                return propertyId;
            }

            public void setPropertyId(String propertyId) {
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

            public String getPropertyName() {
                return propertyName;
            }

            public void setPropertyName(String propertyName) {
                this.propertyName = propertyName;
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

        }

    }

}
