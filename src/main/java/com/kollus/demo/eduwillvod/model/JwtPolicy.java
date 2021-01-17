package com.kollus.demo.eduwillvod.model;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(
                columnNames = {"CHANNEL_KEY", "CUID", "MEDIA_CONTENT_KEY"}
        )
})
public class JwtPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String channel_key ="default";
    @Column
    private String cuid;
    @Column
    private String media_content_key;
    @Column
    private long expt = 600;
    @Column
    private boolean intro = false;
    @Column
    private String intro_mck;
    @Column
    private boolean outro = false;
    @Column
    private String outro_mck;
    @Column
    private boolean scroll_event = false;
    @Column
    private boolean seek = true;
    @Column
    private long seekable_end = -1;
    @Column
    private boolean disable_playrate = false;
    @Column
    private boolean disable_nscreen = false;
    @Column
    private long start_time = -1;
    @Column
    private long end_time = -1;

    @Column
    private boolean enable_video_warter_marking = false;
    @Column
    private String code_kind = "client_user_id";
    @Column
    private int alpha = 200;
    @Column
    private int font_size = 7;
    @Column
    private String font_color = "FFFFFF";
    @Column
    private int show_time  = 5;
    @Column
    private int hide_time = 1;
    @Column
    private boolean enable_html5_player = true;

    public JwtPolicy() {
    }

    public JwtPolicy(String channel_key,
                     String cuid,
                     String media_content_key,
                     long expt, boolean intro, String intro_mck,
                     boolean outro, String outro_mck, boolean scroll_event, boolean seek, long seekable_end,
                     boolean disable_playrate, boolean disable_nscreen, long start_time, long end_time,
                     String code_kind, int alpha, int font_size, String font_color, int show_time,
                     int hide_time, boolean enable_html5_player) {
        this.channel_key = channel_key;
        this.cuid = cuid;
        this.media_content_key = media_content_key;
        this.expt = expt;
        this.intro = intro;
        this.intro_mck = intro_mck;
        this.outro = outro;
        this.outro_mck = outro_mck;
        this.scroll_event = scroll_event;
        this.seek = seek;
        this.seekable_end = seekable_end;
        this.disable_playrate = disable_playrate;
        this.disable_nscreen = disable_nscreen;
        this.start_time = start_time;
        this.end_time = end_time;
        this.code_kind = code_kind;
        this.alpha = alpha;
        this.font_size = font_size;
        this.font_color = font_color;
        this.show_time = show_time;
        this.hide_time = hide_time;
        this.enable_html5_player = enable_html5_player;
    }

    public String getChannel_key() {
        return channel_key;
    }

    public void setChannel_key(String channel_key) {
        this.channel_key = channel_key;
    }

    public String getCuid() {
        return cuid;
    }

    public void setCuid(String cuid) {
        this.cuid = cuid;
    }

    public String getMedia_content_key() {
        return media_content_key;
    }

    public void setMedia_content_key(String media_content_key) {
        this.media_content_key = media_content_key;
    }

    public long getExpt() {
        return expt;
    }

    public void setExpt(long expt) {
        this.expt = expt;
    }

    public boolean isIntro() {
        return intro;
    }

    public void setIntro(boolean intro) {
        this.intro = intro;
    }

    public String getIntro_mck() {
        return intro_mck;
    }

    public void setIntro_mck(String intro_mck) {
        this.intro_mck = intro_mck;
    }

    public boolean isOutro() {
        return outro;
    }

    public void setOutro(boolean outro) {
        this.outro = outro;
    }

    public String getOutro_mck() {
        return outro_mck;
    }

    public void setOutro_mck(String outro_mck) {
        this.outro_mck = outro_mck;
    }

    public boolean isScroll_event() {
        return scroll_event;
    }

    public void setScroll_event(boolean scroll_event) {
        this.scroll_event = scroll_event;
    }

    public boolean isSeek() {
        return seek;
    }

    public void setSeek(boolean seek) {
        this.seek = seek;
    }

    public long getSeekable_end() {
        return seekable_end;
    }

    public void setSeekable_end(long seekable_end) {
        this.seekable_end = seekable_end;
    }

    public boolean isDisable_playrate() {
        return disable_playrate;
    }

    public void setDisable_playrate(boolean disable_playrate) {
        this.disable_playrate = disable_playrate;
    }

    public boolean isDisable_nscreen() {
        return disable_nscreen;
    }

    public void setDisable_nscreen(boolean disable_nscreen) {
        this.disable_nscreen = disable_nscreen;
    }

    public long getStart_time() {
        return start_time;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(long end_time) {
        this.end_time = end_time;
    }

    public boolean isEnable_video_warter_marking() {
        return enable_video_warter_marking;
    }

    public void setEnable_video_warter_marking(boolean enable_video_warter_marking) {
        this.enable_video_warter_marking = enable_video_warter_marking;
    }

    public String getCode_kind() {
        return code_kind;
    }

    public void setCode_kind(String code_kind) {
        this.code_kind = code_kind;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public int getFont_size() {
        return font_size;
    }

    public void setFont_size(int font_size) {
        this.font_size = font_size;
    }

    public String getFont_color() {
        return font_color;
    }

    public void setFont_color(String font_color) {
        this.font_color = font_color;
    }

    public int getShow_time() {
        return show_time;
    }

    public void setShow_time(int show_time) {
        this.show_time = show_time;
    }

    public int getHide_time() {
        return hide_time;
    }

    public void setHide_time(int hide_time) {
        this.hide_time = hide_time;
    }

    public boolean isEnable_html5_player() {
        return enable_html5_player;
    }

    public void setEnable_html5_player(boolean enable_html5_player) {
        this.enable_html5_player = enable_html5_player;
    }

    @Override
    public String toString() {
        return "JwtPolicy{" +
                "channel_key='" + channel_key + '\'' +
                ", cuid='" + cuid + '\'' +
                ", media_content_key='" + media_content_key + '\'' +
                ", expt=" + expt +
                ", intro=" + intro +
                ", intro_mck='" + intro_mck + '\'' +
                ", outro=" + outro +
                ", outro_mck='" + outro_mck + '\'' +
                ", scroll_event=" + scroll_event +
                ", seek=" + seek +
                ", seekable_end=" + seekable_end +
                ", disable_playrate=" + disable_playrate +
                ", disable_nscreen=" + disable_nscreen +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", code_kind='" + code_kind + '\'' +
                ", alpha=" + alpha +
                ", font_size=" + font_size +
                ", font_color='" + font_color + '\'' +
                ", show_time=" + show_time +
                ", hide_time=" + hide_time +
                ", enable_html5_player=" + enable_html5_player +
                '}';
    }
}
