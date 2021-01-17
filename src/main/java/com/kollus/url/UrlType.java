package com.kollus.url;

public enum UrlType {
    LIVE("v-live-kr.kollus.com"), VOD("v.kr.kollus.com");
    private String domain;
    private UrlType(String _domain){
        this.domain = _domain;
    }
    public String getDomain(){
        return this.domain;
    }
}
