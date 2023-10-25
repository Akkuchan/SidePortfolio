package com.newproject.projectn.config.security.auth.jwt;


import com.newproject.projectn.config.exception.BusinessLogicException;
import com.newproject.projectn.config.exception.ExceptionCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {


    @Value("${spring.security.jwt.secret-key}")
    private String SECRET_KEY;
    @Value("${spring.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${spring.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    private Key nowKey;



    public String extractEmail(String token){
     return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){//Claism 에서 각 클레임(각 타입은 T)을 추출
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){// 토큰 생성 메서드
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }


    public String generateRefreshToken(
            UserDetails userDetails
    ) {
        return buildToken(new HashMap<>(), userDetails, refreshExpiration);
    }
    public String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ){

        var test =  Jwts
                .builder()
                .claim("Role", userDetails.getAuthorities())// 아래 주체자, 이슈일자, 만료일자 제외한 claims를 작성하는 메서드
                .setSubject(userDetails.getUsername())//서명의 주체자(사용자)를 설정
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expiration) ) //System.currentTimeMillis()+1000 * 60*24)
                .signWith(getSignInKey())//서명에(jwt 생성)필요한 키값과 서명할 알고리즘 선택)
                .compact();

//        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);//base64로 인코딩 된 비밀키를 바이트로 변환
//
//        DefaultClaims sssss = (DefaultClaims) Jwts.parserBuilder()
//                .setSigningKey(Keys.hmacShaKeyFor(keyBytes)) // 서명 키 설정
//                .build()
//                .parseClaimsJws(test)
//                .getBody();





        return test;
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractEmail(token);//토큰의 서명자와 유저 디테일의 사용자가 같은지 확인
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));// 사용자 같은지 + 만료됬는지도 함께 확인
    
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration );//
    }

    public Claims extractAllClaims(String token){
        System.out.println("token:" + token);
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())// 인증 정보를 서버의 private Key로 서명하는 하기 위해 비밀키를 설정하는 코드
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Key getSignInKey() {

        String secretString = Encoders.BASE64.encode(SECRET_KEY.getBytes());
        byte[] test = secretString.getBytes();
        return Keys.hmacShaKeyFor(secretString.getBytes());


    }
}
