package com.mrswimmer.shift.data.model.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FioResponse {

    @SerializedName("emailCo")
    @Expose
    private String emailCo;
    @SerializedName("status")
    @Expose
    private String status;

    public String getEmailCo() {
        return emailCo;
    }

    public void setEmailCo(String emailCo) {
        this.emailCo = emailCo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
