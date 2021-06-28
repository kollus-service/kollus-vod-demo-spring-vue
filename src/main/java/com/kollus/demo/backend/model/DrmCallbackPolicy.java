package com.kollus.demo.eduwillvod.model;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(
                columnNames = {"CHANNEL_KEY", "CUID", "MEDIA_CONTENT_KEY"}
        )
})
public class DrmCallbackPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String channel_key;
    @Column
    private String cuid;
    @Column
    private String media_content_key;
    @Column
    private long expiration_date = -1;
    @Column private long expiration_count = -1;
    @Column private long expiration_playtime = -1;
    @Column private int expiration_playtime_type = 0 ;
    @Column private int result =1;
    @Column private String message;
    @Column private int expiration_refresh_popup = 1;
    @Column private int vmcheck = 1;
    @Column private int check_abuse = 1;
    @Column private int bookmark_download = 0;
    @Column private int bookmark_readonly = 0;
    @Column private int content_delete = 0;
    @Column private long check_expiration_date = -1;
    @Column private int content_expired = 0;
    @Column private int content_expire_reset = 1;


    public DrmCallbackPolicy(){}
    public DrmCallbackPolicy(String channel_key,
                             String cuid, String media_content_key,
                             long expiration_date, long expiration_count, long expiration_playtime,
                             int expiration_playtime_type, int result, String message, int expiration_refresh_popup,
                             int vmcheck, int check_abuse, int bookmark_download, int bookmark_readonly,
                             int content_delete, long check_expiration_date, int content_expired,
                             int content_expire_reset) {
        this.channel_key = channel_key;
        this.cuid = cuid;
        this.media_content_key = media_content_key;
        this.expiration_date = expiration_date;
        this.expiration_count = expiration_count;
        this.expiration_playtime = expiration_playtime;
        this.expiration_playtime_type = expiration_playtime_type;
        this.result = result;
        this.message = message;
        this.expiration_refresh_popup = expiration_refresh_popup;
        this.vmcheck = vmcheck;
        this.check_abuse = check_abuse;
        this.bookmark_download = bookmark_download;
        this.bookmark_readonly = bookmark_readonly;
        this.content_delete = content_delete;
        this.check_expiration_date = check_expiration_date;
        this.content_expired = content_expired;
        this.content_expire_reset = content_expire_reset;
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

    public String getMedia_content_key() {
        return media_content_key;
    }

    public void setMedia_content_key(String media_content_key) {
        this.media_content_key = media_content_key;
    }

    public long getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(long expiration_date) {
        this.expiration_date = expiration_date;
    }

    public long getExpiration_count() {
        return expiration_count;
    }

    public void setExpiration_count(long expiration_count) {
        this.expiration_count = expiration_count;
    }

    public long getExpiration_playtime() {
        return expiration_playtime;
    }

    public void setExpiration_playtime(long expiration_playtime) {
        this.expiration_playtime = expiration_playtime;
    }

    public int getExpiration_playtime_type() {
        return expiration_playtime_type;
    }

    public void setExpiration_playtime_type(int expiration_playtime_type) {
        this.expiration_playtime_type = expiration_playtime_type;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getExpiration_refresh_popup() {
        return expiration_refresh_popup;
    }

    public void setExpiration_refresh_popup(int expiration_refresh_popup) {
        this.expiration_refresh_popup = expiration_refresh_popup;
    }

    public int getVmcheck() {
        return vmcheck;
    }

    public void setVmcheck(int vmcheck) {
        this.vmcheck = vmcheck;
    }

    public int getCheck_abuse() {
        return check_abuse;
    }

    public void setCheck_abuse(int check_abuse) {
        this.check_abuse = check_abuse;
    }

    public int getBookmark_download() {
        return bookmark_download;
    }

    public void setBookmark_download(int bookmark_download) {
        this.bookmark_download = bookmark_download;
    }

    public int getBookmark_readonly() {
        return bookmark_readonly;
    }

    public void setBookmark_readonly(int bookmark_readonly) {
        this.bookmark_readonly = bookmark_readonly;
    }

    public int getContent_delete() {
        return content_delete;
    }

    public void setContent_delete(int content_delete) {
        this.content_delete = content_delete;
    }

    public long getCheck_expiration_date() {
        return check_expiration_date;
    }

    public void setCheck_expiration_date(long check_expiration_date) {
        this.check_expiration_date = check_expiration_date;
    }

    public int getContent_expired() {
        return content_expired;
    }

    public void setContent_expired(int content_expired) {
        this.content_expired = content_expired;
    }

    public int getContent_expire_reset() {
        return content_expire_reset;
    }

    public void setContent_expire_reset(int content_expire_reset) {
        this.content_expire_reset = content_expire_reset;
    }

    @Override
    public String toString() {
        return "DrmCallbackPolicy{" +
                "channel_key='" + channel_key + '\'' +
                ", cuid='" + cuid + '\'' +
                ", media_content_key='" + media_content_key + '\'' +
                ", expiration_date=" + expiration_date +
                ", expiration_count=" + expiration_count +
                ", expiration_playtime=" + expiration_playtime +
                ", expiration_playtime_type=" + expiration_playtime_type +
                ", result=" + result +
                ", message='" + message + '\'' +
                ", expiration_refresh_popup=" + expiration_refresh_popup +
                ", vmcheck=" + vmcheck +
                ", check_abuse=" + check_abuse +
                ", bookmark_download=" + bookmark_download +
                ", bookmark_readonly=" + bookmark_readonly +
                ", content_delete=" + content_delete +
                ", check_expiration_date=" + check_expiration_date +
                ", content_expired=" + content_expired +
                ", content_expire_reset=" + content_expire_reset +
                '}';
    }
}
