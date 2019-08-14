package com.bests.smssender.service.impl;

import com.bests.smssender.data.dto.SmsData;
import com.bests.smssender.service.SmsService;
import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.AvailablePhoneNumberCountry;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.availablephonenumbercountry.Mobile;
import com.twilio.type.PhoneNumber;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class SmsServiceImpl implements SmsService {
    private ArrayList<Long> SMS_SUCCESS_STATUS_CODE = new ArrayList<Long>( Arrays.asList(200L,201L));
    private String SMS_API_URL = "http://api.smsempresa.com.br/send";
    private String KEY = "745T4QGBQM3GU6BYJ8ZBXQ7R";
    private static final String ACCOUNT_SID = "AC44cba8b0f1daad15e7fcfd278c83806f";
    private static final String AUTH_TOKEN = "2dbe68a5177c48ba5b4181e6a99ccb26";

    @Override
    public void sendSms(String message, String number) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = SMS_API_URL;
        URI uri = new URI( baseUrl );

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<SmsData> request = new HttpEntity<>(getSmsData(message, number), headers);

        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

        if(!SMS_SUCCESS_STATUS_CODE.contains(result.getStatusCodeValue())){
            throw new Exception("Bad Request");
        }
    }

    private SmsData getSmsData(String message, String number) {
        return new SmsData(KEY,
                "9",
                number,
                message,
                "json");
    }

    @Override
    public void testingTwilio(String message, String number){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message msg = Message.creator(new PhoneNumber(number),
                new PhoneNumber("+15005550006"),//"+553140421656"),
                message).create();

        Object status = msg.getStatus();

        System.out.println(msg.getSid());
    }
}