package com.kollus.url;

public enum ButtonPosition {
    TopRight("TR"), TopLeft("TL"), BottomRight("BR"), BottomLeft("BL");
    private String value;
    private ButtonPosition(String _value){
        this.value = _value;
    }
    public String getValue(){
        return this.value;
    }
}
