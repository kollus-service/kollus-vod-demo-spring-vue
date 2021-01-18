package com.kollus.url;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kollus.jwt.IGenerator;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class UrlFactory {
    public static String create(UrlType urlType, StreamType streamType, IGenerator tokenGenerator, String custom_key, QueryString queryString, boolean force_http) throws NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        StringBuilder builder = new StringBuilder();
        if(force_http){
            builder.append("http://");
        }
        else{
            builder.append("https://");
        }
        builder.append(urlType.getDomain());
        builder.append(streamType.getValue());
        builder.append("jwt=");
        builder.append(tokenGenerator.generate());
        builder.append("&custom_key=");
        builder.append(custom_key);
        if(queryString != null && !queryString.isEmpty())
        {
            builder.append("&");
            builder.append(queryString.build());
        }
        return builder.toString();
    }
    public static String create(UrlType urlType, StreamType streamType, IGenerator tokenGenerator, String custom_key, QueryString queryString) throws NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        return create(urlType, streamType, tokenGenerator, custom_key, queryString, false);
    }

    public static String create(UrlType urlType, StreamType streamType, String token, String custom_key, QueryString queryString, boolean force_http) {
        StringBuilder builder = new StringBuilder();
        if(force_http){
            builder.append("http://");
        }
        else{
            builder.append("https://");
        }
        builder.append(urlType.getDomain());
        builder.append(streamType.getValue());
        builder.append("jwt=");
        builder.append(token);
        builder.append("&custom_key=");
        builder.append(custom_key);
        if(queryString != null && !queryString.isEmpty())
        {
            builder.append("&");
            builder.append(queryString.build());
        }
        return builder.toString();
    }

    public static String create(UrlType urlType, StreamType streamType, String token, String custom_key, QueryString queryString)  {
        return create(urlType, streamType, token, custom_key, queryString, false);
    }

}
