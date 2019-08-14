package com.bests.smssender.service;

import com.bests.smssender.data.dto.SmsData;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

public class SmsServiceImpl implements SmsService {
    private ArrayList<Long> SMS_SUCCESS_STATUS_CODE = new ArrayList<Long>( Arrays.asList(200L,201L));
    private String SMS_API_URL = "http://api.smsempresa.com.br/send";

    public void SendSms( String message, String number ) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = SMS_API_URL;
        URI uri = new URI( baseUrl );

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<SmsData> request = new HttpEntity<>(getSmsData(message, number), headers);

        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

        if(!SMS_SUCCESS_STATUS_CODE.contains(result.getStatusCodeValue())){
            throw new Exception("Bad Request");
        }
    }

    private SmsData getSmsData(String message, String number) {
        return new SmsData("86888",
                "9",
                number,
                message,
                "json");
    }
}
