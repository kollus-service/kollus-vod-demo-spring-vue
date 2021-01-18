package com.kollus.vod.drm;

public class DrmPolicy {
    String kind;
    String streaming_type;
    DrmPolicyData data;

    public DrmPolicy() {
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getStreaming_type() {
        return streaming_type;
    }

    public void setStreaming_type(String streaming_type) {
        this.streaming_type = streaming_type;
    }

    public DrmPolicyData getData() {
        return data;
    }

    public void setData(DrmPolicyData data) {
        this.data = data;
    }
}
