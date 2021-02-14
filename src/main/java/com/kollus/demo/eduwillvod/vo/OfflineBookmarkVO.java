package com.kollus.demo.eduwillvod.vo;

public class OfflineBookmarkVO {
    private int download;
    private int readonly;

    public OfflineBookmarkVO(){}
    public OfflineBookmarkVO(int download, int readonly) {
        this.download = download;
        this.readonly = readonly;
    }

    public int getDownload() {
        return download;
    }

    public void setDownload(int download) {
        this.download = download;
    }

    public int getReadonly() {
        return readonly;
    }

    public void setReadonly(int readonly) {
        this.readonly = readonly;
    }

    @Override
    public String toString() {
        return "OfflineBookmarkVO{" +
                "download=" + download +
                ", readonly=" + readonly +
                '}';
    }
}
