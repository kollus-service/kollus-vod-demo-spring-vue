package com.kollus.jwt;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Encoder {
    private static String createJwt(final String headerJson, final String payloadJson, String secretKey)
            throws NoSuchAlgorithmException, InvalidKeyException {
        String header = Base64.encodeBase64URLSafeString(headerJson.getBytes(StandardCharsets.UTF_8));
        String payload = Base64.encodeBase64URLSafeString(payloadJson.getBytes(StandardCharsets.UTF_8));
        String content = String.format("%s.%s", header, payload);
        final Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        byte[] signatureBytes = mac.doFinal(content.getBytes(StandardCharsets.UTF_8));
        String signature = Base64.encodeBase64URLSafeString(signatureBytes);
        return String.format("%s.%s", content, signature);
    }
    public static String createJwt(final String payloadJson, String secretKey)
            throws InvalidKeyException, NoSuchAlgorithmException {
        String headerJson = "{\"alg\": \"HS256\",\"typ\": \"JWT\"}";
        return createJwt(headerJson, payloadJson, secretKey);
    }
}
