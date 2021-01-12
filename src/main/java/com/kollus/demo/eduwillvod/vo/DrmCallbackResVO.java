package com.kollus.demo.eduwillvod.vo;

import java.util.List;

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
