package com.kollus.thirdparty.pallycon.policy;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlaybackPolicy {
    /**
     * 기간제 적용 여부 (기본값: false)
     * true : 기간제, false : 무제한
     */
    private boolean limit = false;
    /***
     * 오프라인용 라이선스 저장 여부. (기본값: false)
     * true : 라이선스 유지, false : 재생 후 라이선스 제거(스트리밍)
     */
    private boolean persistent = false;
    /***
     * 라이선스 유효 기간 (단위 : 초). duration, expire_date 동시 설정 시, duration 값을 우선시합니다.
     * ‘limit’값이 true인 경우에만 적용됩니다. (false일 경우 이 항목은 무시됨)
     */
    private int duration;
    /***
     * 라이선스 만료 날짜, GMT Time 표기'yyyy-mm-ddThh:mm:ssZ' ‘limit’값이 true인 경우에만 적용됩니다. (false일 경우 이 항목은 무시) duration 항목과 함께 사용된 경우, 이 항목은 무시됩니다.
     */
    private String expire_date;
    public PlaybackPolicy(){}
    public PlaybackPolicy(boolean limit, boolean persistent, int duration, String expire_date) {
        this.limit = limit;
        this.persistent = persistent;
        this.duration = duration;
        this.expire_date = expire_date;
    }

    public boolean isLimit() {
        return limit;
    }

    public void setLimit(boolean limit) {
        this.limit = limit;
    }

    public boolean isPersistent() {
        return persistent;
    }

    public void setPersistent(boolean persistent) {
        this.persistent = persistent;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(String expire_date) {
        this.expire_date = expire_date;
    }

}
