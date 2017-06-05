package com.hamilton.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HP on 01-06-2017.
 */

public class SearchFilter implements Serializable {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("msg")
    @Expose
    private String msg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public class Data implements Serializable {

        @SerializedName("badrooms")
        @Expose
        private List<String> badrooms = null;
        @SerializedName("bathrooms")
        @Expose
        private List<String> bathrooms = null;
        @SerializedName("cars")
        @Expose
        private List<String> cars = null;
        @SerializedName("landsize")
        @Expose
        private List<String> landsize = null;
        @SerializedName("toilet")
        @Expose
        private List<String> toilet = null;

        public List<String> getBadrooms() {
            return badrooms;
        }

        public void setBadrooms(List<String> badrooms) {
            this.badrooms = badrooms;
        }

        public List<String> getBathrooms() {
            return bathrooms;
        }

        public void setBathrooms(List<String> bathrooms) {
            this.bathrooms = bathrooms;
        }

        public List<String> getCars() {
            return cars;
        }

        public void setCars(List<String> cars) {
            this.cars = cars;
        }

        public List<String> getLandsize() {
            return landsize;
        }

        public void setLandsize(List<String> landsize) {
            this.landsize = landsize;
        }

        public List<String> getToilet() {
            return toilet;
        }

        public void setToilet(List<String> toilet) {
            this.toilet = toilet;
        }

    }


}
