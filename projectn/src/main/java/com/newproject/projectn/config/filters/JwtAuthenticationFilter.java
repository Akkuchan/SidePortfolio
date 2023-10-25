package com.newproject.projectn.config.filters;

import com.newproject.projectn.config.security.auth.jwt.JwtService;
import com.newproject.projectn.config.security.auth.jwt.TokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;// jwt 객체에서 원하는 클레인(정보)들을 가져오기 위한 클래스
    private final UserDetailsService userDetailsService;//DB 유저 정보를 가져와 Details로 받아보기르 원하기에 직접 구현해야 함
    private final TokenRepository tokenRepository;


    @Override
    protected void doFilterInternal(
            @NonNull  HttpServletRequest request,
            @NonNull  HttpServletResponse response,
            @NonNull  FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");//요청에서 헤더에 토큰을 보내기에 이를 서버에서 확인하기 위한 코드
        final String jwt;
        final String email;

        if(authHeader== null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7); //"Bearer "가 7글자, 이걸 제와한 토큰값만 가져오기
        email= jwtService.extractEmail(jwt);//todo extract the userEmail form JWT token

        if(email != null && SecurityContextHolder.getContext().getAuthentication()== null){// 유저가 이미 인증을 받지는 않았는지 체크
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
            var isTokenValid = tokenRepository.findByToken(jwt)
                    .map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);

            if(jwtService.isTokenValid(jwt, userDetails) && isTokenValid){//토큰이 validate하다면 securityContextHolder의 context에 인증 객체를 반영하고 디스페처 서블릿에 요청을 보내야 한다.
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(//"UsernamePasswordAuthenticationToken" 객체는 스프링에서 쓰는 Authentication 구현체로 시큐리티 context에 인증 객체 반영 위해 생성한다.
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);
    }




}
