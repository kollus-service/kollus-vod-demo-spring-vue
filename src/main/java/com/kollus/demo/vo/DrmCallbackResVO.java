package com.kollus.demo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DrmCallbackResVO {

    List<DrmCallbackResDataVO> data;

    public DrmCallbackResVO(){}
    public DrmCallbackResVO(List<DrmCallbackResDataVO> data) {
        this.data = data;
    }

    public List<DrmCallbackResDataVO> getData() {
        return data;
    }

    public void setData(List<DrmCallbackResDataVO> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DrmCallbackResVO{" +
                "data=" + data +
                '}';
    }
}
