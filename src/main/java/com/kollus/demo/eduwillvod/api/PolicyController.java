package com.kollus.demo.eduwillvod.api;


import com.kollus.demo.eduwillvod.model.DrmCallbackPolicy;
import com.kollus.demo.eduwillvod.model.JwtPolicy;
import com.kollus.demo.eduwillvod.model.PlayCallbackPolicy;
import com.kollus.demo.eduwillvod.repository.DrmCallbackPolicyRepository;
import com.kollus.demo.eduwillvod.repository.JwtPolicyRepository;
import com.kollus.demo.eduwillvod.repository.PlayCallbackPolicyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.function.Supplier;
import java.util.List;
@RestController
@RequestMapping("/api/policy")
public class PolicyController {

    @Autowired
    JwtPolicyRepository jwtPolicyRepository;
    @Autowired
    PlayCallbackPolicyRepository playCallbackPolicyRepository;
    @Autowired
    DrmCallbackPolicyRepository drmCallbackPolicyRepository;
    @GetMapping("/channel/{channel_key}")
    public HashMap getPolicy(@PathVariable("channel_key")String channel_key){

        HashMap<String, Object> policies = new HashMap<>();
        List<JwtPolicy> jwtPolicies = jwtPolicyRepository.findJwtPolicyByChannel_keyWithDefault(channel_key);
        if(jwtPolicies.size() == 1){
            policies.put("jwt", jwtPolicies.get(0));
        }
        else {
            JwtPolicy jwtPolicy = jwtPolicies.stream().filter( j -> !j.getChannel_key().equals("default")).findFirst().get();
            policies.put("jwt", jwtPolicy);
        }

        List<PlayCallbackPolicy> playCallbackPolicies = playCallbackPolicyRepository.findPlayCallbackPolicyByChannel_keyWithDefault(channel_key);
        if(playCallbackPolicies.size() == 1){
            policies.put("playcallback", playCallbackPolicies.get(0));
        }
        else {
            PlayCallbackPolicy playCallbackPolicy = playCallbackPolicies.stream().filter(p -> !p.getChannel_key().equals("default")).findFirst().get();
            policies.put("playcallbck", playCallbackPolicy);
        }

        List<DrmCallbackPolicy> drmCallbackPolicies = drmCallbackPolicyRepository.findDrmCallbackPolicyByChannel_keyWithDefault(channel_key);
        if(playCallbackPolicies.size() == 1){
            policies.put("drmcallback", playCallbackPolicies.get(0));
        }
        else {
            PlayCallbackPolicy playCallbackPolicy = playCallbackPolicies.stream().filter(p -> !p.getChannel_key().equals("default")).findFirst().get();
            policies.put("drmcallbck", playCallbackPolicy);
        }
        return policies;
    }

    @GetMapping("/channel/{channel_key}/mck/{media_content_key}")
    public HashMap getPolicyWithMck(@PathVariable("channel_key")String channel_key,
                                    @PathVariable("media_content_key") String media_content_key
                                    ){

        HashMap<String, Object> policies = new HashMap<>();
        List<JwtPolicy> jwtPolicies = jwtPolicyRepository.findJwtPolicyByChannel_keyAndMckWithDefault(channel_key, media_content_key);
        if(jwtPolicies.size() == 1){
            policies.put("jwt", jwtPolicies.get(0));
        }
        else {
            JwtPolicy jwtPolicy = jwtPolicies.stream().filter( j -> !j.getChannel_key().equals("default")).findFirst().get();
            policies.put("jwt", jwtPolicy);
        }

        List<PlayCallbackPolicy> playCallbackPolicies = playCallbackPolicyRepository.findPlayCallbackPolicyByChannel_keyAndMckWithDefault(channel_key, media_content_key);
        if(playCallbackPolicies.size() == 1){
            policies.put("playcallback", playCallbackPolicies.get(0));
        }
        else {
            PlayCallbackPolicy playCallbackPolicy = playCallbackPolicies.stream().filter(p -> !p.getChannel_key().equals("default")).findFirst().get();
            policies.put("playcallbck", playCallbackPolicy);
        }

        List<DrmCallbackPolicy> drmCallbackPolicies = drmCallbackPolicyRepository.findDrmCallbackPolicyByChannel_keyAndMckWithDefault(channel_key, media_content_key);
        if(playCallbackPolicies.size() == 1){
            policies.put("drmcallback", playCallbackPolicies.get(0));
        }
        else {
            PlayCallbackPolicy playCallbackPolicy = playCallbackPolicies.stream().filter(p -> !p.getChannel_key().equals("default")).findFirst().get();
            policies.put("drmcallbck", playCallbackPolicy);
        }
        return policies;
    }

    @PostMapping("/jwt/{channel_key}")
    public JwtPolicy saveJwt(@PathVariable("channel_key")String channel_key, @RequestBody JwtPolicy jwtPolicy){
        jwtPolicy.setChannel_key(channel_key);
        return jwtPolicyRepository.saveAndFlush(jwtPolicy);
    }

    @PostMapping("/playcallback/{channel_key}")
    public PlayCallbackPolicy saveJwt(@PathVariable("channel_key")String channel_key, @RequestBody PlayCallbackPolicy playCallbackPolicy){
        playCallbackPolicy.setChannel_key(channel_key);
        return playCallbackPolicyRepository.saveAndFlush(playCallbackPolicy);
    }
    @PostMapping("/drmcallback/{channel_key}")
    public DrmCallbackPolicy savedrmCallback(@PathVariable("channel_key")String channel_key, @RequestBody DrmCallbackPolicy drmCallbackPolicy){
        drmCallbackPolicy.setChannel_key(channel_key);
        return drmCallbackPolicyRepository.saveAndFlush(drmCallbackPolicy);
    }


    @PostMapping("/jwt/{channel_key}/mck/{media_content_key}")
    public JwtPolicy saveJwt(@PathVariable("channel_key")String channel_key,
                             @PathVariable("media_content_key")String media_content_key,
                             @RequestBody JwtPolicy jwtPolicy){
        jwtPolicy.setChannel_key(channel_key);
        jwtPolicy.setMedia_content_key(media_content_key);
        return jwtPolicyRepository.saveAndFlush(jwtPolicy);
    }

    @PostMapping("/playcallback/{channel_key}/mck/{media_content_key}")
    public PlayCallbackPolicy saveJwt(@PathVariable("channel_key")String channel_key,
                                      @PathVariable("media_content_key")String media_content_key,
                                      @RequestBody PlayCallbackPolicy playCallbackPolicy){
        playCallbackPolicy.setChannel_key(channel_key);
        playCallbackPolicy.setMedia_content_key(media_content_key);
        return playCallbackPolicyRepository.saveAndFlush(playCallbackPolicy);
    }
    @PostMapping("/drmcallback/{channel_key}/mck/{media_content_key}")
    public DrmCallbackPolicy savedrmCallback(@PathVariable("channel_key")String channel_key,
                                             @PathVariable("media_content_key")String media_content_key,
                                             @RequestBody DrmCallbackPolicy drmCallbackPolicy){
        drmCallbackPolicy.setChannel_key(channel_key);
        drmCallbackPolicy.setMedia_content_key(media_content_key);
        return drmCallbackPolicyRepository.saveAndFlush(drmCallbackPolicy);
    }
}
