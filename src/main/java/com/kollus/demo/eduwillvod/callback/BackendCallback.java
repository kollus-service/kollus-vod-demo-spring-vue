package com.kollus.demo.eduwillvod.callback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kollus.demo.eduwillvod.model.Logs;
import com.kollus.demo.eduwillvod.repository.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/callback")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BackendCallback {

    @Autowired
    private LogsRepository logsRepository;

    @PostMapping("/upload")
    public ResponseEntity upload(@RequestBody MultiValueMap req){
        log("upload", req);
        return new ResponseEntity("", HttpStatus.OK);
    }
    @PostMapping("/transcoding")
    public ResponseEntity transcoding(@RequestBody MultiValueMap req){
        log("transcoding", req);
        return new ResponseEntity("", HttpStatus.OK);
    }
    @PostMapping("/channelAdd")
    public ResponseEntity channelAdd(@RequestBody MultiValueMap req){
        log("channelAdd", req);
        return new ResponseEntity("", HttpStatus.OK);
    }
    @PostMapping("/channelDelete")
    public ResponseEntity channelDelete(@RequestBody MultiValueMap req){
        log("channelDelete", req);
        return new ResponseEntity("", HttpStatus.OK);
    }
    @PostMapping("/contentUpdate")
    public ResponseEntity contentUpdate(@RequestBody MultiValueMap req){
        log("contentUpdate", req);
        return new ResponseEntity("", HttpStatus.OK);
    }

    private void log(String type, MultiValueMap req) {
        ObjectMapper objectMapper = new ObjectMapper();
        Logs logs = new Logs();
        logs.setType(type);
        try {
            logs.setReq(objectMapper.writeValueAsString(req));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        logs.setRes("");
        logs.setLog_at((new Date()).getTime());
        logsRepository.saveAndFlush(logs);
    }
}
