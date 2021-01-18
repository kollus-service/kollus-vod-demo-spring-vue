package com.kollus.url;


import java.util.HashMap;
import java.util.Map;

public class QueryString {
    private HashMap<String, String> queryMap = new HashMap<String, String>();

    public QueryString() {
    }

    public QueryString pf(String profile_key) {
        if (profile_key == null || profile_key.isEmpty()) {
            if (queryMap.containsKey("pf")) queryMap.remove("pf");
        } else {
            queryMap.put("pf", profile_key);
        }
        return this;
    }

    public QueryString autoplay(boolean autoplay) {
        if (autoplay) {
            queryMap.put("autoplay", "");
        } else {
            queryMap.remove("autoplay");
        }
        return this;
    }

    public QueryString mute(boolean mute) {
        if (mute) {
            queryMap.put("mute", "");
        } else {
            queryMap.remove("moute");
        }
        return this;
    }

    public QueryString download(boolean download) {
        if (download) {
            queryMap.put("download", "");
        } else {
            queryMap.remove("download");
        }
        return this;
    }

    public QueryString title(String title) {
        if (title == null || title.isEmpty()) {
            if (queryMap.containsKey("title")) queryMap.remove("title");
        } else {
            queryMap.put("title", title);
        }
        return this;
    }

    public QueryString t(int time) {
        if (time < 0) {
            if (queryMap.containsKey("t")) queryMap.remove("t");
        } else {
            queryMap.put("t", String.format("%d", time));
        }
        return this;
    }

    public QueryString s(int time) {
        if (time < 0) {
            if (queryMap.containsKey("s")) queryMap.remove("s");
        } else {
            queryMap.put("s", String.format("%d", time));
        }
        return this;
    }

    public QueryString force_exclusive_player(boolean force_exculsive_player) {
        if (force_exculsive_player) {
            queryMap.put("force_exculsive_player", "");
        } else {
            queryMap.remove("force_exculsive_player");
        }
        return this;
    }

    public QueryString uservalue0(String uservalue) {
        if (uservalue == null || uservalue.isEmpty()) {
            if (queryMap.containsKey("uservalue0")) queryMap.remove("uservalue0");
        } else {
            queryMap.put("uservalue0", uservalue);
        }
        return this;
    }

    public QueryString uservalue1(String uservalue) {
        if (uservalue == null || uservalue.isEmpty()) {
            if (queryMap.containsKey("uservalue1")) queryMap.remove("uservalue1");
        } else {
            queryMap.put("uservalue1", uservalue);
        }
        return this;
    }

    public QueryString uservalue2(String uservalue) {
        if (uservalue == null || uservalue.isEmpty()) {
            if (queryMap.containsKey("uservalue2")) queryMap.remove("uservalue2");
        } else {
            queryMap.put("uservalue2", uservalue);
        }
        return this;
    }

    public QueryString uservalue3(String uservalue) {
        if (uservalue == null || uservalue.isEmpty()) {
            if (queryMap.containsKey("uservalue3")) queryMap.remove("uservalue3");
        } else {
            queryMap.put("uservalue3", uservalue);
        }
        return this;
    }

    public QueryString uservalue4(String uservalue) {
        if (uservalue == null || uservalue.isEmpty()) {
            if (queryMap.containsKey("uservalue4")) queryMap.remove("uservalue4");
        } else {
            queryMap.put("uservalue4", uservalue);
        }
        return this;
    }

    public QueryString uservalue5(String uservalue) {
        if (uservalue == null || uservalue.isEmpty()) {
            if (queryMap.containsKey("uservalue5")) queryMap.remove("uservalue5");
        } else {
            queryMap.put("uservalue5", uservalue);
        }
        return this;
    }

    public QueryString uservalue6(String uservalue) {
        if (uservalue == null || uservalue.isEmpty()) {
            if (queryMap.containsKey("uservalue6")) queryMap.remove("uservalue6");
        } else {
            queryMap.put("uservalue6", uservalue);
        }
        return this;
    }

    public QueryString uservalue7(String uservalue) {
        if (uservalue == null || uservalue.isEmpty()) {
            if (queryMap.containsKey("uservalue7")) queryMap.remove("uservalue7");
        } else {
            queryMap.put("uservalue7", uservalue);
        }
        return this;
    }

    public QueryString uservalue8(String uservalue) {
        if (uservalue == null || uservalue.isEmpty()) {
            if (queryMap.containsKey("uservalue8")) queryMap.remove("uservalue8");
        } else {
            queryMap.put("uservalue8", uservalue);
        }
        return this;
    }

