package com.kollus.thirdparty.pallycon.token;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomData {
    private String drm_type;
    private String site_id;
    private String data;

    public CustomData() {}

    public String getDrm_type() {
        return drm_type;
    }

    public void setDrm_type(String drm_type) {
        this.drm_type = drm_type;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
