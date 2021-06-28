package com.kollus.demo.eduwillvod.vo;

public class DownloadReqVO {

    private String channel_key;
    private String cuid;
    private String mck;
    private String folder;
    private String title;

    public DownloadReqVO() {
    }

    public DownloadReqVO(String channel_key, String cuid, String mck, String folder, String title) {
        this.channel_key = channel_key;
        this.cuid = cuid;
        this.mck = mck;
        this.folder = folder;
        this.title = title;
    }

    public String getChannel_key() {
        return channel_key;
    }

    public void setChannel_key(String channel_key) {
        this.channel_key = channel_key;
    }

    public String getCuid() {
        return cuid;
    }

    public void setCuid(String cuid) {
        this.cuid = cuid;
    }

    public String getMck() {
        return mck;
    }

    public void setMck(String mck) {
        this.mck = mck;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "DownloadReqVO{" +
                "channel_key='" + channel_key + '\'' +
                ", cuid='" + cuid + '\'' +
                ", mck='" + mck + '\'' +
                ", folder='" + folder + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
