package com.kollus.thirdparty.pallycon.policy;

public class ExternalKey {
    private MpegCenc mpeg_cenc;
    private HlsAes hls_aes;
    private Ncg ncg;

    public MpegCenc getMpeg_cenc() {
        return mpeg_cenc;
    }

    public void setMpeg_cenc(MpegCenc mpeg_cenc) {
        this.mpeg_cenc = mpeg_cenc;
    }

    public HlsAes getHls_aes() {
        return hls_aes;
    }

    public void setHls_aes(HlsAes hls_aes) {
        this.hls_aes = hls_aes;
    }

    public Ncg getNcg() {
        return ncg;
    }

    public void setNcg(Ncg ncg) {
        this.ncg = ncg;
    }

    public ExternalKey() {
    }

    public ExternalKey(MpegCenc mpeg_cenc, HlsAes hls_aes, Ncg ncg) {
        this.mpeg_cenc = mpeg_cenc;
        this.hls_aes = hls_aes;
        this.ncg = ncg;
    }
}
