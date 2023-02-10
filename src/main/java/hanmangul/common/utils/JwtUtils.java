package hanmangul.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

public class JwtUtils implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;

    // Second
//    public static final long JWT_ACCESS_TOKEN_VALIDITY = 6 * 10 * 60; // 60분
//    public static final long JWT_ACCESS_TOKEN_VALIDITY = 5 * 60; // 5분
//    public static final long JWT_ACCESS_TOKEN_VALIDITY = 5; // 5초
//    public static final long JWT_ACCESS_TOKEN_VALIDITY = 24 * 6 * 10 * 60; // 하루
    public static final long JWT_ACCESS_TOKEN_VALIDITY = 6 * 10; // 60초

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    /**
     * 토큰 생성
     */
    public static String generateToken() {
        long now = System.currentTimeMillis();

        Claims claims = Jwts.claims().setSubject("authToken").setIssuedAt(new Date(now)).setExpiration(new Date(now + JWT_ACCESS_TOKEN_VALIDITY * 1000));

//        claims.put("serviceId", serviceId);

        return Jwts.builder().setClaims(claims)
//                .setSubject(serviceId)
                .signWith(key).compact();
    }

    /**
     * 토큰에서 모든 Claim을 조회하는 함수
     */
    public static Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    /**
     * 토큰 만료일 조회
     */
    public static Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * 토큰이 만료되었는지 확인
     */
    public static Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 토큰에서 Claim을 조회하는 함수
     */
    public static <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public static String getToken(HttpServletRequest request) {
        String requestTokenHeader = request.getHeader("Authorization");
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            return requestTokenHeader.substring(7);
        }
        return "";
    }

}
