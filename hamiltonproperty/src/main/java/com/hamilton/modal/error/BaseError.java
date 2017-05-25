package com.hamilton.modal.error;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;

public final class BaseError {

    public String errorType;
    public String DEFAULT_MSG = "NO RESPONSE";
    ArrayList<Error> errors = new ArrayList<>();

    public ErrorModel getErrorModel() {
        return errorModel;
    }

    ErrorModel errorModel;
    public final ErrorType RESPONSE_CODE;
    public final String FULL_RESPONSE;


    public enum ErrorType {
        BAD_REQUEST(400),
        UNAUTHORIZED(401),
        FORBIDDEN(403),
        PRECONDITION_FAILED(412),
        UNPROCESSABLE_ENTITY(422),
        NOT_ACCEPTABLE(406),
        INTERNAL_ERROR(404),
        /**
         * When we don't identify error code.
         */
        UNKOWN_ERROR(-1);
        private int errorCode;

        ErrorType(int errorCode) {
            this.errorCode = errorCode;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public static ErrorType fromErrorCode(int errorCode) {
            for (ErrorType b : ErrorType.values()) {
                if (b.getErrorCode() == errorCode) {
                    return b;
                }
            }
            return UNKOWN_ERROR;
        }
    }

    public BaseError(String serverResponse, ErrorType response_code) {
        this.RESPONSE_CODE = response_code;
        this.FULL_RESPONSE = serverResponse;
        if (!TextUtils.isEmpty(serverResponse)) {
            try {
                ErrorModel error = new Gson().fromJson(serverResponse, ErrorModel.class);
                errorModel = error;
                Log.e("ERRORS", errors.toString());
            } catch (Exception e) {
                e.printStackTrace();
                errorType = "SERVER_RESPONSE";
                errors.add(new Error(errorType, serverResponse));
                Log.e("ERRORS", "ERROR-MSG FROM SERVER" + serverResponse);


            }
        } else {
            errorType = DEFAULT_MSG;
            errors.add(new Error(errorType, ""));
            Log.e("ERRORS", DEFAULT_MSG + " ");

        }
    }

    /**
     * Default object for error.
     */
    public static class Error {
        private String key;
        private String value;

        public Error(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }

    public ArrayList<Error> getErrors() {
        return errors;
    }
}
