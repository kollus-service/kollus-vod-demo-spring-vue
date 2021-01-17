package com.kollus.thirdparty.pallycon.policy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TokenRuleGenerator {
    public String site_key;
    private TokenRule rule;


    public TokenRuleGenerator() {
        rule = new TokenRule();
    }

    public TokenRuleGenerator site_key(String site_key) {
        this.site_key = site_key;
        return this;
    }

    public TokenRuleGenerator limit(boolean limit) {
        if (this.rule.getPlayback_policy() == null) {
            this.rule.setPlayback_policy(new PlaybackPolicy());
        }
        this.rule.getPlayback_policy().setLimit(limit);
        return this;
    }

    public TokenRuleGenerator persistent(boolean persistent) {
        if (this.rule.getPlayback_policy() == null) {
            this.rule.setPlayback_policy(new PlaybackPolicy());
        }
        this.rule.getPlayback_policy().setPersistent(persistent);
        return this;
    }

    public TokenRuleGenerator duration(int duration) {
        if (this.rule.getPlayback_policy() == null) {
            this.rule.setPlayback_policy(new PlaybackPolicy());
        }
        this.rule.getPlayback_policy().setDuration(duration);
        return this;
    }

    public TokenRuleGenerator expire_date(String expire_date) {
        if (this.rule.getPlayback_policy() == null) {
            this.rule.setPlayback_policy(new PlaybackPolicy());
        }
        this.rule.getPlayback_policy().setExpire_date(expire_date);
        return this;
    }

    public TokenRuleGenerator expire_date(Date expire_date) {
        if (this.rule.getPlayback_policy() == null) {
            this.rule.setPlayback_policy(new PlaybackPolicy());
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        this.rule.getPlayback_policy().setExpire_date(format.format(expire_date));
        return this;
    }

    public TokenRuleGenerator hardware_drm(boolean hardware_drm) {
        if (this.rule.getSecurity_policy() == null) {
            this.rule.setSecurity_policy(new SecurityPolicy());
        }
        this.rule.getSecurity_policy().setHardware_drm(hardware_drm);
        return this;
    }

    public TokenRuleGenerator allow_external_display(boolean allow_external_display) {
        if (this.rule.getSecurity_policy() == null) {
            SecurityPolicy securityPolicy = new SecurityPolicy();
            securityPolicy.setOutput_protect(new OutputProtect());
            this.rule.setSecurity_policy(securityPolicy);
        } else {
            if (this.rule.getSecurity_policy().getOutput_protect() == null) {
                this.rule.getSecurity_policy().setOutput_protect(new OutputProtect());
            }
        }
        this.rule.getSecurity_policy().getOutput_protect().setAllow_external_display(allow_external_display);
        return this;
    }

    public TokenRuleGenerator control_hdcp(int control_hdcp) throws Exception {
        if (this.rule.getSecurity_policy() == null) {
            SecurityPolicy securityPolicy = new SecurityPolicy();
            securityPolicy.setOutput_protect(new OutputProtect());
            this.rule.setSecurity_policy(securityPolicy);
        } else {
            if (this.rule.getSecurity_policy().getOutput_protect() == null) {
                this.rule.getSecurity_policy().setOutput_protect(new OutputProtect());
            }
        }
        this.rule.getSecurity_policy().getOutput_protect().setControl_hdcp(control_hdcp);
        return this;
    }

    public TokenRuleGenerator allow_mobile_abnormal_device(boolean allow_mobile_abnormal_device) {
        if (this.rule.getSecurity_policy() == null) {
            this.rule.setSecurity_policy(new SecurityPolicy());
        }
        this.rule.getSecurity_policy().setAllow_mobile_abnormal_device(allow_mobile_abnormal_device);
        return this;
    }

    public TokenRuleGenerator playready_security_level(int playready_security_level) throws Exception {
        if (this.rule.getSecurity_policy() == null) {
            this.rule.setSecurity_policy(new SecurityPolicy());
        }
        this.rule.getSecurity_policy().setPlayready_security_level(playready_security_level);
        return this;
    }

    public TokenRuleGenerator mpeg_cenc_key_id(String mpeg_cenc_key_id) {
        if (this.rule.getExternal_key() == null) {
            ExternalKey externalKey = new ExternalKey();
            externalKey.setMpeg_cenc(new MpegCenc());
            this.rule.setExternal_key(externalKey);
        } else {
            if (this.rule.getExternal_key().getMpeg_cenc() == null) {
                this.rule.getExternal_key().setMpeg_cenc(new MpegCenc());
            }
        }
        this.rule.getExternal_key().getMpeg_cenc().setKey_id(mpeg_cenc_key_id);
        return this;
    }

    public TokenRuleGenerator mpeg_cenc_key(String mpeg_cenc_key) {
        if (this.rule.getExternal_key() == null) {
            ExternalKey externalKey = new ExternalKey();
            externalKey.setMpeg_cenc(new MpegCenc());
            this.rule.setExternal_key(externalKey);
        } else {
            if (this.rule.getExternal_key().getMpeg_cenc() == null) {
                this.rule.getExternal_key().setMpeg_cenc(new MpegCenc());
            }
        }
        this.rule.getExternal_key().getMpeg_cenc().setKey(mpeg_cenc_key);
        return this;
    }

    public TokenRuleGenerator mpeg_cenc_iv(String mpeg_cenc_iv) {
        if (this.rule.getExternal_key() == null) {
            ExternalKey externalKey = new ExternalKey();
            externalKey.setMpeg_cenc(new MpegCenc());
            this.rule.setExternal_key(externalKey);
        } else {
            if (this.rule.getExternal_key().getMpeg_cenc() == null) {
                this.rule.getExternal_key().setMpeg_cenc(new MpegCenc());
            }
        }
        this.rule.getExternal_key().getMpeg_cenc().setIv(mpeg_cenc_iv);
        return this;
    }

    public TokenRuleGenerator hls_aes_key(String hls_aes_key) {
        if (this.rule.getExternal_key() == null) {
            ExternalKey externalKey = new ExternalKey();
            externalKey.setHls_aes(new HlsAes());
            this.rule.setExternal_key(externalKey);
        } else {
            if (this.rule.getExternal_key().getHls_aes() == null) {
                this.rule.getExternal_key().setHls_aes(new HlsAes());
            }
        }
        this.rule.getExternal_key().getHls_aes().setKey(hls_aes_key);
        return this;
    }

    public TokenRuleGenerator hls_aes_iv(String hls_aes_iv) {
        if (this.rule.getExternal_key() == null) {
            ExternalKey externalKey = new ExternalKey();
            externalKey.setHls_aes(new HlsAes());
            this.rule.setExternal_key(externalKey);
        } else {
            if (this.rule.getExternal_key().getHls_aes() == null) {
                this.rule.getExternal_key().setHls_aes(new HlsAes());
            }
        }
        this.rule.getExternal_key().getHls_aes().setIv(hls_aes_iv);
        return this;
    }

    public TokenRuleGenerator ncg_cek(String ncg_cek) {
        if (this.rule.getExternal_key() == null) {
            ExternalKey externalKey = new ExternalKey();
            externalKey.setNcg(new Ncg());
            this.rule.setExternal_key(externalKey);
        } else {
            if (this.rule.getExternal_key().getNcg() == null) {
                this.rule.getExternal_key().setNcg(new Ncg());
            }
        }
        this.rule.getExternal_key().getNcg().setCek(ncg_cek);
        return this;
    }

    public TokenRule getRule() {
        return this.rule;
    }


    public String build() throws JsonProcessingException, NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
            UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        String token = "";
        ObjectMapper mapper = new ObjectMapper();
        String rulePayload = mapper.writeValueAsString(this.rule);
        byte[] keyData = this.site_key.getBytes();
        String IV = "0123456789abcdef";
        SecretKey secureKey = new SecretKeySpec(keyData, "AES");
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(IV.getBytes(Charset.forName("UTF-8"))));
        byte[] encrypted = c.doFinal(rulePayload.getBytes("UTF-8"));
        token = new String(Base64.encodeBase64(encrypted));
        return token;
    }

}
