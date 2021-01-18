package com.kollus.thirdparty.pallycon.token;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kollus.thirdparty.pallycon.policy.DrmTypes;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class CustomDataGenerator {
    private CustomData customData;
    private CustomDataData customDataData;

    public CustomDataGenerator() {
        this.customData = new CustomData();
        this.customDataData = new CustomDataData();
    }

    ;

    public CustomDataGenerator drm_type(DrmTypes drm_type) {
        this.customData.setDrm_type(drm_type.getType());
        return this;
    }

    public CustomDataGenerator site_id(String site_id) {
        this.customData.setSite_id(site_id);
        return this;
    }

    public CustomDataGenerator user_id(String user_id) {
        this.customDataData.setUser_id(user_id);
        return this;
    }

    public CustomDataGenerator cid(String cid) {
        this.customDataData.setCid(cid);
        return this;
    }

    public CustomDataGenerator oid(String oid) {
        this.customDataData.setOid(oid);
        return this;
    }

    public String generate(String sitekey) throws JsonProcessingException, NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException {

        ObjectMapper mapper = new ObjectMapper();
        String customDataDataPayload = mapper.writeValueAsString(this.customDataData);
        byte[] keyData = sitekey.getBytes();
        String IV = "0123456789abcdef";
        SecretKey secureKey = new SecretKeySpec(keyData, "AES");
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(IV.getBytes(Charset.forName("UTF-8"))));
        byte[] encrypted = c.doFinal(customDataDataPayload.getBytes("UTF-8"));
        String data = new String(Base64.encodeBase64(encrypted));
        this.customData.setData(data);
        String customDataPayload = mapper.writeValueAsString(this.customData);
        return new String(Base64.encodeBase64(customDataPayload.getBytes()));
    }
}
