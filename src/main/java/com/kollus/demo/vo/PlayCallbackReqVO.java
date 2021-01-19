package com.kollus.demo.vo;

import java.util.HashMap;

public class PlayCallbackReqVO {

    private String kind;//1
    private String client_user_id;//	고객사 사용자 ID, media_token 생성시 사용된 client_user_id와 동일합니다.
    private String player_id;//	고객사 사용자가 가지고 있는 단말의 아이디
    private String hardware_id;//	단말의 hardware 아이디(PC, 입력값이 있으면)
    private String device_name;//	고객사 사용자가 가지고 있는 단말의 모델명
    private String media_content_key;//	Kollus 컨텐츠 unique key
    private HashMap<String, Object> uservalues;//	JSON format (VideoGateway 호출시 사용된 uservalue0~9)

    public PlayCallbackReqVO(){}

    public PlayCallbackReqVO(String kind, String client_user_id, String player_id, String hardware_id, String device_name, String media_content_key, HashMap<String, Object> uservalues) {
        this.kind = kind;
        this.client_user_id = client_user_id;
        this.player_id = player_id;
        this.hardware_id = hardware_id;
        this.device_name = device_name;
        this.media_content_key = media_content_key;
        this.uservalues = uservalues;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getClient_user_id() {
        return client_user_id;
    }

    public void setClient_user_id(String client_user_id) {
        this.client_user_id = client_user_id;
    }

    public String getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(String player_id) {
        this.player_id = player_id;
    }

    public String getHardware_id() {
        return hardware_id;
    }

    public void setHardware_id(String hardware_id) {
        this.hardware_id = hardware_id;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getMedia_content_key() {
        return media_content_key;
    }

    public void setMedia_content_key(String media_content_key) {
        this.media_content_key = media_content_key;
    }

    public HashMap<String, Object> getUservalues() {
        return uservalues;
    }

    public void setUservalues(HashMap<String, Object> uservalues) {
        this.uservalues = uservalues;
    }

    @Override
    public String toString() {
        return "PlayCallbackReqVO{" +
                "kind='" + kind + '\'' +
                ", client_user_id='" + client_user_id + '\'' +
                ", player_id='" + player_id + '\'' +
                ", hardware_id='" + hardware_id + '\'' +
                ", device_name='" + device_name + '\'' +
                ", media_content_key='" + media_content_key + '\'' +
                ", uservalues='" + uservalues + '\'' +
                '}';
    }
}
