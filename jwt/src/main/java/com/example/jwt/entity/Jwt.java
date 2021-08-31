package com.example.jwt.entity;

public interface Jwt<H extends JwtHeader,B extends JwtBody> {
    /**
     *  获取头部
      */
    H getHeader();

    /**
     * 获取载荷
     */
    B getBody();

    String getToken(String secret);

}
