package com.kollus.demo.vo;

public class UploadReqVO {
    private String category_key;
    private int expire_time;
    private int is_encryption_upload;
    private int is_audio_upload;
    private int is_passthrough;
    private String profile_key;
    private String title;

    public UploadReqVO(String category_key, int expire_time, int is_encryption_upload, int is_audio_upload, int is_passthrough, String profile_key, String title) {
        this.category_key = category_key;
        this.expire_time = expire_time;
        this.is_encryption_upload = is_encryption_upload;
        this.is_audio_upload = is_audio_upload;
        this.is_passthrough = is_passthrough;
        this.profile_key = profile_key;
        this.title = title;
    }

    public String getCategory_key() {
        return category_key;
    }

    public void setCategory_key(String category_key) {
        this.category_key = category_key;
    }

    public int getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(int expire_time) {
        this.expire_time = expire_time;
    }

    public int getIs_encryption_upload() {
        return is_encryption_upload;
    }

    public void setIs_encryption_upload(int is_encryption_upload) {
        this.is_encryption_upload = is_encryption_upload;
    }

    public int getIs_audio_upload() {
        return is_audio_upload;
    }

    public void setIs_audio_upload(int is_audio_upload) {
        this.is_audio_upload = is_audio_upload;
    }

    public int getIs_passthrough() {
        return is_passthrough;
    }

    public void setIs_passthrough(int is_passthrough) {
        this.is_passthrough = is_passthrough;
    }

    public String getProfile_key() {
        return profile_key;
    }

    public void setProfile_key(String profile_key) {
        this.profile_key = profile_key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "UploadReqVO{" +
                "category_key='" + category_key + '\'' +
                ", expire_time=" + expire_time +
                ", is_encryption_upload=" + is_encryption_upload +
                ", is_audio_upload=" + is_audio_upload +
                ", is_passthrough=" + is_passthrough +
                ", profile_key='" + profile_key + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
