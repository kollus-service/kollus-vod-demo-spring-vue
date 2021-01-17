package com.kollus.jwt;

public class FalseFilter {

    @Override
    public boolean equals(Object obj) {

        if(obj == null || !(obj instanceof Boolean)) {
            return true;
        }
        return !((boolean) obj);
    }
}
