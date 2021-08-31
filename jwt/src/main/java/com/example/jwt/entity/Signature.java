package com.example.jwt.entity;



public class Signature {
    private String header;
    private String payload;
    private String secret;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
