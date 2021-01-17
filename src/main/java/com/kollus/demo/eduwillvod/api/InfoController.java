package com.kollus.demo.eduwillvod.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kollus.demo.eduwillvod.config.KollusConstant;
import com.kollus.demo.eduwillvod.config.UrlConstant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/info")
public class InfoController {


    @GetMapping("/categories")
    public List categories() {
        RestTemplate restTemplate = new RestTemplate();
        String url = UrlConstant.CATEGORY_API + "?access_token=" + KollusConstant.API_ACCESS_TOKEN;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, Object>> categories = new ArrayList<HashMap<String, Object>>();
        try {
            HashMap<String, Object> resMap = objectMapper.readValue(responseEntity.getBody(),
                    new TypeReference<HashMap<String, Object>>() {
                    });
            HashMap resultMap = (HashMap) resMap.get("result");
            HashMap items = (HashMap) resultMap.get("items");
            categories = (List<HashMap<String, Object>>) items.get("item");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return categories.stream().map(category -> {
            HashMap<String, Object> c = new HashMap<>();
            c.put("name", category.get("name"));
            c.put("key", category.get("key"));
            return c;
        }).collect(Collectors.toList());
    }

    @GetMapping("/profiles")
    public List profiles() {
        RestTemplate restTemplate = new RestTemplate();
        String url = UrlConstant.PROFILE_API + "?access_token=" + KollusConstant.API_ACCESS_TOKEN;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, Object>> profiles = new ArrayList<HashMap<String, Object>>();
        try {
            HashMap<String, Object> resMap = objectMapper.readValue(responseEntity.getBody(),
                    new TypeReference<HashMap<String, Object>>() {
                    });
            HashMap resultMap = (HashMap) resMap.get("result");
//            HashMap items = (HashMap) resultMap.get("items");
            profiles = (List<HashMap<String, Object>>) resultMap.get("items");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return profiles.stream().map(category -> {
            HashMap<String, Object> c = new HashMap<>();
            c.put("name", category.get("name"));
            c.put("key", category.get("key"));
            c.put("shared", category.get("key"));
            c.put("encrypt", category.get("key"));
            return c;
        }).collect(Collectors.toList());
    }


    @GetMapping("/channels")
    public List channels() {
        RestTemplate restTemplate = new RestTemplate();
        String url = UrlConstant.CHANNEL_API + "?access_token=" + KollusConstant.API_ACCESS_TOKEN;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, Object>> categories = new ArrayList<HashMap<String, Object>>();
        try {
            HashMap<String, Object> resMap = objectMapper.readValue(responseEntity.getBody(),
                    new TypeReference<HashMap<String, Object>>() {
                    });
            HashMap resultMap = (HashMap) resMap.get("result");
            HashMap items = (HashMap) resultMap.get("items");
            categories = (List<HashMap<String, Object>>) items.get("item");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return categories.stream().map(category -> {
            HashMap<String, Object> c = new HashMap<>();
            c.put("name", category.get("name"));
            c.put("key", category.get("key"));
            return c;
        }).collect(Collectors.toList());
    }

    //
    @GetMapping("/contents/category/{category}")
    public List mediaByCategory(@PathVariable("category") String category) {
        RestTemplate restTemplate = new RestTemplate();
        String url = UrlConstant.CATEGORY_CONTENTS_API + "?access_token=" + KollusConstant.API_ACCESS_TOKEN
                + "&category_key=" + category;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, Object>> mediacontents = new ArrayList<HashMap<String, Object>>();
        try {
            HashMap<String, Object> resMap = objectMapper.readValue(responseEntity.getBody(),
                    new TypeReference<HashMap<String, Object>>() {
                    });
            HashMap resultMap = (HashMap) resMap.get("result");
            if (Integer.parseInt(resultMap.get("count").toString()) > 0) {
                HashMap items = (HashMap) resultMap.get("items");
                mediacontents = (List<HashMap<String, Object>>) items.get("item");
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return mediacontents.stream().map(content -> {
//            poster_url: 'http://hdyang2.video.kr.kollus.com/kr/snapshot/hdyang2/20201227/23/79118041.jpg',
//                    title: 'example1',
//                    upload_file_key: 'abc-1234',
//                    use_encryption: true,
//                    is_audio: false,
//                    duration: 486,
//                    uploaded_at: 1610525534
            HashMap<String, Object> c = new HashMap<>();
            c.put("poster_url", content.get("poster_url"));
            c.put("title", content.get("title"));
            c.put("upload_file_key", content.get("upload_file_key"));
            c.put("use_encryption", content.get("use_encryption"));
            c.put("is_audio", content.get("kind").toString() == "2");
            c.put("duration", content.get("duration"));
            c.put("uploaded_at", content.get("created_at"));
            return c;
        }).collect(Collectors.toList());
    }


    @GetMapping("/contents/channel/{channel_key}")
    public List mediaByChannel(@PathVariable("channel_key") String channel_key){
        RestTemplate restTemplate = new RestTemplate();
        String url = UrlConstant.CHANNEL_CONTENTS_API + "?access_token=" + KollusConstant.API_ACCESS_TOKEN
                + "&channel_key=" + channel_key;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, Object>> mediacontents = new ArrayList<HashMap<String, Object>>();
        try {
            HashMap<String, Object> resMap = objectMapper.readValue(responseEntity.getBody(),
                    new TypeReference<HashMap<String, Object>>() {
                    });
            HashMap resultMap = (HashMap) resMap.get("result");
            if (Integer.parseInt(resultMap.get("count").toString()) > 0) {
                HashMap items = (HashMap) resultMap.get("items");
                mediacontents = (List<HashMap<String, Object>>) items.get("item");
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return mediacontents.stream().map(content -> {
            HashMap<String, Object> c = new HashMap<>();
            c.put("poster_url", content.get("poster_url"));
            c.put("title", content.get("title"));
            c.put("media_content_key", content.get("media_content_key"));
            return c;
        }).collect(Collectors.toList());
    }

    @GetMapping("/contents/ufk/{ufk}")
    public HashMap mediaByUFK(@PathVariable("ufk") String ufk) {

        RestTemplate restTemplate = new RestTemplate();
        String url = UrlConstant.CATEGORY_CONTENTS_API + "/" + ufk + "?access_token=" + KollusConstant.API_ACCESS_TOKEN;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Object> mediacontent = new HashMap<String, Object>();
        try {
            HashMap<String, Object> resMap = objectMapper.readValue(responseEntity.getBody(),
                    new TypeReference<HashMap<String, Object>>() {
                    });
            HashMap resultMap = (HashMap) resMap.get("result");
            HashMap item = (HashMap) resultMap.get("item");
            mediacontent.put("title", item.get("title"));
            mediacontent.put("upload_file_key", item.get("upload_file_key"));
            List<HashMap<String, Object>> tr_files = (List<HashMap<String, Object>>) item.get("transcoding_files");
            mediacontent.put("profiles", tr_files.stream().map(tr -> {
                HashMap<String, Object> trMap = new HashMap<>();
                trMap.put("name", tr.get("profile_name"));
                trMap.put("key", tr.get("profile_key"));
                trMap.put("size", ((HashMap)((HashMap) tr.get("media_information")).get("file")).get("filesize"));
                return trMap;
            }).collect(Collectors.toList()));
            mediacontent.put("title", item.get("title"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return mediacontent;

    }
//
//    @GetMapping("/contents/mck/{mck}")
//    public String mediaByMCK(){}


}
