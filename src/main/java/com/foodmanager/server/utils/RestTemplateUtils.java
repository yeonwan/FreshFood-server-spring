package com.foodmanager.server.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class RestTemplateUtils {

    private static RestTemplate restTemplate;

    @Autowired
    public RestTemplateUtils(RestTemplate restTemplate) {
        RestTemplateUtils.restTemplate =restTemplate;
    }

    static public <T> Object getJsonForObject(String uri, Class<T> clazz){
        return restTemplate.getForObject(uri, clazz);
    }

    static public ResponseEntity<String> getResponseEntity(String uri) throws ParseException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);//JSON 변환
        HttpEntity httpEntity = new HttpEntity(headers);
        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
    }


    static public ResponseEntity<String> postResponseEntity(String uri, String json) throws ParseException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);//JSON 변환
        HttpEntity<String> httpEntity = new HttpEntity<>(json, headers);
        return restTemplate.exchange(uri, HttpMethod.POST, httpEntity, String.class);
    }

    static ResponseEntity putResponseEntity(String uri){
        restTemplate.put(uri, "Post Request");
        return new ResponseEntity(HttpStatus.OK);
    }

    static ResponseEntity<String> postResponseEntity(String uri){
        return restTemplate.postForEntity(uri, "Post Request", String.class);
    }

}
