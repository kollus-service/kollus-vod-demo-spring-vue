package com.kollus.vod.legacy;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kollus.vod.cdn.Cdn;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Live {
    private String url;
    private String poster_url;
    private Cdn cdn;
    private String auth_type;
    private boolean use_ip_validation;
    private boolean use_kollus_token;

    public Live(){}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPoster_url() {
        return poster_url;
    }

    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }

    public Cdn getCdn() {
        return cdn;
    }

    public void setCdn(Cdn cdn) {
        this.cdn = cdn;
    }

    public String getAuth_type() {
        return auth_type;
    }

    public void setAuth_type(String auth_type) {
        this.auth_type = auth_type;
    }

    public boolean isUse_ip_validation() {
        return use_ip_validation;
    }

    public void setUse_ip_validation(boolean use_ip_validation) {
        this.use_ip_validation = use_ip_validation;
    }

    public boolean isUse_kollus_token() {
        return use_kollus_token;
    }

    public void setUse_kollus_token(boolean use_kollus_token) {
        this.use_kollus_token = use_kollus_token;
    }
}
