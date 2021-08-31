package com.example.jwt.entity;

import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 构造器方式，要求必须设置
 */
public class JwtHeader {

    private String typ;

    private String alg;

    public JwtHeader() {
        this.typ = "JWT";
        this.alg = SignatureAlgorithm.HS256.getValue();
    }

    public JwtHeader(String typ, String alg) {
        this.typ = typ;
        this.alg = alg;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getAlg() {
        return alg;
    }

    public void setAlg(String alg) {
        this.alg = alg;
    }
}
