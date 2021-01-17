package com.kollus.demo.eduwillvod.callback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kollus.demo.eduwillvod.config.KollusConstant;
import com.kollus.demo.eduwillvod.vo.DrmCallbackReqVO;
import com.kollus.demo.eduwillvod.vo.PlayCallbackReqVO;

import java.util.HashMap;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/callback")
@CrossOrigin("*")
public class PlayerCallback {

    @PostMapping(path = "/play",
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity play(PlayCallbackReqVO req) {

        System.out.println(req);

        String policy = null;
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Kollus-UserKey", KollusConstant.CUSTOM_KEY);


        return ResponseEntity.ok()
                .headers(headers)
                .body("");
    }

    @PostMapping(path = "/drm",
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity drm(@RequestParam HashMap req) {
        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println(req.get("items"));

        try {
            List<DrmCallbackReqVO> list = objectMapper.readValue(req.get("items").toString(), new TypeReference<List<DrmCallbackReqVO>>(){});
            System.out.println(list.get(0));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String policy = null;
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Kollus-UserKey", KollusConstant.CUSTOM_KEY);


        return ResponseEntity.ok()
                .headers(headers)
                .body("");
    }

    @PostMapping("/lms")
    public ResponseEntity lms(@RequestBody MultiValueMap<String, String> req) {

        String policy = null;
        ResponseEntity responseEntity = new ResponseEntity(policy, HttpStatus.OK);
        responseEntity.getHeaders().set("X-Kollus-UserKey", KollusConstant.CUSTOM_KEY);
        return responseEntity;
    }
}
