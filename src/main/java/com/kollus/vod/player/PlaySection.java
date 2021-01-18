package com.kollus.vod.player;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kollus.jwt.PositiveFilter;

public class PlaySection {
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = PositiveFilter.class)
    int start_time;
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = PositiveFilter.class)
    int end_time;
    public PlaySection(){}

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getEnd_time() {
        return end_time;
    }

    public void setEnd_time(int end_time) {
        this.end_time = end_time;
    }
}
