package com.ghd.utils;

import com.alibaba.fastjson.JSON;
import com.ghd.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;

/**
 * @author ghd-alem
 * @description
 * @date 2020/5/7 10:55
 */
public class JwtUtil {

    public static final long EXPIRATION_TIME = 3600_000_000L; // 1000 hour 有效期
    //public static final long EXPIRATION_TIME = 10_000L; // 10m 有效期
    public static final String SECRET = "lambeter";//  密钥

    //解析
    public static Claims parseJWT(String jsonWebToken) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    //和时间有关的token
    /*public static String generateToken(Integer id, Date loginTime) {
        return MD5Util.getMD5ofStr((long)id + (loginTime == null ? 0L : Math.round(loginTime.getTime()/1000.0)) + SECRET);
    }*/

    //生成
    public static String createJWT(User user) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //当前时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        //添加构成JWT的参数
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", user.getId());
        params.put("username", user.getUsername());
        params.put("password",user.getPassword());
        JwtBuilder builder = Jwts.builder().setHeaderParam("type", "JWT")
                .setClaims(params)
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        long expMillis = nowMillis + EXPIRATION_TIME;
        Date exp = new Date(expMillis);
        builder.setExpiration(exp).setNotBefore(now);
        //生成JWT
        String jwtTokenStr = builder.compact();
        return jwtTokenStr;
    }

}