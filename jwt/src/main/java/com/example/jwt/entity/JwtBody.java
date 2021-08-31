package com.example.jwt.entity;

import lombok.Data;

@Data
public abstract class JwtBody {
    /**
     * jwt签发者
     */
    private String issuer;

    /**
     * jwt所面向的用户
     */
    private String subject;

    /**
     * 接收jwt的一方
     */
    private String audit;

    /**
     * jwt的过期时间，这个过期时间必须大于签发时间
     */
    private long expiration;

    /**
     * 定义在什么时间之前，该jwt都是不可用的
     */
    private String nbf;

    /**
     * jwt的签发时间
     */
    private String issueAtTime;

    /**
     * jwt的唯一身份标识，主要用来作为一次性token，从而回避重放攻击
     */
    private String jsonTokenId;

    public static JwtBodyBuilder builder(){
        return new JwtBodyBuilder();
    }

    public static class JwtBodyBuilder {
        private JwtBody body = new DefaultBody();

        public JwtBodyBuilder setIssuer(String iss){
            body.setIssuer(iss);
            return this;
        }

        public JwtBodyBuilder setSubject(String sub){
            body.setSubject(sub);
            return this;
        }

        public JwtBodyBuilder setAudit(String aud){
            body.setAudit(aud);
            return this;
        }

        public JwtBodyBuilder setExpiration(long exp){
            body.setExpiration(exp);
            return this;
        }

        public JwtBodyBuilder setNbf(String nbf){
            body.setNbf(nbf);
            return this;
        }

        public JwtBodyBuilder setIssueAtTime(String iat){
            body.setIssueAtTime(iat);
            return this;
        }

        public JwtBodyBuilder setJsonTokenId(String jti){
            body.setJsonTokenId(jti);
            return this;
        }

        public JwtBody build(){
            return body;
        }

    }

}
