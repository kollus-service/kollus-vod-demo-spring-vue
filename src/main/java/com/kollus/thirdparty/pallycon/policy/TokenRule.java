package com.kollus.thirdparty.pallycon.policy;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenRule {
    /***
     * 재생과 관련된 룰 설정
     */
    private PlaybackPolicy playback_policy;
    /***
     * 보안 관련 룰 설정
     */
    private SecurityPolicy security_policy;
    /***
     * 재생할 콘텐츠의 키 정보
     */
    private ExternalKey external_key;

    public TokenRule(){}
    public TokenRule(PlaybackPolicy playback_policy, SecurityPolicy security_policy, ExternalKey external_key) {
        this.playback_policy = playback_policy;
        this.security_policy = security_policy;
        this.external_key = external_key;
    }

    public PlaybackPolicy getPlayback_policy() {
        return playback_policy;
    }

    public void setPlayback_policy(PlaybackPolicy playback_policy) {
        this.playback_policy = playback_policy;
    }

    public SecurityPolicy getSecurity_policy() {
        return security_policy;
    }

    public void setSecurity_policy(SecurityPolicy security_policy) {
        this.security_policy = security_policy;
    }

    public ExternalKey getExternal_key() {
        return external_key;
    }

    public void setExternal_key(ExternalKey external_key) {
        this.external_key = external_key;
    }

}
