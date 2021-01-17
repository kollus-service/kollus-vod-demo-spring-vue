package com.kollus.vod.cdn;

public class Cdn {
    String type;
    CdnPassword password;
    public Cdn(){}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CdnPassword getPassword() {
        return password;
    }

    public void setPassword(CdnPassword password) {
        this.password = password;
    }
}
