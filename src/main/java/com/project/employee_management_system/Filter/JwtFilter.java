package com.project.employee_management_system.Filter;

import com.project.employee_management_system.Services.UserService;
import com.project.employee_management_system.Utilities.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
//        System.out.println("JwtFilter -> Request URI: " + request.getRequestURI());
        String path = request.getRequestURI();
//        System.out.println("JwtFilter -> path: " + path + ", Authorization: " + header);
        if (path.startsWith("/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
//            System.out.println("JWT valid? " + jwtUtil.validateJwtToken(token));
            System.out.println("Token received: " + token);
            if(jwtUtil.validateJwtToken(token)) {
                String email = jwtUtil.extractEmail(token);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email, null, List.of());
                SecurityContextHolder.getContext().setAuthentication(auth);
//                System.out.println("SecurityContext: " + SecurityContextHolder.getContext().getAuthentication());
            }
            else {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }
        else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        filterChain.doFilter(request, response);
    }
}