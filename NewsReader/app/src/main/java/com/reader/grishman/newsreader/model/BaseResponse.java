package com.reader.grishman.newsreader.model;

import android.support.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Base class for response objects, encapsulates success and result
 */
public abstract class BaseResponse {

    @JsonProperty("status")
    private String status;

    @JsonProperty("source")
    private String source;

    @JsonProperty("sortBy")
    private String sortBy;

    @JsonProperty("message")
    private String message;


    public boolean isSuccess() {
        if (status.equals("ok")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                "success=" + status +
                ", error=" + message +
                '}';
    }

    /**
     * Common class to hold server errors and description
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Error {

        @JsonProperty("status")
        private String status;

        @JsonProperty("message")
        private String message;

        public String getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }
    }
}
