package com.kollus.live;

public class ChattingPolicy {
    private boolean is_visible;
    private boolean is_admin;
    private String position;

    public ChattingPolicy() {

    }

    public ChattingPolicy(boolean is_visible, boolean is_admin, String position) {
        this.is_visible = is_visible;
        this.is_admin = is_admin;
        this.position = position;
    }

    public boolean isIs_visible() {
        return is_visible;
    }

    public void setIs_visible(boolean is_visible) {
        this.is_visible = is_visible;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
