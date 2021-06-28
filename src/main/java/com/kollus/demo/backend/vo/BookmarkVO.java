package com.kollus.demo.eduwillvod.vo;

public class BookmarkVO {
    private String upload_file_key;//	string	콘텐츠 업로드시 생성되는 키 (Unique)
    private String media_content_key;//	string
    private String client_user_id;//	string	사용자 아이디
    private long position;//	integer	북마크 위치
    private long localtime;//	integer	북마크 생성 시간 (참조값: 사용자 system time)
    private String value;//	string	북마크 제목 ('&' 문자 제외)
    private String label;//	string	북마크 리스트 제목
    private String uservalue0;//	string	User Value
    private String uservalue1;
    private String uservalue2;
    private String uservalue3;
    private String uservalue4;
    private String uservalue5;
    private String uservalue6;
    private String uservalue7;
    private String uservalue8;
    private String uservalue9;

    public BookmarkVO(){}

    public BookmarkVO(String upload_file_key, String media_content_key, String client_user_id, long position, long localtime, String value, String label, String uservalue0, String uservalue1, String uservalue2, String uservalue3, String uservalue4, String uservalue5, String uservalue6, String uservalue7, String uservalue8, String uservalue9) {
        this.upload_file_key = upload_file_key;
        this.media_content_key = media_content_key;
        this.client_user_id = client_user_id;
        this.position = position;
        this.localtime = localtime;
        this.value = value;
        this.label = label;
        this.uservalue0 = uservalue0;
        this.uservalue1 = uservalue1;
        this.uservalue2 = uservalue2;
        this.uservalue3 = uservalue3;
        this.uservalue4 = uservalue4;
        this.uservalue5 = uservalue5;
        this.uservalue6 = uservalue6;
        this.uservalue7 = uservalue7;
        this.uservalue8 = uservalue8;
        this.uservalue9 = uservalue9;
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

    public String getClient_user_id() {
        return client_user_id;
    }

    public void setClient_user_id(String client_user_id) {
        this.client_user_id = client_user_id;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public long getLocaltime() {
        return localtime;
    }

    public void setLocaltime(long localtime) {
        this.localtime = localtime;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUservalue0() {
        return uservalue0;
    }

    public void setUservalue0(String uservalue0) {
        this.uservalue0 = uservalue0;
    }

    public String getUservalue1() {
        return uservalue1;
    }

    public void setUservalue1(String uservalue1) {
        this.uservalue1 = uservalue1;
    }

    public String getUservalue2() {
        return uservalue2;
    }

    public void setUservalue2(String uservalue2) {
        this.uservalue2 = uservalue2;
    }

    public String getUservalue3() {
        return uservalue3;
    }

    public void setUservalue3(String uservalue3) {
        this.uservalue3 = uservalue3;
    }

    public String getUservalue4() {
        return uservalue4;
    }

    public void setUservalue4(String uservalue4) {
        this.uservalue4 = uservalue4;
    }

    public String getUservalue5() {
        return uservalue5;
    }

    public void setUservalue5(String uservalue5) {
        this.uservalue5 = uservalue5;
    }

    public String getUservalue6() {
        return uservalue6;
    }

    public void setUservalue6(String uservalue6) {
        this.uservalue6 = uservalue6;
    }

    public String getUservalue7() {
        return uservalue7;
    }

    public void setUservalue7(String uservalue7) {
        this.uservalue7 = uservalue7;
    }

    public String getUservalue8() {
        return uservalue8;
    }

    public void setUservalue8(String uservalue8) {
        this.uservalue8 = uservalue8;
    }

    public String getUservalue9() {
        return uservalue9;
    }

    public void setUservalue9(String uservalue9) {
        this.uservalue9 = uservalue9;
    }

    @Override
    public String toString() {
        return "BookmarkVO{" +
                "upload_file_key='" + upload_file_key + '\'' +
                ", media_content_key='" + media_content_key + '\'' +
                ", client_user_id='" + client_user_id + '\'' +
                ", position=" + position +
                ", localtime=" + localtime +
                ", value='" + value + '\'' +
                ", label='" + label + '\'' +
                ", uservalue0='" + uservalue0 + '\'' +
                ", uservalue1='" + uservalue1 + '\'' +
                ", uservalue2='" + uservalue2 + '\'' +
                ", uservalue3='" + uservalue3 + '\'' +
                ", uservalue4='" + uservalue4 + '\'' +
                ", uservalue5='" + uservalue5 + '\'' +
                ", uservalue6='" + uservalue6 + '\'' +
                ", uservalue7='" + uservalue7 + '\'' +
                ", uservalue8='" + uservalue8 + '\'' +
                ", uservalue9='" + uservalue9 + '\'' +
                '}';
    }
}
