package com.tp.soft.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JwtUtil {
    static final String SECRET = "tzZhstCenter!@_#!123";
    static final String ISSUER = "tzZhstUser";
    static final String SUBJECT = "tzZhstSubject";


    /**
     * 生成默认key的token，key为id
     *
     * @param val
     * @return
     */
    public static String generateDefaultToken(String val) {
        String token = JWT.create()
                //.withIssuer(ISSUER)
                //.withSubject(SUBJECT)
                .withClaim("id", val)
                .withClaim("time",String.valueOf(System.currentTimeMillis()))
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    /**
     * 生成token，包含key值
     *
     * @param key
     * @param val
     * @return
     */
    public static String generateToken(String key, String val) {
        String token = JWT.create()
                //.withIssuer(ISSUER)
                //.withSubject(SUBJECT)
                .withClaim(key, val)
                .withClaim("time", String.valueOf(System.currentTimeMillis()))
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    /**
     * 生成token，包含key值，需传入失效时间
     *
     * @param key
     * @param val
     * @param expiresDate
     * @return
     */
    public static String generateTokenWithExpires(String key, String val, Date expiresDate) {
        String token = JWT.create()
                //.withIssuer(ISSUER)
                //.withSubject(SUBJECT)
                .withClaim(key, val)
                .withClaim("time", String.valueOf(System.currentTimeMillis()))
                .withExpiresAt(expiresDate)
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    /**
     * 校验token，并获取加密参数
     *
     * @param token
     * @return
     */
    public static Map<String, Object> validateToken(String token) {
        Map<String, Object> resp = new HashMap<String, Object>();
        try {
            if (StringUtils.isBlank(token)) {
                resp.put("message", "非法调用，token不能为空");
                resp.put("code", -1);
                return resp;
            }
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claimMap = jwt.getClaims();
            Set<Map.Entry<String, Claim>> entries = claimMap.entrySet();
            for (Map.Entry<String, Claim> claimEntry : entries) {
                resp.put(claimEntry.getKey(), claimEntry.getValue().asString());
            }
            resp.put("code", 0);
            return resp;
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            resp.put("message", "token已过期");
            resp.put("code", -2);
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
            resp.put("message", "校验失败" + e.getLocalizedMessage());
            resp.put("code", -1);
            return resp;
        }
    }

    /**
     * 校验token，并获取含参数key的值
     *
     * @param token
     * @param key
     * @return
     */
    public static Map<String, Object> validateTokenWithKey(String token, String key) {
        Map<String, Object> resp = new HashMap<String, Object>();
        try {
            if (StringUtils.isBlank(token)) {
                resp.put("message", "非法调用，token不能为空");
                resp.put("code", -1);
                return resp;
            }
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            DecodedJWT jwt = verifier.verify(token);
            Claim claim = jwt.getClaim(key);
            if (claim == null || StringUtils.isBlank(claim.asString())) {
                resp.put("message", "key值不存在");
                resp.put("code", -1);
            } else {
                resp.put(key, claim.asString());
                resp.put("code", 0);
            }
            return resp;
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            resp.put("message", "token已过期");
            resp.put("code", -2);
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
            resp.put("message", "校验失败" + e.getLocalizedMessage());
            resp.put("code", -1);
            return resp;
        }
    }

    public static void main(String[] args) {
        //String token = generateTokenWithExpires("username", "zs", DateUtils.getExpiryDate(1));
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0aW1lIjoiMTU1MDQwMTQ5MDM0NSIsImV4cCI6MTU1MDQwMTU1MCwidXNlcm5hbWUiOiJ6cyJ9.fl2hosGbwDZH1Fr2mSEE9gPLk4mcSjaeSZGplRY9k7A";
        Map<String, Object> username = validateTokenWithKey(token, "username");
        System.out.println(username);
        System.out.println(token);
    }
}
