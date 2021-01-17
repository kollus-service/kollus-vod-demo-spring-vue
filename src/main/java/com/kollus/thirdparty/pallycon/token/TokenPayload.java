package com.kollus.thirdparty.pallycon.token;

import com.kollus.thirdparty.pallycon.policy.DrmTypes;

public class TokenPayload {
    /***
     * DRM 종류 (“NCG”, “Widevine”, “PlayReady”, “FairPlay”), 기본값 : “PlayReady”
     * */
    private String drm_type;
    /***
     * PallyCon 서비스에서 발급받은 서비스 사이트 ID
     */
    private String site_id;
    /***
     * 라이선스를 요청하는 사용자의 ID (서비스 사이트에서 관리하는 유저 ID). 입력할 사용자 ID가 없을 경우, 기본값 (“LICENSETOKEN”) 입력
     */
    private String user_id;
    /***
     * 재생하려는 콘텐츠의 고유 ID. DRM 패키징 시에 사용된 값 (최대 200바이트 영숫자)
     */
    private String cid;
    /***
     * 라이선스 발급 관련 토큰 룰 데이터(상세 규격 참조)를 AES256 암호화하고 Base64로 인코딩한 문자열
     */
    private String token;
    /***
     * 토큰 유효성 발효 시간(GMT 기준) ‘yyyy-mm-ddThh:mm:ssZ’. 일반적으로 현재 시간을 설정하며, 이후 기본 600초 동안 토큰이 유효함. (콘솔 사이트에서 설정 가능)
     */
    private String timestamp;
    /***
     * 해시 메시지(상세 규격 참조)를 SHA256으로 해시하고 Base64로 인코딩한 문자열
     * base64( sha256( <site access key> + <drm type> + <site id> + <user id> + <cid> + <token> + <timestamp> ) )
     */
    private String hash;

    public TokenPayload(String drm_type, String site_id, String user_id, String cid, String token, String timestamp, String hash) {
        this.drm_type = drm_type;
        this.site_id = site_id;
        this.user_id = user_id;
        this.cid = cid;
        this.token = token;
        this.timestamp = timestamp;
        this.hash = hash;
    }
    public TokenPayload(){}
    public String getDrm_type() {
        return drm_type;
    }

    public void setDrm_type(String drm_type) {
        this.drm_type = drm_type;
    }
    public void setDrm_type(DrmTypes drm_type) {
        this.drm_type = drm_type.getType();
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }


}
