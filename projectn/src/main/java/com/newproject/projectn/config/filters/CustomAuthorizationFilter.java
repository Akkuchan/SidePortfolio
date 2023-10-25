package com.newproject.projectn.config.filters;

import com.newproject.projectn.entitiy.Authority;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Encoders;
import java.util.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class CustomAuthorizationFilter extends OncePerRequestFilter{//인증 필터

    @Value("${spring.security.jwt.secret-key}")
    private String SECRET_KEY;


     @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/user/login") || request.getServletPath().equals("/user/register")){
            filterChain.doFilter(request, response);
        }else{
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
                String token = authorizationHeader.substring("Bearer ".length());//7

                byte[] keyBytes = Encoders.BASE64.encode(SECRET_KEY.getBytes()).getBytes();//base64로 인코딩 된 비밀키를 바이트로 변환

                assert keyBytes != null;
                Claims test = Jwts.parserBuilder()
                    //.json(new JacksonDeserializer(Maps.of("Role", Role.class).build()))
                    .setSigningKey(Keys.hmacShaKeyFor(keyBytes))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

//                var test5 = Maps.of("Role", Authority.class).build();
                var test3 = Jwts.parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor(keyBytes))
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
                List<Map<String, String>> roleClaims = test3.get("Role", List.class);
                Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                roleClaims.forEach(auth -> authorities.add(new SimpleGrantedAuthority(auth.get("authority"))));
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(test3.getSubject(), null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }
            filterChain.doFilter(request,response);
        }

    }
}
