package com.kollus.thirdparty.pallycon.policy;

public class SecurityPolicy {
    /***
     * 하드웨어 DRM 적용 여부.(기본값 false)
     * - 각 DRM 종류에 따라 security level 설정됨
     * - cenc (Widevine Modular) 컨텐츠에만 적용
     */
    private boolean hardware_drm;
    /**
     * 외부 출력 룰 설정
     */
    private OutputProtect output_protect;
    /***
     * 탈옥 기기 재생 허용 여부 (기본값 false)
     */
    private boolean allow_mobile_abnormal_device = false;
    /***
     * PlayReady 보안 레벨 설정, 150,2000 (기본값 150)
     */
    private int playready_security_level;

    public boolean isHardware_drm() {
        return hardware_drm;
    }

    public void setHardware_drm(boolean hardware_drm) {
        this.hardware_drm = hardware_drm;
    }

    public OutputProtect getOutput_protect() {
        return output_protect;
    }

    public void setOutput_protect(OutputProtect output_protect) {
        this.output_protect = output_protect;
    }

    public boolean isAllow_mobile_abnormal_device() {
        return allow_mobile_abnormal_device;
    }

    public void setAllow_mobile_abnormal_device(boolean allow_mobile_abnormal_device) {
        this.allow_mobile_abnormal_device = allow_mobile_abnormal_device;
    }

    public int getPlayready_security_level() {
        return playready_security_level;
    }

    public void setPlayready_security_level(int playready_security_level) throws Exception {
        if(playready_security_level != 150 && playready_security_level != 2000){
            throw new Exception("해당값은 150, 2000 만 가질수 있습니다.");
        }
        this.playready_security_level = playready_security_level;
    }
    public SecurityPolicy(){}
    public SecurityPolicy(boolean hardware_drm, OutputProtect output_protect, boolean allow_mobile_abnormal_device, int playready_security_level) {
        this.hardware_drm = hardware_drm;
        this.output_protect = output_protect;
        this.allow_mobile_abnormal_device = allow_mobile_abnormal_device;
        this.playready_security_level = playready_security_level;
    }

}
