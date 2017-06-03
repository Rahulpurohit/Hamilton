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

    public class Result implements Serializable {

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
            @SerializedName("preagreement")
            @Expose
            private String preagreement;
            @SerializedName("preagreement_process")
            @Expose
            private String preagreementProcess;
            @SerializedName("contract_procedure")
            @Expose
            private String contractProcedure;
            @SerializedName("tender")
            @Expose
            private String tender;
            @SerializedName("invoice")
            @Expose
            private String invoice;
            @SerializedName("receipts")
            @Expose
            private String receipts;
            @SerializedName("building_contracts")
            @Expose
            private String buildingContracts;
            @SerializedName("sketch_plans_array")
            @Expose
            private List<String> sketchPlansArray = null;
            @SerializedName("facade")
            @Expose
            private String facade;
            @SerializedName("origin_form")
            @Expose
            private String originForm;
            @SerializedName("variation_quote_request")
            @Expose
            private String variationQuoteRequest;
            @SerializedName("receipts_letter_for_banks")
            @Expose
            private String receiptsLetterForBanks;
            @SerializedName("land_information")
            @Expose
            private String landInformation;
            @SerializedName("plan_a_sub")
            @Expose
            private String planASub;
            @SerializedName("civil_engineering")
            @Expose
            private String civilEngineering;
            @SerializedName("compaction_report")
            @Expose
            private String compactionReport;
            @SerializedName("mcp")
            @Expose
            private String mcp;
            @SerializedName("building_envelope_and_design_guidelines")
            @Expose
            private String buildingEnvelopeAndDesignGuidelines;
            @SerializedName("building_updates")
            @Expose
            private String buildingUpdates;
            @SerializedName("building_plan")
            @Expose
            private String buildingPlan;
            @SerializedName("permit_updates")
            @Expose
            private String permitUpdates;
            @SerializedName("permit_with_stamped_plans")
            @Expose
            private String permitWithStampedPlans;
            @SerializedName("stage_progress_update")
            @Expose
            private String stageProgressUpdate;
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

            public String getPreagreement() {
                return preagreement;
            }

            public void setPreagreement(String preagreement) {
                this.preagreement = preagreement;
            }

            public String getPreagreementProcess() {
                return preagreementProcess;
            }

            public void setPreagreementProcess(String preagreementProcess) {
                this.preagreementProcess = preagreementProcess;
            }

            public String getContractProcedure() {
                return contractProcedure;
            }

            public void setContractProcedure(String contractProcedure) {
                this.contractProcedure = contractProcedure;
            }

            public String getTender() {
                return tender;
            }

            public void setTender(String tender) {
                this.tender = tender;
            }

            public String getInvoice() {
                return invoice;
            }

            public void setInvoice(String invoice) {
                this.invoice = invoice;
            }

            public String getReceipts() {
                return receipts;
            }

            public void setReceipts(String receipts) {
                this.receipts = receipts;
            }

            public String getBuildingContracts() {
                return buildingContracts;
            }

            public void setBuildingContracts(String buildingContracts) {
                this.buildingContracts = buildingContracts;
            }

            public List<String> getSketchPlansArray() {
                return sketchPlansArray;
            }

            public void setSketchPlansArray(List<String> sketchPlansArray) {
                this.sketchPlansArray = sketchPlansArray;
            }

            public String getFacade() {
                return facade;
            }

            public void setFacade(String facade) {
                this.facade = facade;
            }

            public String getOriginForm() {
                return originForm;
            }

            public void setOriginForm(String originForm) {
                this.originForm = originForm;
            }

            public String getVariationQuoteRequest() {
                return variationQuoteRequest;
            }

            public void setVariationQuoteRequest(String variationQuoteRequest) {
                this.variationQuoteRequest = variationQuoteRequest;
            }

            public String getReceiptsLetterForBanks() {
                return receiptsLetterForBanks;
            }

            public void setReceiptsLetterForBanks(String receiptsLetterForBanks) {
                this.receiptsLetterForBanks = receiptsLetterForBanks;
            }

            public String getLandInformation() {
                return landInformation;
            }

            public void setLandInformation(String landInformation) {
                this.landInformation = landInformation;
            }

            public String getPlanASub() {
                return planASub;
            }

            public void setPlanASub(String planASub) {
                this.planASub = planASub;
            }

            public String getCivilEngineering() {
                return civilEngineering;
            }

            public void setCivilEngineering(String civilEngineering) {
                this.civilEngineering = civilEngineering;
            }

            public String getCompactionReport() {
                return compactionReport;
            }

            public void setCompactionReport(String compactionReport) {
                this.compactionReport = compactionReport;
            }

            public String getMcp() {
                return mcp;
            }

            public void setMcp(String mcp) {
                this.mcp = mcp;
            }

            public String getBuildingEnvelopeAndDesignGuidelines() {
                return buildingEnvelopeAndDesignGuidelines;
            }

            public void setBuildingEnvelopeAndDesignGuidelines(String buildingEnvelopeAndDesignGuidelines) {
                this.buildingEnvelopeAndDesignGuidelines = buildingEnvelopeAndDesignGuidelines;
            }

            public String getBuildingUpdates() {
                return buildingUpdates;
            }

            public void setBuildingUpdates(String buildingUpdates) {
                this.buildingUpdates = buildingUpdates;
            }

            public String getBuildingPlan() {
                return buildingPlan;
            }

            public void setBuildingPlan(String buildingPlan) {
                this.buildingPlan = buildingPlan;
            }

            public String getPermitUpdates() {
                return permitUpdates;
            }

            public void setPermitUpdates(String permitUpdates) {
                this.permitUpdates = permitUpdates;
            }

            public String getPermitWithStampedPlans() {
                return permitWithStampedPlans;
            }

            public void setPermitWithStampedPlans(String permitWithStampedPlans) {
                this.permitWithStampedPlans = permitWithStampedPlans;
            }

            public String getStageProgressUpdate() {
                return stageProgressUpdate;
            }

            public void setStageProgressUpdate(String stageProgressUpdate) {
                this.stageProgressUpdate = stageProgressUpdate;
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

        }

    }

}