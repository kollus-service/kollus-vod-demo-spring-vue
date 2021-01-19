package com.kollus.demo.model;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(
                columnNames = {"CHANNEL_KEY", "CUID", "MEDIA_CONTENT_KEY"}
        )
})
public class PlayCallbackPolicy {


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
    private long expiration_date=-1;
    @Column
    private int result = 1;
    @Column
    private String message;
    @Column
    private int vmcheck = 1;
    @Column
    private long start_time = -1;
    @Column
    private long end_time = -1;
    @Column
    private int disable_tvout = 1;
    @Column
    private long expiration_playtime = -1;
    @Column
    private int content_expired = 0;

    public PlayCallbackPolicy() {
    }

    public PlayCallbackPolicy(String channel_key,
                              String cuid, String media_content_key,
                              long expiration_date, int result, String message, int vmcheck, long start_time,
                              long end_time, int disable_tvout, long expiration_playtime, int content_expired) {

        this.channel_key = channel_key;
        this.cuid = cuid;
        this.media_content_key = media_content_key;
        this.expiration_date = expiration_date;
        this.result = result;
        this.message = message;
        this.vmcheck = vmcheck;
        this.start_time = start_time;
        this.end_time = end_time;
        this.disable_tvout = disable_tvout;
        this.expiration_playtime = expiration_playtime;
        this.content_expired = content_expired;
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

    public int getVmcheck() {
        return vmcheck;
    }

    public void setVmcheck(int vmcheck) {
        this.vmcheck = vmcheck;
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

    public int getDisable_tvout() {
        return disable_tvout;
    }

    public void setDisable_tvout(int disable_tvout) {
        this.disable_tvout = disable_tvout;
    }

    public long getExpiration_playtime() {
        return expiration_playtime;
    }

    public void setExpiration_playtime(long expiration_playtime) {
        this.expiration_playtime = expiration_playtime;
    }

    public int getContent_expired() {
        return content_expired;
    }

    public void setContent_expired(int content_expired) {
        this.content_expired = content_expired;
    }

    @Override
    public String toString() {
        return "PlayCallbackPolicy{" +
                "channel_key='" + channel_key + '\'' +
                ", cuid='" + cuid + '\'' +
                ", media_content_key='" + media_content_key + '\'' +
                ", expiration_date=" + expiration_date +
                ", result=" + result +
                ", message='" + message + '\'' +
                ", vmcheck=" + vmcheck +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", disable_tvout=" + disable_tvout +
                ", expiration_playtime=" + expiration_playtime +
                ", content_expired=" + content_expired +
                '}';
    }
}
