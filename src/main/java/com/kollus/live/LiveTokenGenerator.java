package com.kollus.live;

import com.kollus.jwt.Generator;
import com.kollus.vod.drm.VideoWatermarkingCodePolicy;

public class LiveTokenGenerator extends Generator<LivePayload> {

    public LiveTokenGenerator(){
        this.payload = new LivePayload();
    }
    public LiveTokenGenerator secret_key(String secret_key){
        this.secret_key = secret_key;
        return this;
    }
    public LiveTokenGenerator client_user_id(String client_user_id){
        this.payload.setClient_user_id(client_user_id);
        return this;
    }
    public LiveTokenGenerator client_user_name(String client_user_name){
        this.payload.setClient_user_name(client_user_name);
        return this;
    }
    public LiveTokenGenerator video_watermarking_code_policy(VideoWatermarkingCodePolicy video_watermarking_code_policy){
        this.payload.setVideo_watermarking_code_policy(video_watermarking_code_policy);
        return this;
    }
    public LiveTokenGenerator client_user_image(String client_user_image){
        this.payload.setClient_user_image(client_user_image);
        return this;
    }
    public LiveTokenGenerator expire_time(long expire_time){
        this.payload.setExpire_time(expire_time);
        return this;
    }
    public LiveTokenGenerator play_expt(long play_expt){
        this.payload.setPlay_expt(play_expt);
        return this;
    }
    public LiveTokenGenerator live_media_channel_key(String live_media_channel_key){
        this.payload.setLive_media_channel_key(live_media_channel_key);
        return this;
    }
    public LiveTokenGenerator live_media_profile_key(String live_media_profile_key){
        this.payload.setLive_media_profile_key(live_media_profile_key);
        return this;
    }
    public LiveTokenGenerator title(String title){
        this.payload.setTitle(title);
        return this;
    }
    public LiveTokenGenerator chatting_policy(ChattingPolicy chatting_policy){
        this.payload.setChatting_policy(chatting_policy);
        return this;
    }
    public LiveTokenGenerator player_version(String player_version){
        this.payload.setPlayer_version(player_version);
        return this;
    }

}
