package com.kollus.demo.eduwillvod.vo;

public class PlaySectionVO {
    private long start_time;
    private long end_time;

    public PlaySectionVO(){}
    public PlaySectionVO(long start_time, long end_time) {
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public long getStart_time() {
        return start_time;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(long end_time) {
        this.end_time = end_time;
    }

    @Override
    public String toString() {
        return "PlaySection{" +
                "start_time=" + start_time +
                ", end_time=" + end_time +
                '}';
    }
}
