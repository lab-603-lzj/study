package com.example.jwt.utils;

import com.example.jwt.entity.DefaultBody;
import com.example.jwt.entity.DefaultJwt;
import com.example.jwt.entity.JwtBody;
import com.example.jwt.entity.JwtHeader;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

public class JwtUtil {

    // 过期时间是3600秒，既是1个小时
    public static final long EXPIRATION = 3600L;

    // 选择了记住我之后的过期时间为7天
    public static final long EXPIRATION_REMEMBER = 604800L;

    // 密钥
    private static String secret;

    // 配置获取密钥
    @Value("${JWT.secret:tmp}")
    private String secretValue;

    @PostConstruct
    public void init(){
        secret = secretValue;
    }

    public static String createToken(String id,String username,String password,boolean isRememberMe){
        long expiration = isRememberMe?EXPIRATION_REMEMBER:EXPIRATION;
        JwtBody jwtBody = JwtBody.builder().setExpiration(expiration).build();

        DefaultJwt<JwtHeader, JwtBody> defaultJwt = new DefaultJwt<>(jwtBody);
        return defaultJwt.getToken(secret);
    }
}
