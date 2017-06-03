package com.hamilton.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/*{
        "result": {
        "status": "true",
        "data": {
        "ValidUser": "yes",
        "userId": 1,
        "user_email": "jaiagarwal28@gmail.com",
        "user_registered": "2016-12-27 04:29:40",
        "display_name": "cogency"
        },
        "msg": "Login Successfully"
        }
        }*/

public class User implements Serializable {

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


        public class Data {

            @SerializedName("ValidUser")
            @Expose
            private String validUser;
            @SerializedName("userId")
            @Expose
            private int userId;
            @SerializedName("user_email")
            @Expose
            private String userEmail;
            @SerializedName("user_registered")
            @Expose
            private String userRegistered;
            @SerializedName("display_name")
            @Expose
            private String displayName;
            @SerializedName("BuildingInformation")
            @Expose
            private String buildingInformation;
            @SerializedName("BuildingInformationDetails")
            @Expose
            private List<LandInformationDetail> buildingInformationDetails = null;
            @SerializedName("land_information")
            @Expose
            private String landInformation;
            @SerializedName("landInformationDetails")
            @Expose
            private List<LandInformationDetail> landInformationDetails = null;
            @SerializedName("buildingUpdates")
            @Expose
            private List<LandInformationDetail> buildingUpdates = null;
            @SerializedName("supervisor_number")
            @Expose
            private String supervisorNumber;
            @SerializedName("stage_progress_photos")
            @Expose
            private List<String> stageProgressPhotos = null;
            @SerializedName("customer_care")
            @Expose
            private String customerCare;
            @SerializedName("contract_updates")
            @Expose
            private String contractUpdates;
            @SerializedName("permit_updates_care")
            @Expose
            private String permitUpdatesCare;
            @SerializedName("accountant_name")
            @Expose
            private String accountantName;
            @SerializedName("accountant_email")
            @Expose
            private String accountantEmail;
            @SerializedName("accountant_phone")
            @Expose
            private String accountantPhone;
            @SerializedName("supervisor_name")
            @Expose
            private String supervisorName;
            @SerializedName("supervisor_email")
            @Expose
            private String supervisorEmail;
            @SerializedName("supervisor_phone")
            @Expose
            private String supervisorPhone;

            public String getValidUser() {
                return validUser;
            }

            public void setValidUser(String validUser) {
                this.validUser = validUser;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getUserEmail() {
                return userEmail;
            }

            public void setUserEmail(String userEmail) {
                this.userEmail = userEmail;
            }

            public String getUserRegistered() {
                return userRegistered;
            }

            public void setUserRegistered(String userRegistered) {
                this.userRegistered = userRegistered;
            }

            public String getDisplayName() {
                return displayName;
            }

            public void setDisplayName(String displayName) {
                this.displayName = displayName;
            }

            public String getBuildingInformation() {
                return buildingInformation;
            }

            public void setBuildingInformation(String buildingInformation) {
                this.buildingInformation = buildingInformation;
            }

            public List<LandInformationDetail> getBuildingInformationDetails() {
                return buildingInformationDetails;
            }

            public void setBuildingInformationDetails(List<LandInformationDetail> buildingInformationDetails) {
                this.buildingInformationDetails = buildingInformationDetails;
            }

            public String getLandInformation() {
                return landInformation;
            }

            public void setLandInformation(String landInformation) {
                this.landInformation = landInformation;
            }

            public List<LandInformationDetail> getLandInformationDetails() {
                return landInformationDetails;
            }

            public void setLandInformationDetails(List<LandInformationDetail> landInformationDetails) {
                this.landInformationDetails = landInformationDetails;
            }

            public List<LandInformationDetail> getBuildingUpdates() {
                return buildingUpdates;
            }

            public void setBuildingUpdates(List<LandInformationDetail> buildingUpdates) {
                this.buildingUpdates = buildingUpdates;
            }

            public String getSupervisorNumber() {
                return supervisorNumber;
            }

            public void setSupervisorNumber(String supervisorNumber) {
                this.supervisorNumber = supervisorNumber;
            }

            public List<String> getStageProgressPhotos() {
                return stageProgressPhotos;
            }

            public void setStageProgressPhotos(List<String> stageProgressPhotos) {
                this.stageProgressPhotos = stageProgressPhotos;
            }

            public String getCustomerCare() {
                return customerCare;
            }

            public void setCustomerCare(String customerCare) {
                this.customerCare = customerCare;
            }

            public String getContractUpdates() {
                return contractUpdates;
            }

            public void setContractUpdates(String contractUpdates) {
                this.contractUpdates = contractUpdates;
            }

            public String getPermitUpdatesCare() {
                return permitUpdatesCare;
            }

            public void setPermitUpdatesCare(String permitUpdatesCare) {
                this.permitUpdatesCare = permitUpdatesCare;
            }

            public String getAccountantName() {
                return accountantName;
            }

            public void setAccountantName(String accountantName) {
                this.accountantName = accountantName;
            }

            public String getAccountantEmail() {
                return accountantEmail;
            }

            public void setAccountantEmail(String accountantEmail) {
                this.accountantEmail = accountantEmail;
            }

            public String getAccountantPhone() {
                return accountantPhone;
            }

            public void setAccountantPhone(String accountantPhone) {
                this.accountantPhone = accountantPhone;
            }

            public String getSupervisorName() {
                return supervisorName;
            }

            public void setSupervisorName(String supervisorName) {
                this.supervisorName = supervisorName;
            }

            public String getSupervisorEmail() {
                return supervisorEmail;
            }

            public void setSupervisorEmail(String supervisorEmail) {
                this.supervisorEmail = supervisorEmail;
            }

            public String getSupervisorPhone() {
                return supervisorPhone;
            }

            public void setSupervisorPhone(String supervisorPhone) {
                this.supervisorPhone = supervisorPhone;
            }

            public class LandInformationDetail {

                @SerializedName("title")
                @Expose
                private String title;
                @SerializedName("url")
                @Expose
                private String url;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

            }

        }

    }

}