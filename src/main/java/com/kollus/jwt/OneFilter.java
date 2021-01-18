package com.kollus.jwt;

public class OneFilter {

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Integer) || !(obj instanceof Long)){
            return true;
        }
        int value = (int) obj;
        return value == 1;
    }
}
