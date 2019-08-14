package com.bests.smssender.controller;

import com.bests.smssender.data.dto.Content;
import com.bests.smssender.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("send-sms")
public class SendSmsController {

    @Autowired
    private SmsService smsService;

    @PostMapping
    public ResponseEntity send(@RequestBody Content content) throws Exception {
        smsService.testingTwilio(content.getMessage(), content.getNumber());
        return new ResponseEntity(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity exceptionHandler(Exception ex){
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
