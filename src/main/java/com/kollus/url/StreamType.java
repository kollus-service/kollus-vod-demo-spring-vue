package com.kollus.url;

public enum StreamType {
    STREAMING("/s?"),
    INFO("/si?"),
    REDIRECT("/sr?");
    private String value;
    private StreamType(String _value){
        this.value = _value;
    }
    public String getValue(){
        return this.value;
    }
}
