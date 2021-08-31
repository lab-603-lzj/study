package com.example.jwt.entity;

import com.example.jwt.utils.Base64Util;

public class DefaultJwt<H,B> implements Jwt<JwtHeader,JwtBody>,IDefaultJwt {
    private JwtHeader header;

    private JwtBody body;

    private Signature signature;

    public DefaultJwt(String type,String alg, JwtBody body) {
        this.header = new JwtHeader(type,alg);
        this.body = body;
    }

    public DefaultJwt(JwtBody body){
        this.header = new JwtHeader();
        this.body = body;
    }

    @Override
    public JwtHeader getHeader() {
        return header;
    }

    @Override
    public JwtBody getBody() {
        return body;
    }

    @Override
    public String getToken(String secret) {
        if (header.getTyp().equals("JWT")) {
            String header64 = Base64Util.convertToBase64(header);
            String body64 = Base64Util.convertToBase64(body);
            signature.setHeader(header64);
            signature.setPayload(body64);
            signature.setSecret(secret);

        }else {
            throw new RuntimeException("不是JWT");
        }
        return null;
    }

    public String encryption(Signature signature){

        return null;
    }
}
