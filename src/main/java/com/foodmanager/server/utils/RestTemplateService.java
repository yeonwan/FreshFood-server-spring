package com.foodmanager.server.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RestTemplateService {

    public String getJsonData() {
        return RestTemplateUtils.getJsonResponse();
    }

    public ResponseEntity<String> getEntity(String key) {
        return RestTemplateUtils.getResponseEntity(key);
    }

    public ResponseEntity<String> addData() {
        return RestTemplateUtils.post();
    }
}