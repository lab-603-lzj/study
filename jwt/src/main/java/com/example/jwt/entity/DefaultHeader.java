package com.example.jwt.entity;

public class DefaultHeader extends JwtHeader {
    /**
     * 构造器方式，要求必须设置
     * @param typ
     * @param alg
     */
    public DefaultHeader(String typ, String alg) {
        super(typ, alg);
    }
}
