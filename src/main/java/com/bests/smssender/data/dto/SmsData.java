package com.bests.smssender.data.dto;

public class SmsData {

    public SmsData(String key, String type, String number, String msg, String out) {
        this.key = key;
        this.type = type;
        this.number = number;
        this.msg = msg;
        this.out = out;
    }

    private String key;
    private String type;
    private String number;
    private String msg;
    private String out;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }
}
