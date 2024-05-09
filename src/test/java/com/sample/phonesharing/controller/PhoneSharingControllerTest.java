package com.sample.phonesharing.controller;

import com.sample.phonesharing.PhonesharingApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {PhonesharingApplication.class})
class PhoneSharingControllerTest {
    @Value("${local.server.port}")
    private int localServerPort;

    @Test
    void getPhone() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<String> forEntity = testRestTemplate.getForEntity("http://localhost:" + localServerPort + "/phones/Samsung%20Galaxy%20S9", String.class);

        Assertions.assertNotNull(forEntity);
        Assertions.assertEquals(HttpStatus.OK, forEntity.getStatusCode());
    }

    @Test
    void bookPhone() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> request = new HttpEntity<>("Name", headers);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("bookedBy", "Name");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<String> forEntity = testRestTemplate.postForEntity("http://localhost:" + localServerPort + "/phones/Samsung%20Galaxy%20S9/book", entity, String.class);
        Assertions.assertNotNull(forEntity);
        Assertions.assertEquals(HttpStatus.OK, forEntity.getStatusCode());
    }

    @Test
    void returnPhone() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<String> forEntity = testRestTemplate.getForEntity("http://localhost:"+localServerPort+"/phones/Samsung%20Galaxy%20S9/return",String.class);
        Assertions.assertNotNull(forEntity);
        Assertions.assertEquals(HttpStatus.OK, forEntity.getStatusCode());
    }
}