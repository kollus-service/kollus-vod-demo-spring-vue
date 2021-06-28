package com.kollus.vod.subtitle;

public class SubtitlePolicy {
    SubtitleFilter filter;
    boolean show_by_filter;
    boolean is_showable;
    public SubtitlePolicy(){}

    public SubtitleFilter getFilter() {
        return filter;
    }

    public void setFilter(SubtitleFilter filter) {
        this.filter = filter;
    }

    public boolean isShow_by_filter() {
        return show_by_filter;
    }

    public void setShow_by_filter(boolean show_by_fiter) {
        this.show_by_filter = show_by_fiter;
    }

    public boolean isIs_showable() {
        return is_showable;
    }

    public void setIs_showable(boolean is_showable) {
        this.is_showable = is_showable;
    }
}