    public QueryString uservalue9(String uservalue) {
        if (uservalue == null || uservalue.isEmpty()) {
            if (queryMap.containsKey("uservalue9")) queryMap.remove("uservalue9");
        } else {
            queryMap.put("uservalue9", uservalue);
        }
        return this;
    }

    public QueryString brightness(int brightness) {
        if (brightness == 0 || brightness < -50 || brightness > 50) {
            if (queryMap.containsKey("brightness")) queryMap.remove("brightness");
        } else {
            queryMap.put("brightness", String.format("%d", brightness));
        }
        return this;
    }

    public QueryString contrast(int constrast) {
        if (constrast == 0 || constrast < -50 || constrast > 50) {
            if (queryMap.containsKey("constrast")) queryMap.remove("constrast");
        } else {
            queryMap.put("constrast", String.format("%d", constrast));
        }
        return this;
    }

    public QueryString saturation(int saturation) {
        if (saturation == 0 || saturation < -50 || saturation > 50) {
            if (queryMap.containsKey("saturation")) queryMap.remove("saturation");
        } else {
            queryMap.put("saturation", String.format("%d", saturation));
        }
        return this;
    }

    public QueryString mobile_return_url(String mobile_return_url) {
        if (mobile_return_url == null || mobile_return_url.isEmpty()) {
            if (queryMap.containsKey("mobile_return_url")) queryMap.remove("mobile_return_url");
        } else {
            queryMap.put("mobile_return_url", mobile_return_url);
        }
        return this;
    }

    public QueryString mobile_folder_download(String mobile_folder_download) {
        if (mobile_folder_download == null || mobile_folder_download.isEmpty()) {
            if (queryMap.containsKey("mobile_folder_download")) queryMap.remove("mobile_folder_download");
        } else {
            queryMap.put("mobile_folder_download", mobile_folder_download);
        }
        return this;
    }

//    public QueryString pc_player_version(PlayerVersion playerversion) {
//        if (playerversion == null) {
//            if (queryMap.containsKey("pc_player_version")) queryMap.remove("pc_player_version");
//        } else {
//            queryMap.put("pc_player_version", playerversion.getValue());
//        }
//        return this;
//    }

    public QueryString pv(PlayerVersion playerVersion){
        if (playerVersion == null) {
            if (queryMap.containsKey("pv")) queryMap.remove("pv");
        } else {
            queryMap.put("pv", playerVersion.getValue());
        }
        return this;
    }

    public QueryString pc_folder_download(String pc_folder_download) {
        if (pc_folder_download == null || pc_folder_download.isEmpty()) {
            if (queryMap.containsKey("pc_folder_download")) queryMap.remove("pc_folder_download");
        } else {
            queryMap.put("pc_folder_download", pc_folder_download);
        }
        return this;
    }

    public QueryString play_downloaded_file(boolean play_download_file) {
        if (play_download_file) {
            queryMap.put("play_download_file", "");
        } else {
            queryMap.remove("play_download_file");
        }
        return this;
    }

    public QueryString buffer(int buffer) {
        if (buffer < 2 || buffer > 10) {
            if (queryMap.containsKey("buffer")) queryMap.remove("buffer");
        } else {
            queryMap.put("buffer", String.format("%d", buffer));
        }
        return this;
    }

    public QueryString show_controls_pause(boolean show_controls_pause) {
        if (show_controls_pause) {
            queryMap.put("show_controls_pause", "");
        } else {
            queryMap.remove("show_controls_pause");
        }
        return this;
    }

    public QueryString show_poster_ended(boolean show_poster_ended) {
        if (show_poster_ended) {
            queryMap.put("show_poster_ended", "");
        } else {
            queryMap.remove("show_poster_ended");
        }
        return this;
    }

    public QueryString overlay_button_position(ButtonPosition overlay_button_position) {
        if (overlay_button_position == null) {
            if (queryMap.containsKey("overlay_button_position")) queryMap.remove("overlay_button_position");
        } else {
            queryMap.put("overlay_button_position", overlay_button_position.getValue());
        }
        return this;
    }

    public QueryString loadcheck(int loadcheck) {

        if (loadcheck != 0) {
            if (queryMap.containsKey("loadcheck")) queryMap.remove("loadcheck");
        } else {
            queryMap.put("loadcheck", String.format("%d", loadcheck));
        }
        return this;
    }


    public String build() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : this.queryMap.entrySet()) {
            if (builder.length() != 0) {
                builder.append("&");
            }
            builder.append(entry.getKey());
            if (!"".equals(entry.getValue())) {
                builder.append("=");
                builder.append(entry.getValue());
            }
        }
        return builder.toString();
    }

    public boolean isEmpty() {
        return this.queryMap.keySet().size() <= 0;
    }
    public static QueryString create(){
        return new QueryString();
    }
}
