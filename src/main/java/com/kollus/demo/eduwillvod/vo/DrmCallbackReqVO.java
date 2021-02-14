package com.kollus.demo.eduwillvod.vo;

import java.util.HashMap;

public class DrmCallbackReqVO {


    private String kind;//	1,2,3
    private String client_user_id;//	고객사 사용자 ID, media_token 생성시 사용된 client_user_id와 동일합니다.
    private String player_id;//	고객사 사용자가 가지고 있는 단말의 아이디
    private String hardware_id;//	단말의 hardware 아이디(PC, 입력값이 있으면)
    private String device_name;//	고객사 사용자가 가지고 있는 단말의 모델명
    private String media_content_key;//	Kollus 컨텐츠 unique key
    private HashMap<String, Object> uservalues;//	JSON format (VideoGateway 호출시 사용된 uservalue0~9)
    private String localtime;//	단말의 UTC Time
    private String start_at;//	unixtimestamp (localtime)- 전송 요청 시간
    private String content_expired;//	만료된 컨텐츠 확인 flag( 1 : 만료, 0 : 재생가능)
    private String check_expired;//	체크 유효 시간 만료된 컨텐츠 확인 flag( 1 : 만료, 0 : 재생가능)
    private String reset_req;//	일괄갱신 요청인지 판단( 0 (default), 1 : 일괄갱신)
    private String expiration_date;//	만료 날짜(unixtimestamp)
    private String session_key;
    public DrmCallbackReqVO() {
    }

    public DrmCallbackReqVO(String kind, String client_user_id, String player_id, String hardware_id, String device_name, String media_content_key, HashMap<String, Object> uservalues, String localtime, String start_at, String content_expired, String check_expired, String reset_req, String expiration_date, String session_key) {
        this.kind = kind;
        this.client_user_id = client_user_id;
        this.player_id = player_id;
        this.hardware_id = hardware_id;
        this.device_name = device_name;
        this.media_content_key = media_content_key;
        this.uservalues = uservalues;
        this.localtime = localtime;
        this.start_at = start_at;
        this.content_expired = content_expired;
        this.check_expired = check_expired;
        this.reset_req = reset_req;
        this.expiration_date = expiration_date;
        this.session_key = session_key;
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

    public String getLocaltime() {
        return localtime;
    }

    public void setLocaltime(String localtime) {
        this.localtime = localtime;
    }

    public String getStart_at() {
        return start_at;
    }

    public void setStart_at(String start_at) {
        this.start_at = start_at;
    }

    public String getContent_expired() {
        return content_expired;
    }

    public void setContent_expired(String content_expired) {
        this.content_expired = content_expired;
    }

    public String getCheck_expired() {
        return check_expired;
    }

    public void setCheck_expired(String check_expired) {
        this.check_expired = check_expired;
    }

    public String getReset_req() {
        return reset_req;
    }

    public void setReset_req(String reset_req) {
        this.reset_req = reset_req;
    }

    public String getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    @Override
    public String toString() {
        return "DrmCallbackReqVO{" +
                "kind='" + kind + '\'' +
                ", client_user_id='" + client_user_id + '\'' +
                ", player_id='" + player_id + '\'' +
                ", hardware_id='" + hardware_id + '\'' +
                ", device_name='" + device_name + '\'' +
                ", media_content_key='" + media_content_key + '\'' +
                ", uservalues=" + uservalues +
                ", localtime='" + localtime + '\'' +
                ", start_at='" + start_at + '\'' +
                ", content_expired='" + content_expired + '\'' +
                ", check_expired='" + check_expired + '\'' +
                ", reset_req='" + reset_req + '\'' +
                ", expiration_date='" + expiration_date + '\'' +
                ", session_key='" + session_key + '\'' +
                '}';
    }
}
