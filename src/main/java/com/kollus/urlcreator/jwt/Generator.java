package com.kollus.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public abstract class Generator<T extends Payload> implements IGenerator {
    protected String secret_key;
    protected T payload;
    public String generate() throws JsonProcessingException, NoSuchAlgorithmException, InvalidKeyException {
        ObjectMapper mapper = new ObjectMapper();
        return Encoder.createJwt(mapper.writeValueAsString(this.payload), this.secret_key);
    }
    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return  mapper.writeValueAsString(this.payload);
    }

}
