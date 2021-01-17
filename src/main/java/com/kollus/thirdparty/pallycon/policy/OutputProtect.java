package com.kollus.thirdparty.pallycon.policy;

public class OutputProtect {
    /***
     * 모바일 외부 출력 허용 여부. (기본값 false) NCG DRM에만 해당
     */
    private boolean allow_external_display;
    /***
     * HDCP 적용 여부. (기본값 0)
     * 0 : HDCP 제어 안함, 1 : HDCP 1.4 필요, 2 : HDCP 2.2 필요
     */
    private int control_hdcp;

    public boolean isAllow_external_display() {
        return allow_external_display;
    }

    public void setAllow_external_display(boolean allow_external_display) {
        this.allow_external_display = allow_external_display;
    }

    public int getControl_hdcp() {
        return control_hdcp;
    }

    public void setControl_hdcp(int control_hdcp) throws Exception {
        if(control_hdcp < 0 || control_hdcp > 2){
            throw new Exception("해당값은 0|1|2 값만 가질수 있습니다.");
        }
        this.control_hdcp = control_hdcp;
    }
    public OutputProtect(){}
    public OutputProtect(boolean allow_external_display, int control_hdcp) {
        this.allow_external_display = allow_external_display;
        this.control_hdcp = control_hdcp;
    }
}
