package com.chaozusTracker.security;

import io.jsonwebtoken.lang.Collections;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer")) {
            String token = authHeader.substring(7);
            if(this.jwtUtil.validateToken(token)){
                String email = jwtUtil.extractEmail(token);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email,null, Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

}
