package com.kollus.thirdparty.pallycon.policy;

public class MpegCenc {
    private String key_id;
    private String key;
    private String iv;

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public MpegCenc() {
    }

    public MpegCenc(String key_id, String key, String iv) {
        this.key_id = key_id;
        this.key = key;
        this.iv = iv;
    }
}
