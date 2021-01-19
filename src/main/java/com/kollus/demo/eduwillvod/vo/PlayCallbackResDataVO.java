package com.kollus.demo.eduwillvod.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kollus.jwt.OneFilter;
import com.kollus.jwt.ZeroFilter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PlayCallbackResDataVO {

    //만료될 시간의 unixtime stamp
    //최대값 : 2029년 12월 31일 23시 59분 59초 (1893455999)
//    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = ZeroFilter.class)
    private long expiration_date;
    //	0 (비정상), 1 (정상)
    private int result;
    //    result == 0 (비정상)의 경우 message를 추가하면 지정 한 메시지가 표출됩니다.
//    PC 플레이의 경우 재생 URL에 loadcheck=0 파라미터를 추가 해 주셔야 지정 한 메시지가 표출됩니다.
//    EX. http://v.kr.kollus.com/{미디어 컨텐츠 키}?loadcheck=0
    private String message;
    //	0 (사용안함), 1 (사용함, default) virtural machine 체크 여부, PC(v3)용에서만 사용 가능
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = OneFilter.class)
    private int vmcheck;
//        start_time,end_time
//    미리 보기 구간 초로 구분(end_time이 start_time 보다 커야 한다)
    private PlaySectionVO play_section;
    //	0 (tvout 차단 안함), 1 (tvout 차단) 이 항목이 없으면 채널에 있는 disable_tvout정책이 적용됩니다.
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = OneFilter.class)
    private int disable_tvout;
    //	이 항목이 없거나 0이면 재생 시간을 사용하지 않고 0보다 크면 해당 값의 초만큼만 재생 후 종료합니다.
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = ZeroFilter.class)
    private long expiration_playtime;
    //    	0 (재생가능), 1 (재생 제한)
//    재생을 차단합니다. DRM 콜백과 동일한 스펙 유지를 위해 동일한 항목으로 유지됩니다.
//    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = ZeroFilter.class)
    private int content_expired;

    public PlayCallbackResDataVO() {
    }

    public PlayCallbackResDataVO(long expiration_date, int result, String message, int vmcheck, PlaySectionVO play_section, int disable_tvout, long expiration_playtime, int content_expired) {
        this.expiration_date = expiration_date;
        this.result = result;
        this.message = message;
        this.vmcheck = vmcheck;
        this.play_section = play_section;
        this.disable_tvout = disable_tvout;
        this.expiration_playtime = expiration_playtime;
        this.content_expired = content_expired;
    }

    public long getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(long expiration_date) {
        this.expiration_date = expiration_date;
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

    public int getVmcheck() {
        return vmcheck;
    }

    public void setVmcheck(int vmcheck) {
        this.vmcheck = vmcheck;
    }

    public PlaySectionVO getPlay_section() {
        return play_section;
    }

    public void setPlay_section(PlaySectionVO play_section) {
        this.play_section = play_section;
    }

    public int getDisable_tvout() {
        return disable_tvout;
    }

    public void setDisable_tvout(int disable_tvout) {
        this.disable_tvout = disable_tvout;
    }

    public long getExpiration_playtime() {
        return expiration_playtime;
    }

    public void setExpiration_playtime(long expiration_playtime) {
        this.expiration_playtime = expiration_playtime;
    }

    public int getContent_expired() {
        return content_expired;
    }

    public void setContent_expired(int content_expired) {
        this.content_expired = content_expired;
    }

    @Override
    public String toString() {
        return "PlayCallbackResDataVO{" +
                "expiration_date=" + expiration_date +
                ", result=" + result +
                ", message='" + message + '\'' +
                ", vmcheck=" + vmcheck +
                ", play_section=" + play_section +
                ", disable_tvout=" + disable_tvout +
                ", expiration_playtime=" + expiration_playtime +
                ", content_expired=" + content_expired +
                '}';
    }
}
