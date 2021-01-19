package com.kollus.demo.model;

import javax.persistence.*;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"START_AT", "CUID", "MEDIA_CONTENT_KEY"})
        }
)
public class LmsData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column private long start_at;
    @Column private String cuid;
    @Column private String media_content_key;
    @Column private String device;
    @Column private long play_time;
    @Column private long last_play_at;

    public LmsData() {
    }

    public LmsData(long id, long start_at, String cuid, String media_content_key, String device, long play_time, long last_play_at) {
        this.id = id;
        this.start_at = start_at;
        this.cuid = cuid;
        this.media_content_key = media_content_key;
        this.device = device;
        this.play_time = play_time;
        this.last_play_at = last_play_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStart_at() {
        return start_at;
    }

    public void setStart_at(long start_at) {
        this.start_at = start_at;
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

    public String getDevice() {
        return device;
    }

    public void setDevice(String deivce) {
        this.device = deivce;
    }

    public long getPlay_time() {
        return play_time;
    }

    public void setPlay_time(long play_time) {
        this.play_time = play_time;
    }

    public long getLast_play_at() {
        return last_play_at;
    }

    public void setLast_play_at(long last_play_at) {
        this.last_play_at = last_play_at;
    }

    @Override
    public String toString() {
        return "LmsData{" +
                "id=" + id +
                ", start_at=" + start_at +
                ", cuid='" + cuid + '\'' +
                ", media_content_key='" + media_content_key + '\'' +
                ", device='" + device + '\'' +
                ", play_time=" + play_time +
                ", last_play_at=" + last_play_at +
                '}';
    }
}
