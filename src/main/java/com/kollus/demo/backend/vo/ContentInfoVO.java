package com.kollus.demo.eduwillvod.vo;

public class ContentInfoVO {
    private long duration; // : 컨텐츠 길이
    private String encoding_profile; // : 인코딩 프로파일
    private String media_content_key;// : 미디어 컨텐츠 키
    private String channel_key; // : 채널키
    private long real_playtime;// : 실제 전체 재생 시간 (단위:초) 배속 기능을 사용하는 경우 배속을 포함한 시간입니다. 10초간 2배속으로 재생한 경우 20초로 계산됩니다. 모든 재생 시간을 포함한 시간입니다. (구간반복을 한 경우는 제외됩니다.)
    private long playtime; // : 컨텐츠 재생 시간
    private long playtime_percent; // : duration에 대한 전체 재생 비율
    private long start_at; // : Video-gateway를 호출한 시점의 Unixtimestamp 입니다. 다운로드 컨텐츠의 경우 start_at은 컨텐츠 재생시 단말의 unixtimestamp 입니다.
    private long last_play_at; // : 마지막 재생 위치 (단위:초)
    private long runtime; // : 플레이어 실제 구동 시간(단위:초)
    private long showtime; // :  플레이어를 실제적으로 play 한 구동 시간 (단위:초)

    public ContentInfoVO(){}
    public ContentInfoVO(long duration, String encoding_profile, String media_content_key, String channel_key, long real_playtime, long playtime, long playtime_percent, long start_at, long last_play_at, long runtime, long showtime) {
        this.duration = duration;
        this.encoding_profile = encoding_profile;
        this.media_content_key = media_content_key;
        this.channel_key = channel_key;
        this.real_playtime = real_playtime;
        this.playtime = playtime;
        this.playtime_percent = playtime_percent;
        this.start_at = start_at;
        this.last_play_at = last_play_at;
        this.runtime = runtime;
        this.showtime = showtime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getEncoding_profile() {
        return encoding_profile;
    }

    public void setEncoding_profile(String encoding_profile) {
        this.encoding_profile = encoding_profile;
    }

    public String getMedia_content_key() {
        return media_content_key;
    }

    public void setMedia_content_key(String media_content_key) {
        this.media_content_key = media_content_key;
    }

    public String getChannel_key() {
        return channel_key;
    }

    public void setChannel_key(String channel_key) {
        this.channel_key = channel_key;
    }

    public long getReal_playtime() {
        return real_playtime;
    }

    public void setReal_playtime(long real_playtime) {
        this.real_playtime = real_playtime;
    }

    public long getPlaytime() {
        return playtime;
    }

    public void setPlaytime(long playtime) {
        this.playtime = playtime;
    }

    public long getPlaytime_percent() {
        return playtime_percent;
    }

    public void setPlaytime_percent(long playtime_percent) {
        this.playtime_percent = playtime_percent;
    }

    public long getStart_at() {
        return start_at;
    }

    public void setStart_at(long start_at) {
        this.start_at = start_at;
    }

    public long getLast_play_at() {
        return last_play_at;
    }

    public void setLast_play_at(long last_play_at) {
        this.last_play_at = last_play_at;
    }

    public long getRuntime() {
        return runtime;
    }

    public void setRuntime(long runtime) {
        this.runtime = runtime;
    }

    public long getShowtime() {
        return showtime;
    }

    public void setShowtime(long showtime) {
        this.showtime = showtime;
    }

    @Override
    public String toString() {
        return "ContentInfoVO{" +
                "duration=" + duration +
                ", encoding_profile='" + encoding_profile + '\'' +
                ", media_content_key='" + media_content_key + '\'' +
                ", channel_key='" + channel_key + '\'' +
                ", real_playtime=" + real_playtime +
                ", playtime=" + playtime +
                ", playtime_percent=" + playtime_percent +
                ", start_at=" + start_at +
                ", last_play_at=" + last_play_at +
                ", runtime=" + runtime +
                ", showtime=" + showtime +
                '}';
    }
}
