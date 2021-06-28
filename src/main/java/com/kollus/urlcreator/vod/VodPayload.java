package com.kollus.vod;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kollus.jwt.Payload;
import com.kollus.vod.drm.VideoWatermarkingCodePolicy;
import com.kollus.vod.mediacontent.Mc;
import com.kollus.vod.player.PcSkin;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VodPayload extends Payload {
    private String cuid;
    private String awtc;
    private VideoWatermarkingCodePolicy video_watermarking_code_policy;
    private long expt;
    private PcSkin pc_skin;
    private List<Mc> mc;

    public VodPayload(){}

    public String getCuid() {
        return cuid;
    }

    public void setCuid(String cuid) {
        this.cuid = cuid;
    }

    public String getAwtc() {
        return awtc;
    }

    public void setAwtc(String awtc) {
        this.awtc = awtc;
    }

    public VideoWatermarkingCodePolicy getVideo_watermarking_code_policy() {
        return video_watermarking_code_policy;
    }

    public void setVideo_watermarking_code_policy(VideoWatermarkingCodePolicy video_watermarking_code_policy) {
        this.video_watermarking_code_policy = video_watermarking_code_policy;
    }

    public long getExpt() {
        return expt;
    }

    public void setExpt(long expt) {
        this.expt = expt;
    }

    public PcSkin getPc_skin() {
        return pc_skin;
    }

    public void setPc_skin(PcSkin pc_skin) {
        this.pc_skin = pc_skin;
    }

    public List<Mc> getMc() {
        return mc;
    }

    public void setMc(List<Mc> mc) {
        this.mc = mc;
    }
}
