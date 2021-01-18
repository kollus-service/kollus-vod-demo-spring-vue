package com.kollus.demo.eduwillvod.api;

import com.kollus.demo.eduwillvod.config.KollusConstant;
import com.kollus.demo.eduwillvod.config.UrlConstant;
import com.kollus.demo.eduwillvod.vo.UploadReqVO;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/upload")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UploadController {


    @PostMapping("/create")
    public String create(@RequestBody UploadReqVO req){

        System.out.println(req);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> reqBody = new LinkedMultiValueMap<>();
        if(req.getCategory_key() != null && !req.getCategory_key().isEmpty()){
            reqBody.set("category_key", req.getCategory_key());
        }
        reqBody.set("expire_time", String.format("%d", req.getExpire_time()));
        if(req.getIs_encryption_upload() == 1) {
            reqBody.set("is_encryption_upload", "1");
        }
        if(req.getIs_audio_upload() == 1) {
            reqBody.set("is_audio_upload", "1");
        }
        if(req.getIs_passthrough() == 1) {
            reqBody.set("is_passthrough", "1");
            reqBody.set("profile_key", req.getProfile_key());
        }
        if(req.getTitle() != null && !req.getTitle().isEmpty()) {
            reqBody.set("title", req.getTitle());
        }
        System.out.println(reqBody);
        HttpEntity httpEntity = new HttpEntity(reqBody, httpHeaders);
        String url = UrlConstant.UPLOAD_API + "?access_token=" + KollusConstant.API_ACCESS_TOKEN;
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        return responseEntity.getBody();
    }
}
