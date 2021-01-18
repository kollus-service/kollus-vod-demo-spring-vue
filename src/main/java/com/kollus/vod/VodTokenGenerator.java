package com.kollus.vod;

import com.kollus.jwt.Generator;
import com.kollus.vod.drm.VideoWatermarkingCodePolicy;
import com.kollus.vod.mediacontent.Mc;
import com.kollus.vod.player.PcSkin;

import java.util.ArrayList;

public class VodTokenGenerator extends Generator<VodPayload> {

    public VodTokenGenerator() {
        this.payload = new VodPayload();
    }

    public VodTokenGenerator secret_key(String secret_key) {
        this.secret_key = secret_key;
        return this;
    }

    public VodTokenGenerator cuid(String cuid) {
        this.payload.setCuid(cuid);
        return this;
    }

    public VodTokenGenerator awtc(String awtc) {
        this.payload.setAwtc(awtc);
        return this;
    }

    public VodTokenGenerator expt(long expt) {
        this.payload.setExpt(expt);
        return this;
    }

    public VodTokenGenerator mc(Mc mc) {
        if (this.payload.getMc() == null) {
            this.payload.setMc(new ArrayList<Mc>());
        }
        this.payload.getMc().add(mc);
        return this;
    }

    public VodTokenGenerator video_watermarking_code_policy(VideoWatermarkingCodePolicy video_watermarking_code_policy) {
        this.payload.setVideo_watermarking_code_policy(video_watermarking_code_policy);
        return this;
    }

    public VodTokenGenerator pc_skin(PcSkin pc_skin) {
        this.payload.setPc_skin(pc_skin);
        return this;
    }
}
