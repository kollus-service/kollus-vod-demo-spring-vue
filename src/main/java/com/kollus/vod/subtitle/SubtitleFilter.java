package com.kollus.vod.subtitle;

import com.fasterxml.jackson.annotation.JsonInclude;

public class SubtitleFilter {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String language_code;
    public SubtitleFilter() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage_code() {
        return language_code;
    }

    public void setLanguage_code(String language_code) {
        this.language_code = language_code;
    }
}
