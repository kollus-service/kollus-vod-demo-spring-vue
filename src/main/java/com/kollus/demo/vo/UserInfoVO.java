package com.kollus.demo.vo;

public class UserInfoVO {
    private String content_provider_key;// : 고객사 key
    private String client_user_id;// : 사용자(고객) ID
    private String player_id;// : 플레이어(player) ID
    private String hardware_id;// : 플레이어(player) hardware ID, 고객 확인용
    private String host_name;// : 비디오 링크 요청 도메인명
    private String device;// : 디바이스명

    public UserInfoVO(){}

    public UserInfoVO(String content_provider_key, String client_user_id, String player_id, String hardware_id, String host_name, String device) {
        this.content_provider_key = content_provider_key;
        this.client_user_id = client_user_id;
        this.player_id = player_id;
        this.hardware_id = hardware_id;
        this.host_name = host_name;
        this.device = device;
    }

    public String getContent_provider_key() {
        return content_provider_key;
    }

    public void setContent_provider_key(String content_provider_key) {
        this.content_provider_key = content_provider_key;
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

    public String getHost_name() {
        return host_name;
    }

    public void setHost_name(String host_name) {
        this.host_name = host_name;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    @Override
    public String toString() {
        return "UserInfoVO{" +
                "content_provider_key='" + content_provider_key + '\'' +
                ", client_user_id='" + client_user_id + '\'' +
                ", player_id='" + player_id + '\'' +
                ", hardware_id='" + hardware_id + '\'' +
                ", host_name='" + host_name + '\'' +
                ", device='" + device + '\'' +
                '}';
    }
}
