package dev.nars.wannab.util;

import dev.nars.wannab.util.secret.Secret;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.time.Duration;
import java.util.Date;

import static dev.nars.wannab.util.CustomResponseStatus.EMPTY_JWT;
import static dev.nars.wannab.util.CustomResponseStatus.INVALID_JWT;

@Slf4j
@Service
public class JwtService {

    //JWT 생성
    public String createJwt(Long userId){
        byte[] keyBytes = Decoders.BASE64.decode(Secret.JWT_SECRET_KEY);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam("type","jwt")
                .claim("userId",userId)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofDays(1).toMillis()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Header에서 X-ACCESS-TOKEN 으로 JWT 추출
    public String getJwt(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader("X-ACCESS-TOKEN");
    }

    // JWT에서 userId 추출
    public Long getUserId() throws CustomException{
        // JWT 추출
        String accessToken = getJwt();
        if(accessToken == null || accessToken.length() == 0){
            throw new CustomException(EMPTY_JWT);
        }

        // JWT 파싱
        Jws<Claims> claims;
        try{
            claims = Jwts.parserBuilder()
                    .setSigningKey(Secret.JWT_SECRET_KEY)
                    .build()
                    .parseClaimsJws(accessToken);
        } catch (Exception ignored) {
            throw new CustomException(INVALID_JWT);
        }

        // userId 추출
        return claims.getBody().get("userId", Long.class);
    }
}
