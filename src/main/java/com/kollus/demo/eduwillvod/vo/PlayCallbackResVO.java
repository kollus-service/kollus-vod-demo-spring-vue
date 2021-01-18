package com.kollus.demo.eduwillvod.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kollus.jwt.ZeroFilter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PlayCallbackResVO {

    private PlayCallbackResDataVO data;
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = ZeroFilter.class)
    private long exp;

    public PlayCallbackResVO(){}


    public PlayCallbackResVO(PlayCallbackResDataVO data, long exp) {
        this.data = data;
        this.exp = exp;
    }

    public PlayCallbackResDataVO getData() {
        return data;
    }

    public void setData(PlayCallbackResDataVO data) {
        this.data = data;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "PlayCallbackResVO{" +
                "data=" + data +
                ", exp=" + exp +
                '}';
    }
}
