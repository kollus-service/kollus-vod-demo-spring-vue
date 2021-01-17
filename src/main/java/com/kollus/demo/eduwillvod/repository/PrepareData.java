package com.kollus.demo.eduwillvod.repository;


import com.kollus.demo.eduwillvod.model.DrmCallbackPolicy;
import com.kollus.demo.eduwillvod.model.JwtPolicy;
import com.kollus.demo.eduwillvod.model.PlayCallbackPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class PrepareData implements ApplicationListener<ContextRefreshedEvent> {


    @Autowired
    private JwtPolicyRepository jwtPolicyRepository;
    @Autowired
    private PlayCallbackPolicyRepository playCallbackPolicyRepository;
    @Autowired
    private DrmCallbackPolicyRepository drmCallbackPolicyRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        JwtPolicy jwtPolicy = new JwtPolicy();
        PlayCallbackPolicy playCallbackPolicy = new PlayCallbackPolicy();
        DrmCallbackPolicy drmCallbackPolicy = new DrmCallbackPolicy();
        jwtPolicyRepository.saveAndFlush( jwtPolicy);
        playCallbackPolicyRepository.saveAndFlush(playCallbackPolicy);
        drmCallbackPolicyRepository.saveAndFlush(drmCallbackPolicy);
    }
}
