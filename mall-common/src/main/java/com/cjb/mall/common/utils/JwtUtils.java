package com.cjb.mall.common.utils;

import com.cjb.mall.common.user.UserInfo;
import io.jsonwebtoken.*;
import org.joda.time.DateTime;

import javax.crypto.SecretKey;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

/**
 * @author bystander
 * @date 2018/10/1
 */
public class JwtUtils {

    public static final String JWT_KEY_ID = "id";
    public static final String JWT_KEY_USER_NAME = "username";

    /**
     * 生成Token
     * @param userInfo
     * @param privateKey
     * @param expireDate 过期时间
     * @return
     */
    public static String generateToken(UserInfo userInfo, PrivateKey privateKey, Date expireDate) {
        return Jwts.builder()
                .claim(JWT_KEY_ID, userInfo.getId())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    /**
     * 生成Token
     * @param userInfo
     * @param privateKey
     * @param expireMinutes
     * @return
     */
    public static String generateToken(UserInfo userInfo, PrivateKey privateKey, int expireMinutes) {
        return Jwts.builder()
                .claim(JWT_KEY_ID, userInfo.getId())
                .claim(JWT_KEY_USER_NAME, userInfo.getName())
                .setExpiration(DateTime.now().plusMinutes(expireMinutes).toDate())
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    /**
     * 生成Token
     * @param userInfo
     * @param privateKey
     * @param expireMinutes
     * @return
     * @throws Exception
     */
    public static String generateToken(UserInfo userInfo, byte[] privateKey, int expireMinutes) throws Exception {
        return Jwts.builder()
                .claim(JWT_KEY_ID, userInfo.getId())
                .claim(JWT_KEY_USER_NAME, userInfo.getName())
                .setExpiration(DateTime.now().plus(expireMinutes).toDate())
                .signWith(SignatureAlgorithm.ES256, RsaUtils.getPrivateKey(privateKey))
                .compact();
    }

    /**
     * 公钥解析Token
     * @param publicKey
     * @param token
     * @return
     */
    public static Jws<Claims> parseToken(PublicKey publicKey, String token) {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }


    /**
     * 公钥解析Token
     * @param publicKey
     * @param token
     * @return
     * @throws Exception
     */
    public static Jws<Claims> parseToken(byte[] publicKey, String token) throws Exception {
        return Jwts.parser().setSigningKey(RsaUtils.getPublicKey(publicKey)).parseClaimsJws(token);
    }


    /**
     * 从Token中获取用户信息（使用公钥解析）
     * @param publicKey
     * @param token
     * @return
     */
    public static UserInfo getUserInfo(PublicKey publicKey, String token) {
        Jws<Claims> claimsJws = parseToken(publicKey, token);
        Claims body = claimsJws.getBody();
        String id = body.get(JWT_KEY_ID).toString();
//        String name = body.get(JWT_KEY_USER_NAME).toString();
        return new UserInfo(Integer.valueOf(id), body.getExpiration());
    }

    /**
     * 从Token中获取用户信息（使用公钥解析）
     * @param publicKey
     * @param token
     * @return
     * @throws Exception
     */
    public static UserInfo getUserInfo(byte[] publicKey, String token) throws Exception {
        Jws<Claims> claimsJws = parseToken(publicKey, token);
        Claims body = claimsJws.getBody();
        String id = body.get(JWT_KEY_ID).toString();
        String name = body.get(JWT_KEY_USER_NAME).toString();
        return new UserInfo(Integer.valueOf(id), name);
    }

    /**
     *  验证token是否失效
     *  true:过期   false:没过期
     */
    public static Boolean isTokenExpired(PublicKey publicKey,String token) {
        try {
            final Date expiration = getExpirationDateFromToken(publicKey,token);
            return expiration.before(new Date());
        } catch (ExpiredJwtException expiredJwtException) {
            return true;
        }
    }

    /**
     * 获取jwt失效时间
     */
    public static Date getExpirationDateFromToken(PublicKey publicKey,String token) {
        return parseToken(publicKey,token).getBody().getExpiration();
    }

}
