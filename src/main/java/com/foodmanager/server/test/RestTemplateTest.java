package com.foodmanager.server.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import com.foodmanager.server.utils.RestTemplateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import java.util.logging.Logger;


@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class RestTemplateTest {

    @Autowired
    private RestTemplateService restTemplateService;

    @Test
    public void json_test() {
        //when
        String jsonVo= restTemplateService.getJsonData();
        System.out.println("json_test"+ jsonVo);
    }

    @Test
    public void header_check() {
        //when
        ResponseEntity<String> responseEntity= restTemplateService.getEntity("LEMON");
        //then
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
    }

    @Test(expected = Unauthorized.class)
    public void header_check_fail() {
        //when
        ResponseEntity<String> responseEntity= restTemplateService.getEntity("fail");
        //then
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.UNAUTHORIZED));

    }
    @Test
    public void post_test() {
        //when
        ResponseEntity<String> responseEntity= restTemplateService.addData();
        //then
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
    }
}