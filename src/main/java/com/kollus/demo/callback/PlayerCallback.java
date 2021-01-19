package com.kollus.demo.callback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kollus.demo.config.KollusConstant;
import com.kollus.demo.config.UrlConstant;
import com.kollus.demo.model.*;
import com.kollus.demo.repository.DrmCallbackPolicyRepository;
import com.kollus.demo.repository.LmsDataRepository;
import com.kollus.demo.repository.LogsRepository;
import com.kollus.demo.repository.PlayCallbackPolicyRepository;
import com.kollus.demo.vo.*;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.kollus.jwt.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/callback")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PlayerCallback {

    @Autowired
    private PlayCallbackPolicyRepository playCallbackPolicyRepository;
    @Autowired
    private DrmCallbackPolicyRepository drmCallbackPolicyRepository;
    @Autowired
    private LogsRepository logsRepository;
    @Autowired
    private LmsDataRepository lmsDataRepository;

    @PostMapping(path = "/play",
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity play(PlayCallbackReqVO req) throws JsonProcessingException, NoSuchAlgorithmException, InvalidKeyException {

        System.out.println(req);

        RestTemplate restTemplate = new RestTemplate();
        String url = UrlConstant.CONTENT_MCK_API + "/" + req.getMedia_content_key() + "?access_token=" + KollusConstant.API_ACCESS_TOKEN;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Object> channelMap = objectMapper.readValue(responseEntity.getBody(), new TypeReference<HashMap<String, Object>>() {
        });
        String channel_key = ((HashMap) ((HashMap) channelMap.get("data")).get("channel")).get("key").toString();

        List<PlayCallbackPolicy> playCallbackPolicies = playCallbackPolicyRepository.findPlayCallbackPoliciesForCallback(channel_key);
        PlayCallbackPolicy playCallbackPolicy = null;

        if (playCallbackPolicies.size() == 1) {
            //Default Policy
            playCallbackPolicy = playCallbackPolicies.get(0);
        } else {
            long matchedMckAndCuid = playCallbackPolicies.stream().filter(policy -> {
                return req.getMedia_content_key().equals(policy.getMedia_content_key()) &&
                        req.getClient_user_id().equals(policy.getCuid());
            }).count();
            if (matchedMckAndCuid == 1) {
                playCallbackPolicy = playCallbackPolicies.stream().filter(policy -> {
                    return req.getMedia_content_key().equals(policy.getMedia_content_key()) &&
                            req.getClient_user_id().equals(policy.getCuid());
                }).collect(Collectors.toList()).get(0);
            } else {
                long matchedMck = playCallbackPolicies.stream().filter(policy -> {
                    return req.getMedia_content_key().equals(policy.getMedia_content_key()) &&
                            (policy.getCuid() == null || policy.getCuid().isEmpty());
                }).count();
                if (matchedMck == 1) {
                    playCallbackPolicy = playCallbackPolicies.stream().filter(policy -> {
                        return req.getMedia_content_key().equals(policy.getMedia_content_key()) &&
                                (policy.getCuid() == null || policy.getCuid().isEmpty());
                    }).collect(Collectors.toList()).get(0);
                } else {
                    long matchedChannel = playCallbackPolicies.stream().filter(policy -> {
                        return channel_key.equals(policy.getChannel_key()) &&
                                (policy.getMedia_content_key() == null || policy.getMedia_content_key().isEmpty()) &&
                                (policy.getCuid() == null || policy.getCuid().isEmpty());
                    }).count();
                    if (matchedChannel == 1) {
                        playCallbackPolicy = playCallbackPolicies.stream().filter(policy -> {
                            return channel_key.equals(policy.getChannel_key()) &&
                                    (policy.getMedia_content_key() == null || policy.getMedia_content_key().isEmpty()) &&
                                    (policy.getCuid() == null || policy.getCuid().isEmpty());
                        }).collect(Collectors.toList()).get(0);
                    } else {
                        playCallbackPolicy = playCallbackPolicies.stream().filter(policy -> "default".equals(policy.getChannel_key()))
                                .collect(Collectors.toList()).get(0);
                    }
                }
            }
        }

        System.out.println(playCallbackPolicy);
        PlayCallbackResDataVO playCallbackResDataVO = new PlayCallbackResDataVO();
        playCallbackResDataVO.setResult(playCallbackPolicy.getResult());
        playCallbackResDataVO.setMessage(playCallbackPolicy.getMessage());
        if ("1".equals(req.getKind())) {
            playCallbackResDataVO.setExpiration_date((int) (playCallbackPolicy.getExpiration_date() / 1000));
            playCallbackResDataVO.setVmcheck(playCallbackPolicy.getVmcheck());
            playCallbackResDataVO.setDisable_tvout(playCallbackPolicy.getDisable_tvout());
            playCallbackResDataVO.setExpiration_playtime(playCallbackPolicy.getExpiration_playtime());
            if (playCallbackPolicy.getStart_time() > -1 && playCallbackPolicy.getEnd_time() > -1) {
                PlaySectionVO playSectionVO = new PlaySectionVO();
                playSectionVO.setStart_time(playCallbackPolicy.getStart_time());
                playSectionVO.setEnd_time(playCallbackPolicy.getEnd_time());
                playCallbackResDataVO.setPlay_section(playSectionVO);
            }
        } else if ("3".equals(req.getKind())) {
            playCallbackResDataVO.setContent_expired(playCallbackPolicy.getContent_expired());
        }

        PlayCallbackResVO playCallbackResVO = new PlayCallbackResVO();
        playCallbackResVO.setData(playCallbackResDataVO);

        String body = Encoder.createJwt(objectMapper.writeValueAsString(playCallbackResVO), KollusConstant.SECRET_KEY);

        Logs logs = new Logs();
        logs.setLog_at((new Date()).getTime());
        logs.setType("playcallback");
        logs.setReq(objectMapper.writeValueAsString(req));
        logs.setRes(objectMapper.writeValueAsString(playCallbackResVO));
        logsRepository.saveAndFlush(logs);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Kollus-UserKey", KollusConstant.CUSTOM_KEY);
        return ResponseEntity.ok()
                .headers(headers)
                .body(body);
    }

    @PostMapping(path = "/drm",
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity drm(@RequestParam HashMap req) throws JsonProcessingException, NoSuchAlgorithmException, InvalidKeyException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<DrmCallbackReqVO> list = null;
        List<DrmCallbackResDataVO> data = new ArrayList<>();
        try {
            list = objectMapper.readValue(req.get("items").toString(), new TypeReference<List<DrmCallbackReqVO>>() {
            });
            list.forEach(item -> {
                RestTemplate restTemplate = new RestTemplate();
                String url = UrlConstant.CONTENT_MCK_API + "/" + item.getMedia_content_key() + "?access_token=" + KollusConstant.API_ACCESS_TOKEN;
                ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

                HashMap<String, Object> channelMap = null;
                try {
                    channelMap = objectMapper.readValue(responseEntity.getBody(), new TypeReference<HashMap<String, Object>>() {
                    });
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                String channel_key = ((HashMap) ((HashMap) channelMap.get("data")).get("channel")).get("key").toString();

                List<DrmCallbackPolicy> drmCallbackPolicies = drmCallbackPolicyRepository.findDrmCallbackPolicyForCallback(channel_key);
                DrmCallbackPolicy drmCallbackPolicy = null;

                if (drmCallbackPolicies.size() == 1) {
                    //Default Policy
                    drmCallbackPolicy = drmCallbackPolicies.get(0);
                } else {
                    long matchedMckAndCuid = drmCallbackPolicies.stream().filter(policy -> {
                        return item.getMedia_content_key().equals(policy.getMedia_content_key()) &&
                                item.getClient_user_id().equals(policy.getCuid());
                    }).count();
                    if (matchedMckAndCuid == 1) {
                        drmCallbackPolicy = drmCallbackPolicies.stream().filter(policy -> {
                            return item.getMedia_content_key().equals(policy.getMedia_content_key()) &&
                                    item.getClient_user_id().equals(policy.getCuid());
                        }).collect(Collectors.toList()).get(0);
                    } else {
                        long matchedMck = drmCallbackPolicies.stream().filter(policy -> {
                            return item.getMedia_content_key().equals(policy.getMedia_content_key()) &&
                                    (policy.getCuid() == null || policy.getCuid().isEmpty());
                        }).count();
                        if (matchedMck == 1) {
                            drmCallbackPolicy = drmCallbackPolicies.stream().filter(policy -> {
                                return item.getMedia_content_key().equals(policy.getMedia_content_key()) &&
                                        (policy.getCuid() == null || policy.getCuid().isEmpty());
                            }).collect(Collectors.toList()).get(0);
                        } else {
                            long matchedChannel = drmCallbackPolicies.stream().filter(policy -> {
                                return channel_key.equals(policy.getChannel_key()) &&
                                        (policy.getMedia_content_key() == null || policy.getMedia_content_key().isEmpty()) &&
                                        (policy.getCuid() == null || policy.getCuid().isEmpty());
                            }).count();
                            if (matchedChannel == 1) {
                                drmCallbackPolicy = drmCallbackPolicies.stream().filter(policy -> {
                                    return channel_key.equals(policy.getChannel_key()) &&
                                            (policy.getMedia_content_key() == null || policy.getMedia_content_key().isEmpty()) &&
                                            (policy.getCuid() == null || policy.getCuid().isEmpty());
                                }).collect(Collectors.toList()).get(0);
                            } else {
                                drmCallbackPolicy = drmCallbackPolicies.stream().filter(policy -> "default".equals(policy.getChannel_key()))
                                        .collect(Collectors.toList()).get(0);
                            }
                        }
                    }
                }

                DrmCallbackResDataVO drmCallbackResDataVO = new DrmCallbackResDataVO();
                drmCallbackResDataVO.setResult(drmCallbackPolicy.getResult());
                drmCallbackResDataVO.setMessage(drmCallbackPolicy.getMessage());
                drmCallbackResDataVO.setMedia_content_key(item.getMedia_content_key());
                drmCallbackResDataVO.setKind(Integer.parseInt(item.getKind()));
                if (item.getKind() == "1") {
                    drmCallbackResDataVO.setExpiration_date((int) (drmCallbackPolicy.getExpiration_date() / 1000));
                    drmCallbackResDataVO.setExpiration_count((int) drmCallbackPolicy.getExpiration_count());
                    drmCallbackResDataVO.setExpiration_playtime(drmCallbackPolicy.getExpiration_playtime());
                    drmCallbackResDataVO.setExpiration_playtime_type(drmCallbackPolicy.getExpiration_playtime_type());
                    drmCallbackResDataVO.setExpiration_refresh_popup(drmCallbackPolicy.getExpiration_refresh_popup());
                    drmCallbackResDataVO.setVmcheck(drmCallbackPolicy.getVmcheck());
                    drmCallbackResDataVO.setCheck_abuse(drmCallbackPolicy.getCheck_abuse());
                    if (drmCallbackPolicy.getBookmark_download() == 1 || drmCallbackPolicy.getBookmark_readonly() == 1) {
                        OfflineBookmarkVO offlineBookmarkVO = new OfflineBookmarkVO();
                        offlineBookmarkVO.setDownload(drmCallbackPolicy.getBookmark_download());
                        offlineBookmarkVO.setReadonly(drmCallbackPolicy.getBookmark_readonly());
                        drmCallbackResDataVO.setOffline_bookmark(offlineBookmarkVO);
                    }
                } else if (item.getKind().equals("2")) {
                    drmCallbackResDataVO.setContent_delete(drmCallbackPolicy.getContent_delete());
                    drmCallbackResDataVO.setCheck_expiration_date((int) (drmCallbackPolicy.getCheck_expiration_date() / 1000));
                } else if (item.getKind().equals("3")) {
                    drmCallbackResDataVO.setSession_key(item.getSession_key());
                    drmCallbackResDataVO.setStart_at(Long.parseLong(item.getStart_at()));
                    drmCallbackResDataVO.setContent_expired(drmCallbackPolicy.getContent_expired());
                    drmCallbackResDataVO.setContent_delete(drmCallbackPolicy.getContent_delete());
                    drmCallbackResDataVO.setContent_expire_reset(drmCallbackPolicy.getContent_expire_reset());
                    drmCallbackResDataVO.setExpiration_date((int) (drmCallbackPolicy.getExpiration_date() / 1000));
                    drmCallbackResDataVO.setExpiration_count((int) drmCallbackPolicy.getExpiration_count());
                    drmCallbackResDataVO.setExpiration_playtime(drmCallbackPolicy.getExpiration_playtime());
                    drmCallbackResDataVO.setCheck_abuse(drmCallbackPolicy.getCheck_abuse());
                    drmCallbackResDataVO.setCheck_expiration_date((int) (drmCallbackPolicy.getCheck_expiration_date() / 1000));
                }
                data.add(drmCallbackResDataVO);
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        DrmCallbackResVO drmCallbackResVO = new DrmCallbackResVO();
        drmCallbackResVO.setData(data);
        String policy = Encoder.createJwt(objectMapper.writeValueAsString(drmCallbackResVO), KollusConstant.SECRET_KEY);
        Logs logs = new Logs();
        logs.setLog_at((new Date()).getTime());
        logs.setType("drmcallback");
        logs.setReq(objectMapper.writeValueAsString(list));
        logs.setRes(objectMapper.writeValueAsString(drmCallbackResVO));
        logsRepository.saveAndFlush(logs);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Kollus-UserKey", KollusConstant.CUSTOM_KEY);
        return ResponseEntity.ok()
                .headers(headers)
                .body(policy);
    }

    @PostMapping(path = "/lms",
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity lms(@RequestParam HashMap req) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Object> jsonData = objectMapper.readValue(req.get("json_data").toString(),
                new TypeReference<HashMap<String, Object>>() {
                });
        HashMap<String, Object> user_info = (HashMap<String, Object>) jsonData.get("user_info");
        HashMap<String, Object> content_info = (HashMap<String, Object>) jsonData.get("content_info");

        String cuid = user_info.get("client_user_id").toString();
        String device = user_info.get("device").toString();
        String media_content_key = content_info.get("media_content_key").toString();
        long start_at = Long.parseLong(content_info.get("start_at").toString());
        long last_play_at = Long.parseLong(content_info.get("last_play_at").toString());
        long play_time = Long.parseLong(content_info.get("playtime").toString());


        LmsData lmsData = lmsDataRepository.findByCuidAndMedia_content_keyAndStart_at(cuid, media_content_key, start_at);

        if (lmsData == null) {
            lmsData = new LmsData();
            lmsData.setCuid(cuid);
            lmsData.setMedia_content_key(media_content_key);
            lmsData.setStart_at(start_at);
        }
        lmsData.setDevice(device);
        lmsData.setLast_play_at(last_play_at);
        lmsData.setPlay_time(play_time);
        LmsData result = lmsDataRepository.saveAndFlush(lmsData);
        System.out.println(result);
        return ResponseEntity.ok()
                .body("");
    }
}
