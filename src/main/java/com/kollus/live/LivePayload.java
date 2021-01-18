package com.kollus.live;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.kollus.jwt.Payload;
import com.kollus.vod.drm.VideoWatermarkingCodePolicy;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LivePayload extends Payload {
    private String client_user_id;
    private String client_user_name;
    private VideoWatermarkingCodePolicy video_watermarking_code_policy;
    private String client_user_image;
    private long expire_time;
    private long play_expt;
    private String live_media_channel_key;
    private String live_media_profile_key;
    private String player_version;
    private String title;
    private ChattingPolicy chatting_policy;

    public LivePayload() {

    }


    public String getClient_user_id() {
        return client_user_id;
    }

    public void setClient_user_id(String client_user_id) {
        this.client_user_id = client_user_id;
    }

    public String getClient_user_name() {
        return client_user_name;
    }

    public void setClient_user_name(String client_user_name) {
        this.client_user_name = client_user_name;
    }

    public VideoWatermarkingCodePolicy getVideo_watermarking_code_policy() {
        return video_watermarking_code_policy;
    }

    public void setVideo_watermarking_code_policy(VideoWatermarkingCodePolicy video_watermarking_code_policy) {
        this.video_watermarking_code_policy = video_watermarking_code_policy;
    }

    public String getClient_user_image() {
        return client_user_image;
    }

    public void setClient_user_image(String client_user_image) {
        this.client_user_image = client_user_image;
    }

    public long getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(long expire_time) {
        this.expire_time = expire_time;
    }

    public long getPlay_expt() {
        return play_expt;
    }

    public void setPlay_expt(long play_expt) {
        this.play_expt = play_expt;
    }

    public String getLive_media_channel_key() {
        return live_media_channel_key;
    }

    public void setLive_media_channel_key(String live_media_channel_key) {
        this.live_media_channel_key = live_media_channel_key;
    }

    public String getLive_media_profile_key() {
        return live_media_profile_key;
    }

    public void setLive_media_profile_key(String live_media_profile_key) {
        this.live_media_profile_key = live_media_profile_key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ChattingPolicy getChatting_policy() {
        return chatting_policy;
    }

    public void setChatting_policy(ChattingPolicy chatting_policy) {
        this.chatting_policy = chatting_policy;
    }

    public String getPlayer_version() {
        return player_version;
    }

    public void setPlayer_version(String player_version) {
        this.player_version = player_version;
    }
}
