package com.kollus.demo.eduwillvod.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kollus.jwt.OneFilter;
import com.kollus.jwt.ZeroFilter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DrmCallbackResDataVO {


    //1, 2, 3
    private int kind;
    //Kollus 컨텐츠 Unique Key
    private String media_content_key;
    //    만료될 시간의 unixtime stamp
//    최대값 : 2029년 12월 31일 23시 59분 59초 (1893455999)
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = ZeroFilter.class)
    private long expiration_date;
    // 재생 제한 횟수, 예) 10 ← 10번 재생 가능
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = ZeroFilter.class)
    private int expiration_count;
    //    재생 시간 제한, 예) 60 ← 60초 재생 가능
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = ZeroFilter.class)
    private long expiration_playtime;
    //            1이면 play 상태일 때 구동 시간으로 제한
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = ZeroFilter.class)
    private int expiration_playtime_type;
    //0 (비정상), 1 (정상)
//    0 인 경우 다운로드 되지 않습니다.
//0 인 경우 재 요청되지 않습니다.
    private int result;
    // 0 (비정상)의 경우 message를 추가하면 상황에 따른 메시지가 표시됩니다.
    //0 (비정상)의 경우나 content_delete이 1(다운로드 받은 파일 삭제)시 message를 추가하면 상황에 따른 메시지가 표시됩니다.
    private String message;

    //    만료후 갱신 여부 팝업 표시
//0 인 경우 표시하지 않음 (기본값)
//1 인 경우 DRM 만료후 갱신이 필요할때 사용자 확인을 받는
//    팝업을 표시합니다.
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = ZeroFilter.class)
    private int expiration_refresh_popup;

    //    virtual machine 체크 여부 판단, PC용
//0 : check 안 함, 1 : check 함(기본값)
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = OneFilter.class)
    private int vmcheck;

    //    DRM kind3 항상 물어보기(0 : 안함(기본값), 1: 체크)
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = ZeroFilter.class)
    private int check_abuse;

    //download
//    오프라인일 때 기존 북마크 다운로드 사용 여부
//    컨텐츠 다운로드 시점의 데이터만 다운로드되며, 이후 서버와 싱크처리 되지 않습니다.
//0 : 안함(기본값) 1 : 인덱스만 다운로드
    //readonly
//    오프라인일 때 추가/삭제 사용 여부(0 : 사용(기본값), 1: 안함)
    private OfflineBookmarkVO offline_bookmark;
    //0 (삭제하지 않음), 1 (다운로드 받은 파일 삭제)
//    다운로드된 컨텐츠를 요청에 의해 삭제하는 옵션입니다.
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = ZeroFilter.class)
    private int content_delete;
    //사용안함(0), 체크 유효 만료될 시간의 unixtime stamp
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = ZeroFilter.class)
    private int check_expiration_date;

    //content_expire_reset 요청시 session_key 확인합니다.
    private String session_key;

    //Request에 포함된 start_at
    private long start_at;
    //0 (재생가능), 1 (재생 제한)
//    다운로드 컨텐츠를 강제로 expire 시킵니다.
//    필요시 content_expire_reset 옵션으로 다시 복구 할 수 있습니다.
//            * expired 컨텐츠는 0(재생가능)으로 응답해도 재생되지 않습니다.
//            * 1인 경우 content_expire_reset 무시됩니다.
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = ZeroFilter.class)
    private int content_expired;
    //0 (추가 액션 없음), 1 (expired된 콘텐츠 권한 Reset)
