package com.kollus.demo.vo;

public class PlayerReqVO {
    private String channel_key;
    private String mck;
    private String cuid;

    public PlayerReqVO() {
    }

    public PlayerReqVO(String channel_key, String mck, String cuid) {
        this.channel_key = channel_key;
        this.mck = mck;
        this.cuid = cuid;
    }

    public String getChannel_key() {
        return channel_key;
    }

    public void setChannel_key(String channel_key) {
        this.channel_key = channel_key;
    }

    public String getMck() {
        return mck;
    }

    public void setMck(String mck) {
        this.mck = mck;
    }

    public String getCuid() {
        return cuid;
    }

    public void setCuid(String cuid) {
        this.cuid = cuid;
    }

    @Override
    public String toString() {
        return "PlayerReqVO{" +
                "channel_key='" + channel_key + '\'' +
                ", mck='" + mck + '\'' +
                ", cuid='" + cuid + '\'' +
                '}';
    }
}
