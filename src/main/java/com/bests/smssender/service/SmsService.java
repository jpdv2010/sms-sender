package com.bests.smssender.service;

public interface SmsService {
    void sendSms(String message, String number) throws Exception;

    void sendTwilio(String message, String number);
}
