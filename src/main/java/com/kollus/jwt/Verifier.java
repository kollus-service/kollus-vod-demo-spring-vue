package com.kollus.jwt;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Verifier {

    private static String[] splitJwt(String jwt) throws Exception{
        String[] parts = jwt.split("\\.");
        if (parts.length == 2 && jwt.endsWith(".")) {
            parts = new String[] { parts[0], parts[1], "" };
        }
        if (parts.length != 3) {
            throw new Exception(String.format("The token was expected to have 3 parts, but got %s.", parts.length));
        }
        return parts;
    }

    public static boolean verify(String jwt, String secretKey) throws Exception{
        String[] parts = splitJwt(jwt);
        byte[] contentBytes = String.format("%s.%s", parts[0], parts[1]).getBytes(StandardCharsets.UTF_8);
        byte[] signatureBytes = Base64.decodeBase64(parts[2]);

        final Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        byte[] newSignatureBytes = mac.doFinal(contentBytes);
        return MessageDigest.isEqual(newSignatureBytes, signatureBytes);

    }
}
