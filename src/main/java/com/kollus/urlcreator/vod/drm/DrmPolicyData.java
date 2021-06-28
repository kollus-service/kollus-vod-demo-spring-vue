package com.kollus.vod.drm;

public class DrmPolicyData {
    String license_url;
    String certificate_url;
    CustomHeader custom_header;
    public DrmPolicyData(){}

    public String getLicense_url() {
        return license_url;
    }

    public void setLicense_url(String license_url) {
        this.license_url = license_url;
    }

    public String getCertificate_url() {
        return certificate_url;
    }

    public void setCertificate_url(String certificate_url) {
        this.certificate_url = certificate_url;
    }

    public CustomHeader getCustom_header() {
        return custom_header;
    }

    public void setCustom_header(CustomHeader custom_header) {
        this.custom_header = custom_header;
    }
}
