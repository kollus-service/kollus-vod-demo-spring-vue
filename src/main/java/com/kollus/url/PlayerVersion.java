package com.kollus.url;

public enum PlayerVersion {
    V2("v2"),V3("v3"),V3E("v3e"),V4("html5"),V4A("v4a");
    private String value;
    private PlayerVersion(String _value){
        this.value = _value;
    }
    public String getValue(){
        return this.value;
    }
}
