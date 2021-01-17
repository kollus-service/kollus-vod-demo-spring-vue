package com.kollus.jwt;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

public class Decoder {
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
    public static String[] decodeJwt(String jwt) throws Exception {

        String[] parts = splitJwt(jwt);
        String headerJson = StringUtils.newStringUtf8(Base64.decodeBase64(parts[0]));
        String payloadJson = StringUtils.newStringUtf8(Base64.decodeBase64(parts[1]));
        String signature = parts[2];
        return new String[]{headerJson, payloadJson, signature};
    }
}
