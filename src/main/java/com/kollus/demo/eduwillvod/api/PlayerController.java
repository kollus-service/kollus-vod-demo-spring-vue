package com.kollus.demo.eduwillvod.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kollus.demo.eduwillvod.config.KollusConstant;
import com.kollus.demo.eduwillvod.model.JwtPolicy;
import com.kollus.demo.eduwillvod.repository.JwtPolicyRepository;
import com.kollus.demo.eduwillvod.vo.DownloadReqVO;
import com.kollus.demo.eduwillvod.vo.PlayerReqVO;
import com.kollus.jwt.Encoder;
import com.kollus.url.*;
import com.kollus.vod.VodTokenGenerator;
import com.kollus.vod.drm.VideoWatermarkingCodePolicy;
import com.kollus.vod.mediacontent.Mc;
import com.kollus.vod.mediacontent.McGenerator;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua_parser.Client;
import ua_parser.Parser;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/player")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PlayerController {

    @Autowired
    JwtPolicyRepository jwtPolicyRepository;

    @PostMapping("/create")
    public HashMap create(@RequestBody PlayerReqVO playerReq) throws Throwable {

        List<JwtPolicy> jwtPolicies = jwtPolicyRepository.findJwtPolicyForPlayerUrl(playerReq.getChannel_key());
        JwtPolicy jwtPolicy = null;
        if (jwtPolicies.size() == 1) {
            //Default Policy
            jwtPolicy = jwtPolicies.get(0);
        } else {
            long matchedMckAndCuid = jwtPolicies.stream().filter(policy -> {
                return playerReq.getMck().equals(policy.getMedia_content_key()) &&
                        playerReq.getCuid().equals(policy.getCuid());
            }).count();
            if (matchedMckAndCuid == 1) {
                jwtPolicy = jwtPolicies.stream().filter(policy -> {
                    return playerReq.getMck().equals(policy.getMedia_content_key()) &&
                            playerReq.getCuid().equals(policy.getCuid());
                }).collect(Collectors.toList()).get(0);
            } else {
                long matchedMck = jwtPolicies.stream().filter(policy -> {
                    return playerReq.getMck().equals(policy.getMedia_content_key()) &&
                            (policy.getCuid() == null || policy.getCuid().isEmpty());
                }).count();
                if (matchedMck == 1) {
                    jwtPolicy = jwtPolicies.stream().filter(policy -> {
                        return playerReq.getMck().equals(policy.getMedia_content_key()) &&
                                (policy.getCuid() == null || policy.getCuid().isEmpty());
                    }).collect(Collectors.toList()).get(0);
                } else {
                    long matchedChannel = jwtPolicies.stream().filter(policy -> {
                        return playerReq.getChannel_key().equals(policy.getChannel_key()) &&
                                (policy.getMedia_content_key() == null || policy.getMedia_content_key().isEmpty()) &&
                                (policy.getCuid() == null || policy.getCuid().isEmpty());
                    }).count();
                    if (matchedChannel == 1) {
                        jwtPolicy = jwtPolicies.stream().filter(policy -> {
                            return playerReq.getChannel_key().equals(policy.getChannel_key()) &&
                                    (policy.getMedia_content_key() == null || policy.getMedia_content_key().isEmpty()) &&
                                    (policy.getCuid() == null || policy.getCuid().isEmpty());
                        }).collect(Collectors.toList()).get(0);
                    } else {
                        jwtPolicy = jwtPolicies.stream().filter(policy -> "default".equals(policy.getChannel_key()))
                                .collect(Collectors.toList()).get(0);
                    }
                }
            }
        }

        VodTokenGenerator vodTokenGenerator = new VodTokenGenerator();
        McGenerator mcGenerator = new McGenerator();

        mcGenerator = mcGenerator
                .mckey(playerReq.getMck())
                .seek(jwtPolicy.isSeek())
                .disable_playrate(jwtPolicy.isDisable_playrate())
                .disable_nscreen(jwtPolicy.isDisable_nscreen());
        if (jwtPolicy.getSeekable_end() > -1) {
            mcGenerator = mcGenerator.seekable_end((int) jwtPolicy.getSeekable_end());
        }
        if (jwtPolicy.getStart_time() > -1) {
            mcGenerator = mcGenerator.start_time((int) jwtPolicy.getStart_time());
        }
        if (jwtPolicy.getEnd_time() > -1) {
            mcGenerator = mcGenerator.end_time((int) jwtPolicy.getEnd_time());
        }
        if (jwtPolicy.isEnable_video_warter_marking()) {
            mcGenerator.code_kind(jwtPolicy.getCode_kind());
            mcGenerator.alpha(jwtPolicy.getAlpha());
            mcGenerator.font_size(jwtPolicy.getFont_size());
            mcGenerator.font_color(jwtPolicy.getFont_color());
            mcGenerator.show_time(jwtPolicy.getShow_time());
            mcGenerator.hide_time(jwtPolicy.getHide_time());
            mcGenerator.enable_html5_player(jwtPolicy.isEnable_html5_player());
        }
        Mc main = mcGenerator.build();

        vodTokenGenerator = vodTokenGenerator.cuid(playerReq.getCuid())
                .expt(new Date().getTime() / 1000 + jwtPolicy.getExpt());

        if (jwtPolicy.isIntro()) {
            vodTokenGenerator = vodTokenGenerator.mc((new McGenerator()).intr(true).mckey(jwtPolicy.getIntro_mck()).build());
        }
        vodTokenGenerator.mc(main);
        if (jwtPolicy.isOutro()) {
            vodTokenGenerator = vodTokenGenerator.mc((new McGenerator()).mckey(jwtPolicy.getOutro_mck()).build());
        }
        vodTokenGenerator.secret_key(KollusConstant.SECRET_KEY);

        String playerUrl = "";
        String token = "";

        try {
            playerUrl = UrlFactory.create(UrlType.VOD, StreamType.STREAMING, vodTokenGenerator, KollusConstant.CUSTOM_KEY, null);
            token = vodTokenGenerator.toJson();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        HashMap<String, Object> play = new HashMap<>();
        play.put("url", playerUrl);
        play.put("payload", token);
        return play;
    }


    @PostMapping("/download")
    public HashMap download(
            @RequestHeader("User-Agent") String useragent,
            @RequestBody List<DownloadReqVO> req) {


        HashMap<String, Object> download = new HashMap<>();
        Parser uaPaser = new Parser();
        Client client = uaPaser.parse(useragent);
//        if(client.os.family.toLowerCase().contains("mac")){
//            download.put("url", "");
//            return download;
//        }
        List<String> downloadList = req.stream().map(downloadReqVO -> {


            List<JwtPolicy> jwtPolicies = jwtPolicyRepository.findJwtPolicyForPlayerUrl(downloadReqVO.getChannel_key());
            JwtPolicy jwtPolicy = null;
            if (jwtPolicies.size() == 1) {
                //Default Policy
                jwtPolicy = jwtPolicies.get(0);
            } else {
                long matchedMckAndCuid = jwtPolicies.stream().filter(policy -> {
                    return downloadReqVO.getMck().equals(policy.getMedia_content_key()) &&
                            downloadReqVO.getCuid().equals(policy.getCuid());
                }).count();
                if (matchedMckAndCuid == 1) {
                    jwtPolicy = jwtPolicies.stream().filter(policy -> {
                        return downloadReqVO.getMck().equals(policy.getMedia_content_key()) &&
                                downloadReqVO.getCuid().equals(policy.getCuid());
                    }).collect(Collectors.toList()).get(0);
                } else {
                    long matchedMck = jwtPolicies.stream().filter(policy -> {
                        return downloadReqVO.getMck().equals(policy.getMedia_content_key()) &&
                                (policy.getCuid() == null || policy.getCuid().isEmpty());
                    }).count();
                    if (matchedMck == 1) {
                        jwtPolicy = jwtPolicies.stream().filter(policy -> {
                            return downloadReqVO.getMck().equals(policy.getMedia_content_key()) &&
                                    (policy.getCuid() == null || policy.getCuid().isEmpty());
                        }).collect(Collectors.toList()).get(0);
                    } else {
                        long matchedChannel = jwtPolicies.stream().filter(policy -> {
                            return downloadReqVO.getChannel_key().equals(policy.getChannel_key()) &&
                                    (policy.getMedia_content_key() == null || policy.getMedia_content_key().isEmpty()) &&
                                    (policy.getCuid() == null || policy.getCuid().isEmpty());
                        }).count();
                        if (matchedChannel == 1) {
                            jwtPolicy = jwtPolicies.stream().filter(policy -> {
                                return downloadReqVO.getChannel_key().equals(policy.getChannel_key()) &&
                                        (policy.getMedia_content_key() == null || policy.getMedia_content_key().isEmpty()) &&
                                        (policy.getCuid() == null || policy.getCuid().isEmpty());
                            }).collect(Collectors.toList()).get(0);
                        } else {
                            jwtPolicy = jwtPolicies.stream().filter(policy -> "default".equals(policy.getChannel_key()))
                                    .collect(Collectors.toList()).get(0);
                        }
                    }
                }
            }

            VodTokenGenerator vodTokenGenerator = new VodTokenGenerator();
            McGenerator mcGenerator = new McGenerator();

            mcGenerator = mcGenerator
                    .mckey(downloadReqVO.getMck())
                    .seek(jwtPolicy.isSeek())
                    .disable_playrate(jwtPolicy.isDisable_playrate())
                    .disable_nscreen(jwtPolicy.isDisable_nscreen());
            if (jwtPolicy.getSeekable_end() > -1) {
                mcGenerator = mcGenerator.seekable_end((int) jwtPolicy.getSeekable_end());
            }
            if (jwtPolicy.getStart_time() > -1) {
                mcGenerator = mcGenerator.start_time((int) jwtPolicy.getStart_time());
            }
            if (jwtPolicy.getEnd_time() > -1) {
                mcGenerator = mcGenerator.end_time((int) jwtPolicy.getEnd_time());
            }
            if (jwtPolicy.isEnable_video_warter_marking()) {
                mcGenerator.code_kind(jwtPolicy.getCode_kind());
                mcGenerator.alpha(jwtPolicy.getAlpha());
                mcGenerator.font_size(jwtPolicy.getFont_size());
                mcGenerator.font_color(jwtPolicy.getFont_color());
                mcGenerator.show_time(jwtPolicy.getShow_time());
                mcGenerator.hide_time(jwtPolicy.getHide_time());
                mcGenerator.enable_html5_player(jwtPolicy.isEnable_html5_player());
            }
            Mc main = mcGenerator.build();

            vodTokenGenerator = vodTokenGenerator.cuid(downloadReqVO.getCuid())
                    .expt(new Date().getTime() / 1000 + jwtPolicy.getExpt());

            if (jwtPolicy.isIntro()) {
                vodTokenGenerator = vodTokenGenerator.mc((new McGenerator()).intr(true).mckey(jwtPolicy.getIntro_mck()).build());
            }
            vodTokenGenerator.mc(main);
            if (jwtPolicy.isOutro()) {
                vodTokenGenerator = vodTokenGenerator.mc((new McGenerator()).mckey(jwtPolicy.getOutro_mck()).build());
            }
            vodTokenGenerator.secret_key(KollusConstant.SECRET_KEY);

            String playerUrl = "";



            try {
                playerUrl = UrlFactory.create(UrlType.VOD, StreamType.INFO, vodTokenGenerator, KollusConstant.CUSTOM_KEY, null);
                playerUrl = "url=" + URLEncoder.encode(playerUrl, StandardCharsets.UTF_8.toString());
                if (downloadReqVO.getFolder() != null && !downloadReqVO.getFolder().isEmpty()) {
                    playerUrl += "&folder=" + URLEncoder.encode(downloadReqVO.getFolder(), StandardCharsets.UTF_8.toString());
                }

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return playerUrl;

        }).collect(Collectors.toList());
        String strDownloadList = String.join("&", downloadList);
        if(client.os.family.toLowerCase().contains("windows")){
            download.put("url", "https://proxy.catoms.net:8389/download/start?"+strDownloadList);
        } else if(client.os.family.toLowerCase().contains("ios") || client.os.family.toLowerCase().contains("android")){
            download.put("url", "kollus://download?" + strDownloadList);
        }
        System.out.println(download.get("url"));
        return download;
    }

    @PostMapping("/querystring")
    public HashMap queryString(@RequestBody HashMap<String, Object> req) {
        HashMap<String, Object> query = new HashMap<>();
        QueryString queryStringBuilder = new QueryString();
        queryStringBuilder
                .pf(req.get("pf").toString())
                .autoplay((boolean) req.get("autoplay"))
                .mute((boolean) req.get("mute"))
                .download((boolean) req.get("download"))
                .title(req.get("title").toString())
                .t(Integer.parseInt(req.get("t").toString()))
                .s(Integer.parseInt(req.get("s").toString()))
                .force_exclusive_player((boolean) req.get("force_exclusive_player"))

                .uservalue0(req.get("uservalue0").toString())
                .uservalue1(req.get("uservalue1").toString())
                .uservalue2(req.get("uservalue2").toString())
                .uservalue3(req.get("uservalue3").toString())
                .uservalue4(req.get("uservalue4").toString())
                .uservalue5(req.get("uservalue5").toString())
                .uservalue6(req.get("uservalue6").toString())
                .uservalue7(req.get("uservalue7").toString())
                .uservalue8(req.get("uservalue8").toString())
                .uservalue9(req.get("uservalue9").toString())
                .brightness(Integer.parseInt(req.get("brightness").toString()))
                .contrast(Integer.parseInt(req.get("contrast").toString()))
                .saturation(Integer.parseInt(req.get("saturation").toString()))
                .mobile_return_url(req.get("mobile_return_url").toString())
                .mobile_folder_download(req.get("mobile_folder_download").toString())
                .pc_folder_download(req.get("pc_folder_download").toString())
                .play_downloaded_file((boolean) req.get("play_downloaded_file"))
                .buffer(Integer.parseInt(req.get("buffer").toString()))
                .show_controls_pause((boolean) req.get("show_controls_pause"))
                .show_poster_ended((boolean) req.get("show_poster_ended"))
                .loadcheck(Integer.parseInt(req.get("loadcheck").toString()));

                if(!req.get("pv").toString().isEmpty()){
                    queryStringBuilder.pv(PlayerVersion.valueOf(req.get("pv").toString()));
                }

                String strQuery = queryStringBuilder.build();

        query.put("query", strQuery);
        return query;
    }

    @PostMapping("/payload")
    public HashMap paylod(@RequestBody HashMap<String, Object> req) throws JsonProcessingException, NoSuchAlgorithmException, InvalidKeyException {
        ObjectMapper objectMapper = new ObjectMapper();
        String token = Encoder.createJwt(objectMapper.writeValueAsString(req), KollusConstant.SECRET_KEY);
        String playerUrl = UrlFactory.create(UrlType.VOD, StreamType.STREAMING, token, KollusConstant.CUSTOM_KEY, null);
        HashMap<String, Object> play = new HashMap<>();
        play.put("url", playerUrl);
        play.put("payload", objectMapper.writeValueAsString(req));
        return play;

    }

}
