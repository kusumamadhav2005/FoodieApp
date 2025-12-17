package com.example.FoodieApp.filter;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class Jwtfilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String path = httpRequest.getServletPath();

        // ✅ ALLOW REGISTER & LOGIN WITHOUT TOKEN
        if (path.contains("/register") || path.contains("/login")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String authHeader = httpRequest.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().println("Missing or invalid Token");
            return;
        }

        String jwtToken = authHeader.substring(7); // remove "Bearer "

        try {
            String emailid = Jwts.parser()
                    .setSigningKey("key123")
                    .parseClaimsJws(jwtToken)
                    .getBody()
                    .getSubject();

            httpRequest.setAttribute("emailid", emailid);
            filterChain.doFilter(servletRequest, servletResponse);

        } catch (Exception e) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().println("Invalid Token");
        }
        authHeader = httpRequest.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            // validate token here
        }

    }
}