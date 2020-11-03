package online.yangcloud.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * jwt 工具类，用于生产 token 及 token 的校验
 *
 * @author zhuby
 * @since 2020/11/2 11:27 下午
 */

public class JwtUtil {

    private static final String token_sign = "9cc09665-bdb4-4ba8-a282-a53760e44401";
    private static final long expire_time_change = 3600000L;
    private static final String token_illegal = "非法Token值";
    private static final boolean yes = true;
    private static final boolean no = false;

    /**
     * 用户登录成功后生成Jwt
     * 使用 HS256 算法  私匙使用用户密码
     *
     * @param username 用户名
     * @param password 用户密码
     * @return token
     */
    public static String createToken(String username, String password) {
        Algorithm al = Algorithm.HMAC256(token_sign);
        Instant instant = LocalDateTime.now().plusHours(expire_time_change).atZone(ZoneId.systemDefault()).toInstant();
        Date expire = Date.from(instant);
        return JWT.create()
                .withSubject(username)
                .withClaim(username, password)
                .withExpiresAt(expire)
                .sign(al);
    }

    /**
     * Token的解密
     *
     * @param token 加密后的token
     * @return .
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                // 设置签名的秘钥
                .setSigningKey(token_sign)
                // 设置需要解析的 JWT
                .parseClaimsJws(token).getBody();
    }

    /**
     * 根据 token 获取 claim
     *
     * @param token token
     * @return .
     */
    public static Claim getClaimFromToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(token_sign);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim(jwt.getSubject());
    }


    /**
     * 校验token
     * 在这里可以使用官方的校验，我这里校验的是token中携带的密码于数据库一致的话就校验通过
     *
     * @param token    token
     * @param password 用户密码
     * @return 是否一致的结果
     */
    public static Boolean isVerify(String token, String password) {
        Algorithm algorithm = Algorithm.HMAC256(token_sign);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        Claim claim = jwt.getClaim(jwt.getSubject());
        String pwdInToken = claim.asString();
        return pwdInToken.equals(password);
    }

    /**
     * 根据token获取用户名
     *
     * @param token token
     * @return 用户名
     */
    public static String getUsernameFromToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(token_sign)).build().verify(token).getSubject();
        } catch (NullPointerException e) {
            return token_illegal;
        }
    }

    /**
     * 校验token是否合法
     *
     * @param token token
     * @return 是否合法的结果
     */
    public static boolean verifyTokenIsLegal(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(token_sign);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException e) {
            return no;
        }
        return yes;
    }

    /**
     * 验证 token 是否已过期
     *
     * @param token token
     * @return 是否已过期的结果
     * @author zhuby
     * @since 2020/11/3 7:54 上午
     */
    public static boolean isExpired(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(token_sign);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            if (jwt.getExpiresAt().before(new Date())) {
                return no;
            }
        } catch (JWTVerificationException e) {
            return no;
        }
        return yes;
    }
}
