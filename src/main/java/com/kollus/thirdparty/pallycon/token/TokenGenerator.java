package com.kollus.thirdparty.pallycon.token;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kollus.thirdparty.pallycon.policy.DrmTypes;
import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TokenGenerator {
    private TokenPayload tokenPayload;

    public TokenGenerator() {
    }

    public TokenGenerator drm_type(String drm_type) {
        if (this.tokenPayload == null) {
            this.tokenPayload = new TokenPayload();
        }
        this.tokenPayload.setDrm_type(drm_type);
        return this;
    }

    public TokenGenerator drm_type(DrmTypes drm_type) {
        if (this.tokenPayload == null) {
            this.tokenPayload = new TokenPayload();
        }
        this.tokenPayload.setDrm_type(drm_type);
        return this;
    }

    public TokenGenerator site_id(String site_id) {
        if (this.tokenPayload == null) {
            this.tokenPayload = new TokenPayload();
        }
        this.tokenPayload.setSite_id(site_id);
        return this;
    }
    public TokenGenerator cid(String cid) {
        if (this.tokenPayload == null) {
            this.tokenPayload = new TokenPayload();
        }
        this.tokenPayload.setCid(cid);
        return this;
    }

    public TokenGenerator user_id(String user_id) {
        if (this.tokenPayload == null) {
            this.tokenPayload = new TokenPayload();
        }
        if (user_id != null) {
            this.tokenPayload.setUser_id(user_id);
        } else {
            this.tokenPayload.setUser_id("LICENSETOKEN");
        }
        return this;
    }

    public TokenGenerator token(String token) {
        if (this.tokenPayload == null) {
            this.tokenPayload = new TokenPayload();
        }
        this.tokenPayload.setToken(token);
        return this;
    }

    public TokenGenerator timestamp(String timestamp) {
        if (this.tokenPayload == null) {
            this.tokenPayload = new TokenPayload();
        }
        this.tokenPayload.setTimestamp(timestamp);
        return this;
    }

    public TokenGenerator timestamp(Date timestamp) {
        if (this.tokenPayload == null) {
            this.tokenPayload = new TokenPayload();
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.KOREAN);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        this.tokenPayload.setTimestamp(format.format(timestamp));
        return this;
    }

    public TokenGenerator hash(String accessKey) throws Exception {
        String msg = "";
        msg += accessKey;
        if (this.tokenPayload.getDrm_type() != null) {
            msg += tokenPayload.getDrm_type();
        } else {
            throw new Exception("Not Found Drm Type");
        }
        if (this.tokenPayload.getSite_id() != null) {
            msg += tokenPayload.getSite_id();
        } else {
            throw new Exception("Not Found SITE ID");
        }
        if (this.tokenPayload.getUser_id() != null) {
            msg += tokenPayload.getUser_id();
        } else {
            throw new Exception("Not Found User ID");
        }
        if (this.tokenPayload.getCid() != null) {
            msg += tokenPayload.getCid();
        } else {
            throw new Exception("Not Found CID");
        }
        if (this.tokenPayload.getToken() != null) {
            msg += tokenPayload.getToken();
        } else {
            throw new Exception("Not Found Token");
        }
        if (this.tokenPayload.getTimestamp() != null) {
            msg += tokenPayload.getTimestamp();
        } else {
            throw new Exception("Not Found Timestamp");
        }
        String hash = null;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(msg.getBytes());
        hash = new String(Base64.encodeBase64(md.digest()));
        this.tokenPayload.setHash(hash);
        return this;
    }

    public TokenPayload tokenPayload(){
        return this.tokenPayload;
    }
    public String generate() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(this.tokenPayload);
        return new String(Base64.encodeBase64(json.getBytes()));
    }

}
