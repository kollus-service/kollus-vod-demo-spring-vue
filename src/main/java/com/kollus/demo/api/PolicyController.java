package com.kollus.demo.api;


import com.kollus.demo.model.DrmCallbackPolicy;
import com.kollus.demo.model.JwtPolicy;
import com.kollus.demo.model.PlayCallbackPolicy;
import com.kollus.demo.repository.DrmCallbackPolicyRepository;
import com.kollus.demo.repository.JwtPolicyRepository;
import com.kollus.demo.repository.PlayCallbackPolicyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/policy")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PolicyController {

    @Autowired
    JwtPolicyRepository jwtPolicyRepository;
    @Autowired
    PlayCallbackPolicyRepository playCallbackPolicyRepository;
    @Autowired
    DrmCallbackPolicyRepository drmCallbackPolicyRepository;

    @GetMapping("/channel/{channel_key}")
    public HashMap getPolicy(@PathVariable("channel_key") String channel_key) {

        HashMap<String, Object> policies = new HashMap<>();
        List<JwtPolicy> jwtPolicies = jwtPolicyRepository.findJwtPolicyByChannel_keyWithDefault(channel_key);
        if (jwtPolicies.size() == 1) {
            policies.put("jwt", jwtPolicies.get(0));
        } else {
            JwtPolicy jwtPolicy = jwtPolicies.stream().filter(j -> !"default".equals(j.getChannel_key())).findFirst().get();
            policies.put("jwt", jwtPolicy);
        }

        List<PlayCallbackPolicy> playCallbackPolicies = playCallbackPolicyRepository.findPlayCallbackPolicyByChannel_keyWithDefault(channel_key);
        if (playCallbackPolicies.size() == 1) {
            policies.put("playcallback", playCallbackPolicies.get(0));
        } else {
            PlayCallbackPolicy playCallbackPolicy = playCallbackPolicies.stream().filter(p -> !"default".equals(p.getChannel_key())).findFirst().get();
            policies.put("playcallbck", playCallbackPolicy);
        }

        List<DrmCallbackPolicy> drmCallbackPolicies = drmCallbackPolicyRepository.findDrmCallbackPolicyByChannel_keyWithDefault(channel_key);
        if (playCallbackPolicies.size() == 1) {
            policies.put("drmcallback", playCallbackPolicies.get(0));
        } else {
            PlayCallbackPolicy playCallbackPolicy = playCallbackPolicies.stream().filter(p -> !"default".equals(p.getChannel_key())).findFirst().get();
            policies.put("drmcallbck", playCallbackPolicy);
        }
        return policies;
    }

    @GetMapping("/channel/{channel_key}/mck/{media_content_key}")
    public HashMap getPolicyWithMck(@PathVariable("channel_key") String channel_key,
                                    @PathVariable("media_content_key") String media_content_key
    ) {

        HashMap<String, Object> policies = new HashMap<>();
        List<JwtPolicy> jwtPolicies = jwtPolicyRepository.findJwtPolicyByChannel_keyAndMckWithDefault(channel_key, media_content_key);
        if (jwtPolicies.size() == 1) {
            policies.put("jwt", jwtPolicies.get(0));
        } else {
            JwtPolicy jwtPolicy = jwtPolicies.stream().filter(j -> !"default".equals(j.getChannel_key())).findFirst().get();
            policies.put("jwt", jwtPolicy);
        }

        List<PlayCallbackPolicy> playCallbackPolicies = playCallbackPolicyRepository.findPlayCallbackPolicyByChannel_keyAndMckWithDefault(channel_key, media_content_key);
        if (playCallbackPolicies.size() == 1) {
            policies.put("playcallback", playCallbackPolicies.get(0));
        } else {
            PlayCallbackPolicy playCallbackPolicy = playCallbackPolicies.stream().filter(p -> !"default".equals(p.getChannel_key())).findFirst().get();
            policies.put("playcallbck", playCallbackPolicy);
        }

        List<DrmCallbackPolicy> drmCallbackPolicies = drmCallbackPolicyRepository.findDrmCallbackPolicyByChannel_keyAndMckWithDefault(channel_key, media_content_key);
        if (drmCallbackPolicies.size() == 1) {
            policies.put("drmcallback", drmCallbackPolicies.get(0));
        } else {
            DrmCallbackPolicy drmCallbackPolicy = drmCallbackPolicies.stream().filter(d -> !"default".equals(d.getChannel_key())).findFirst().get();
            policies.put("drmcallbck", drmCallbackPolicy);
        }
        return policies;
    }


    @GetMapping("/channel/{channel_key}/cuid/{cuid}")
    public HashMap getPolicyWithChannelAndCuid(@PathVariable("channel_key") String channel_key,
                                               @PathVariable("cuid") String cuid) {

        HashMap<String, Object> policies = new HashMap<>();
        List<JwtPolicy> jwtPolicies = jwtPolicyRepository.findJwtPolicyByChannel_keyAndCuidWithDefault(channel_key, cuid);
        if (jwtPolicies.size() == 1) {
            policies.put("jwt", jwtPolicies.get(0));
        } else {
            JwtPolicy jwtPolicy = jwtPolicies.stream().filter(j -> !"default".equals(j.getChannel_key())).findFirst().get();
            policies.put("jwt", jwtPolicy);
        }

        List<PlayCallbackPolicy> playCallbackPolicies = playCallbackPolicyRepository.findPlayCallbackPolicyByChannel_keyAndCuidWithDefault(channel_key, cuid);
        if (playCallbackPolicies.size() == 1) {
            policies.put("playcallback", playCallbackPolicies.get(0));
        } else {
            PlayCallbackPolicy playCallbackPolicy = playCallbackPolicies.stream().filter(p -> !"default".equals(p.getChannel_key())).findFirst().get();
            policies.put("playcallbck", playCallbackPolicy);
        }

        List<DrmCallbackPolicy> drmCallbackPolicies = drmCallbackPolicyRepository.findDrmCallbackPolicyByChannel_keyAndCuidWithDefault(channel_key, cuid);
        if (playCallbackPolicies.size() == 1) {
            policies.put("drmcallback", drmCallbackPolicies.get(0));
        } else {
            DrmCallbackPolicy drmCallbackPolicy = drmCallbackPolicies.stream().filter(d -> !"default".equals(d.getChannel_key())).findFirst().get();
            policies.put("drmcallbck", drmCallbackPolicy);
        }
        return policies;
    }


    @GetMapping("/cuid/{cuid}")
    public HashMap getPolicyWithCuid(@PathVariable("cuid") String cuid) {

        HashMap<String, Object> policies = new HashMap<>();
        List<JwtPolicy> jwtPolicies = jwtPolicyRepository.findJwtPoliciesByCuidWidthDefault(cuid);

        if (jwtPolicies.size() == 1) {
            policies.put("jwt", jwtPolicies.get(0));
        } else {
            JwtPolicy jwtPolicy = jwtPolicies.stream().filter(j -> !"default".equals(j.getChannel_key())).findFirst().get();
            policies.put("jwt", jwtPolicy);
        }

        List<PlayCallbackPolicy> playCallbackPolicies = playCallbackPolicyRepository.findPlayCallbackPoliciesByCuidWithDefault(cuid);
        if (playCallbackPolicies.size() == 1) {
            policies.put("playcallback", playCallbackPolicies.get(0));
        } else {
            PlayCallbackPolicy playCallbackPolicy = playCallbackPolicies.stream().filter(p -> !"default".equals(p.getChannel_key())).findFirst().get();
            policies.put("playcallbck", playCallbackPolicy);
        }

        List<DrmCallbackPolicy> drmCallbackPolicies = drmCallbackPolicyRepository.findDrmCallbackPoliciesByCuidWithDefault(cuid);
        if (drmCallbackPolicies.size() == 1) {
            policies.put("drmcallback", drmCallbackPolicies.get(0));
        } else {
            DrmCallbackPolicy drmCallbackPolicy = drmCallbackPolicies.stream().filter(d -> !"default".equals(d.getChannel_key())).findFirst().get();
            policies.put("drmcallbck", drmCallbackPolicy);
        }
        return policies;
    }

    @GetMapping("/channel/{channel_key}/mck/{media_content_key}/cuid/{cuid}")
    public HashMap getPolicyWithMck(@PathVariable("channel_key") String channel_key,
                                    @PathVariable("media_content_key") String media_content_key,
                                    @PathVariable("cuid") String cuid
    ) {

        HashMap<String, Object> policies = new HashMap<>();
        List<JwtPolicy> jwtPolicies = jwtPolicyRepository.findJwtPolicyByChannel_keyAndCuidAndMckWithDefault(channel_key, cuid, media_content_key);
        if (jwtPolicies.size() == 1) {
            policies.put("jwt", jwtPolicies.get(0));
        } else {
            JwtPolicy jwtPolicy = jwtPolicies.stream().filter(j -> !"default".equals(j.getChannel_key())).findFirst().get();
            policies.put("jwt", jwtPolicy);
        }

        List<PlayCallbackPolicy> playCallbackPolicies = playCallbackPolicyRepository.findPlayCallbackPolicyByChannel_keyAndCuidAndMckWithDefault(channel_key, cuid, media_content_key);
        if (playCallbackPolicies.size() == 1) {
            policies.put("playcallback", playCallbackPolicies.get(0));
        } else {
            PlayCallbackPolicy playCallbackPolicy = playCallbackPolicies.stream().filter(p -> !"default".equals(p.getChannel_key())).findFirst().get();
            policies.put("playcallbck", playCallbackPolicy);
        }

        List<DrmCallbackPolicy> drmCallbackPolicies = drmCallbackPolicyRepository.findDrmCallbackPolicyByChannel_keyAndCuidAndMckWithDefault(channel_key, cuid, media_content_key);
        if (drmCallbackPolicies.size() == 1) {
            policies.put("drmcallback", drmCallbackPolicies.get(0));
        } else {
            DrmCallbackPolicy drmCallbackPolicy = drmCallbackPolicies.stream().filter(d -> !"default".equals(d.getChannel_key())).findFirst().get();
            policies.put("drmcallbck", drmCallbackPolicy);
        }
        return policies;
    }


    @PostMapping("/jwt/channel/{channel_key}")
    public JwtPolicy saveJwtWithChannel(@PathVariable("channel_key") String channel_key, @RequestBody JwtPolicy jwtPolicy) {
        jwtPolicy.setChannel_key(channel_key);
        return jwtPolicyRepository.saveAndFlush(jwtPolicy);
    }

    @PostMapping("/playcallback/channel/{channel_key}")
    public PlayCallbackPolicy savePlayCallbacktWithChannel(@PathVariable("channel_key") String channel_key, @RequestBody PlayCallbackPolicy playCallbackPolicy) {
        playCallbackPolicy.setChannel_key(channel_key);
        return playCallbackPolicyRepository.saveAndFlush(playCallbackPolicy);
    }

    @PostMapping("/drmcallback/channel/{channel_key}")
    public DrmCallbackPolicy savedrmCallbacktWithChannel(@PathVariable("channel_key") String channel_key, @RequestBody DrmCallbackPolicy drmCallbackPolicy) {
        drmCallbackPolicy.setChannel_key(channel_key);
        return drmCallbackPolicyRepository.saveAndFlush(drmCallbackPolicy);
    }


    @PostMapping("/jwt/channel/{channel_key}/mck/{media_content_key}")
    public JwtPolicy saveJwtWithChannelAndMck(@PathVariable("channel_key") String channel_key,
                                              @PathVariable("media_content_key") String media_content_key,
                                              @RequestBody JwtPolicy jwtPolicy) {
        jwtPolicy.setChannel_key(channel_key);
        jwtPolicy.setMedia_content_key(media_content_key);
        return jwtPolicyRepository.saveAndFlush(jwtPolicy);
    }

    @PostMapping("/playcallback/channel/{channel_key}/mck/{media_content_key}")
    public PlayCallbackPolicy savePlayCallbackWithChannelAndMck(@PathVariable("channel_key") String channel_key,
                                                                @PathVariable("media_content_key") String media_content_key,
                                                                @RequestBody PlayCallbackPolicy playCallbackPolicy) {
        playCallbackPolicy.setChannel_key(channel_key);
        playCallbackPolicy.setMedia_content_key(media_content_key);
        return playCallbackPolicyRepository.saveAndFlush(playCallbackPolicy);
    }

    @PostMapping("/drmcallback/channel/{channel_key}/mck/{media_content_key}")
    public DrmCallbackPolicy saveDrmCallbackWithChannelAndMck(@PathVariable("channel_key") String channel_key,
                                                              @PathVariable("media_content_key") String media_content_key,
                                                              @RequestBody DrmCallbackPolicy drmCallbackPolicy) {
        drmCallbackPolicy.setChannel_key(channel_key);
        drmCallbackPolicy.setMedia_content_key(media_content_key);
        return drmCallbackPolicyRepository.saveAndFlush(drmCallbackPolicy);
    }


    @PostMapping("/jwt/channel/{channel_key}/mck/{media_content_key}/cuid/{cuid}")
    public JwtPolicy saveJwtWithChannelAndMckAndCuid(@PathVariable("channel_key") String channel_key,
                                                     @PathVariable("media_content_key") String media_content_key,
                                                     @PathVariable("cuid") String cuid,
                                                     @RequestBody JwtPolicy jwtPolicy) {
        jwtPolicy.setChannel_key(channel_key);
        jwtPolicy.setMedia_content_key(media_content_key);
        jwtPolicy.setCuid(cuid);
        return jwtPolicyRepository.saveAndFlush(jwtPolicy);
    }

    @PostMapping("/playcallback/channel/{channel_key}/mck/{media_content_key}/cuid/{cuid}")
    public PlayCallbackPolicy savePlayCallbackWithChannelAndMckCuid(@PathVariable("channel_key") String channel_key,
                                                                    @PathVariable("media_content_key") String media_content_key,
                                                                    @PathVariable("cuid") String cuid,
                                                                    @RequestBody PlayCallbackPolicy playCallbackPolicy) {
        playCallbackPolicy.setChannel_key(channel_key);
        playCallbackPolicy.setMedia_content_key(media_content_key);
        playCallbackPolicy.setCuid(cuid);
        return playCallbackPolicyRepository.saveAndFlush(playCallbackPolicy);
    }

    @PostMapping("/drmcallback/channel/{channel_key}/mck/{media_content_key}/cuid/{cuid}")
    public DrmCallbackPolicy saveDrmCallbackWithChannelAndMckAndCuid(@PathVariable("channel_key") String channel_key,
                                                                     @PathVariable("media_content_key") String media_content_key,
                                                                     @PathVariable("cuid") String cuid,
                                                                     @RequestBody DrmCallbackPolicy drmCallbackPolicy) {
        drmCallbackPolicy.setChannel_key(channel_key);
        drmCallbackPolicy.setMedia_content_key(media_content_key);
        drmCallbackPolicy.setCuid(cuid);
        return drmCallbackPolicyRepository.saveAndFlush(drmCallbackPolicy);
    }


    @PostMapping("/jwt/cuid/{cuid}")
    public JwtPolicy saveJwtWithCuid(@PathVariable("cuid") String cuid, @RequestBody JwtPolicy jwtPolicy) {
        jwtPolicy.setCuid(cuid);
        return jwtPolicyRepository.saveAndFlush(jwtPolicy);
    }
    @PostMapping("/playcallback/cuid/{cuid}")
    public PlayCallbackPolicy savePlayCallbacktWithCuid(@PathVariable("cuid") String cuid, @RequestBody PlayCallbackPolicy playCallbackPolicy) {
        playCallbackPolicy.setCuid(cuid);
        return playCallbackPolicyRepository.saveAndFlush(playCallbackPolicy);
    }
    @PostMapping("/drmcallback/cuid/{cuid}")
    public DrmCallbackPolicy savedrmCallbackWithCuid(@PathVariable("cuid") String cuid, @RequestBody DrmCallbackPolicy drmCallbackPolicy) {
        drmCallbackPolicy.setCuid(cuid);
        return drmCallbackPolicyRepository.saveAndFlush(drmCallbackPolicy);
    }


    @PostMapping("/jwt/channel/{channel_key}/cuid/{cuid}")
    public JwtPolicy saveJwtWithChannelAndCuid(@PathVariable("channel_key") String channel_key,
                                              @PathVariable("cuid") String cuid,
                                              @RequestBody JwtPolicy jwtPolicy) {
        jwtPolicy.setChannel_key(channel_key);
        jwtPolicy.setCuid(cuid);
        return jwtPolicyRepository.saveAndFlush(jwtPolicy);
    }

    @PostMapping("/playcallback/channel/{channel_key}/cuid/{cuid}")
    public PlayCallbackPolicy savePlayCallbackWithChannelAndCuid(@PathVariable("channel_key") String channel_key,
                                                                @PathVariable("cuid") String cuid,
                                                                @RequestBody PlayCallbackPolicy playCallbackPolicy) {
        playCallbackPolicy.setChannel_key(channel_key);
        playCallbackPolicy.setCuid(cuid);
        return playCallbackPolicyRepository.saveAndFlush(playCallbackPolicy);
    }

    @PostMapping("/drmcallback/channel/{channel_key}/cuid/{cuid}")
    public DrmCallbackPolicy saveDrmCallbackWithChannelAndCuid(@PathVariable("channel_key") String channel_key,
                                                              @PathVariable("cuid") String cuid,
                                                              @RequestBody DrmCallbackPolicy drmCallbackPolicy) {
        drmCallbackPolicy.setChannel_key(channel_key);
        drmCallbackPolicy.setCuid(cuid);
        return drmCallbackPolicyRepository.saveAndFlush(drmCallbackPolicy);
    }




}