//    expired된 콘텐츠 권한을 Reset 하는 옵션입니다.
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = ZeroFilter.class)
    private int content_expire_reset;

    public DrmCallbackResDataVO(){}

    public DrmCallbackResDataVO(int kind, String media_content_key, long expiration_date, int expiration_count, long expiration_playtime, int expiration_playtime_type, int result, String message, int expiration_refresh_popup, int vmcheck, int check_abuse, OfflineBookmarkVO offline_bookmark, int content_delete, int check_expiration_date, String session_key, long start_at, int content_expired, int content_expire_reset) {
        this.kind = kind;
        this.media_content_key = media_content_key;
        this.expiration_date = expiration_date;
        this.expiration_count = expiration_count;
        this.expiration_playtime = expiration_playtime;
        this.expiration_playtime_type = expiration_playtime_type;
        this.result = result;
        this.message = message;
        this.expiration_refresh_popup = expiration_refresh_popup;
        this.vmcheck = vmcheck;
        this.check_abuse = check_abuse;
        this.offline_bookmark = offline_bookmark;
        this.content_delete = content_delete;
        this.check_expiration_date = check_expiration_date;
        this.session_key = session_key;
        this.start_at = start_at;
        this.content_expired = content_expired;
        this.content_expire_reset = content_expire_reset;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
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

    public int getExpiration_count() {
        return expiration_count;
    }

    public void setExpiration_count(int expiration_count) {
        this.expiration_count = expiration_count;
    }

    public long getExpiration_playtime() {
        return expiration_playtime;
    }

    public void setExpiration_playtime(long expiration_playtime) {
        this.expiration_playtime = expiration_playtime;
    }

    public int getExpiration_playtime_type() {
        return expiration_playtime_type;
    }

    public void setExpiration_playtime_type(int expiration_playtime_type) {
        this.expiration_playtime_type = expiration_playtime_type;
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

    public int getExpiration_refresh_popup() {
        return expiration_refresh_popup;
    }

    public void setExpiration_refresh_popup(int expiration_refresh_popup) {
        this.expiration_refresh_popup = expiration_refresh_popup;
    }

    public int getVmcheck() {
        return vmcheck;
    }

    public void setVmcheck(int vmcheck) {
        this.vmcheck = vmcheck;
    }

    public int getCheck_abuse() {
        return check_abuse;
    }

    public void setCheck_abuse(int check_abuse) {
        this.check_abuse = check_abuse;
    }

    public OfflineBookmarkVO getOffline_bookmark() {
        return offline_bookmark;
    }

    public void setOffline_bookmark(OfflineBookmarkVO offline_bookmark) {
        this.offline_bookmark = offline_bookmark;
    }

    public int getContent_delete() {
        return content_delete;
    }

    public void setContent_delete(int content_delete) {
        this.content_delete = content_delete;
    }

    public int getCheck_expiration_date() {
        return check_expiration_date;
    }

    public void setCheck_expiration_date(int check_expiration_date) {
        this.check_expiration_date = check_expiration_date;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public long getStart_at() {
        return start_at;
    }

    public void setStart_at(long start_at) {
        this.start_at = start_at;
    }

    public int getContent_expired() {
        return content_expired;
    }

    public void setContent_expired(int content_expired) {
        this.content_expired = content_expired;
    }

    public int getContent_expire_reset() {
        return content_expire_reset;
    }

    public void setContent_expire_reset(int content_expire_reset) {
        this.content_expire_reset = content_expire_reset;
    }

    @Override
    public String toString() {
        return "DrmCallbackResDataVO{" +
                "kind=" + kind +
                ", media_content_key='" + media_content_key + '\'' +
                ", expiration_date=" + expiration_date +
                ", expiration_count=" + expiration_count +
                ", expiration_playtime=" + expiration_playtime +
                ", expiration_playtime_type=" + expiration_playtime_type +
                ", result=" + result +
                ", message='" + message + '\'' +
                ", expiration_refresh_popup=" + expiration_refresh_popup +
                ", vmcheck=" + vmcheck +
                ", check_abuse=" + check_abuse +
                ", offline_bookmark=" + offline_bookmark +
                ", content_delete=" + content_delete +
                ", check_expiration_date=" + check_expiration_date +
                ", session_key='" + session_key + '\'' +
                ", start_at=" + start_at +
                ", content_expired=" + content_expired +
                ", content_expire_reset=" + content_expire_reset +
                '}';
    }
}
