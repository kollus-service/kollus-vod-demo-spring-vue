package com.kollus.jwt;

public class TrueFilter {

    @Override
    public boolean equals(Object obj) {

        if(obj == null || !(obj instanceof Boolean)) {
            return true;
        }
        return (boolean) obj;
    }
}
