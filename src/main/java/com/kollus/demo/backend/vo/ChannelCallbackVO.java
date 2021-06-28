package com.kollus.demo.eduwillvod.vo;

public class ChannelCallbackVO {

    private String content_provider_key;//	string	고객사의 서비스 계정 키 입니다.
    private String full_filename;//	string	폴더를 포함한 업로드 된 파일명입니다.
    private String filename;//	string	업로드 된 파일명입니다.
    private String upload_file_key;//	string	업로드 파일 키
    private String media_content_key;//	string	미디어 컨텐츠 키. 동영상 재생을 위해 채널에 할당된 컨텐츠를 식별하기 위한 키.
    private String channel_key;//	string	콘텐츠가 할당된 채널의 식별 키
    private String channel_name;//	string	콘텐츠가 할당된 채널의 이름
    private String profile_key;//	string	콘텐츠가 트랜스코딩된 프로파일 명. 하나 이상일 경우는 ‘|’를 구분자로 하여 표현합니다.
    private String update_type;//	string	업데이트 종류

    public ChannelCallbackVO() {
    }

    public ChannelCallbackVO(String content_provider_key, String full_filename, String filename, String upload_file_key, String media_content_key, String channel_key, String channel_name, String profile_key, String update_type) {
        this.content_provider_key = content_provider_key;
        this.full_filename = full_filename;
        this.filename = filename;
        this.upload_file_key = upload_file_key;
        this.media_content_key = media_content_key;
        this.channel_key = channel_key;
        this.channel_name = channel_name;
        this.profile_key = profile_key;
        this.update_type = update_type;
    }

    public String getContent_provider_key() {
        return content_provider_key;
    }

    public void setContent_provider_key(String content_provider_key) {
        this.content_provider_key = content_provider_key;
    }

    public String getFull_filename() {
        return full_filename;
    }

    public void setFull_filename(String full_filename) {
        this.full_filename = full_filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUpload_file_key() {
        return upload_file_key;
    }

    public void setUpload_file_key(String upload_file_key) {
        this.upload_file_key = upload_file_key;
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

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    public String getProfile_key() {
        return profile_key;
    }

    public void setProfile_key(String profile_key) {
        this.profile_key = profile_key;
    }

    public String getUpdate_type() {
        return update_type;
    }

    public void setUpdate_type(String update_type) {
        this.update_type = update_type;
    }

    @Override
    public String toString() {
        return "ChannelCallbackVO{" +
                "content_provider_key='" + content_provider_key + '\'' +
                ", full_filename='" + full_filename + '\'' +
                ", filename='" + filename + '\'' +
                ", upload_file_key='" + upload_file_key + '\'' +
                ", media_content_key='" + media_content_key + '\'' +
                ", channel_key='" + channel_key + '\'' +
                ", channel_name='" + channel_name + '\'' +
                ", profile_key='" + profile_key + '\'' +
                ", update_type='" + update_type + '\'' +
                '}';
    }
}
