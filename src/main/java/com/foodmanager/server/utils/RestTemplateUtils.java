package com.foodmanager.server.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Component
public class RestTemplateUtils {

    private static RestTemplate restTemplate;

    @Autowired
    public RestTemplateUtils(RestTemplate restTemplate) {
        RestTemplateUtils.restTemplate =restTemplate;
    }

    static String getJsonResponse(){
        return restTemplate.getForObject("http://localhost:8080/json", String.class);
    }

    static ResponseEntity<String> getResponseEntity(String key){
        //header setting
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authentication", key);
        HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(headers);
        Map<String, String> params = new HashMap<>();
        params.put("name", "jaeyeon");
        //순서대로 url, method, entity(header, params), return type
        return restTemplate.exchange("http://localhost:8080/entity?name={name}", HttpMethod.GET, httpEntity, String.class, params);
    }

    static ResponseEntity<String> post(){
        return restTemplate.postForEntity("http://localhost:8080/post", "Post Request", String.class);
    }
}
