package com.kollus.vod.player;

public class Thumbnail {
    boolean enable;
    boolean thread;
    String type;
    public Thumbnail() {}

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isThread() {
        return thread;
    }

    public void setThread(boolean thread) {
        this.thread = thread;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
