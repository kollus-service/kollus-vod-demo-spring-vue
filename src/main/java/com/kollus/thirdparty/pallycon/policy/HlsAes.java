package com.kollus.thirdparty.pallycon.policy;

public class HlsAes {
    private String key;
    private String iv;

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

    public HlsAes() {
    }

    public HlsAes(String key, String iv) {
        this.key = key;
        this.iv = iv;
    }
}
