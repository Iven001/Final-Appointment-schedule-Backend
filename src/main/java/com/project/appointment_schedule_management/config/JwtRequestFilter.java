package com.project.appointment_schedule_management.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.project.appointment_schedule_management.service.JwtService;
import com.project.appointment_schedule_management.utils.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private final RequestMatcher ignoredPath1 = new AntPathRequestMatcher("/user/register");
    private final RequestMatcher ignoredPath2 = new AntPathRequestMatcher("/password/forget_password");
    private final RequestMatcher ignoredPath3 = new AntPathRequestMatcher("/password/reset_password");
   // private final RequestMatcher ignoredPath4 = new AntPathRequestMatcher("/employee/findEmployeeById");
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (this.ignoredPath1.matches(request) || this.ignoredPath2.matches(request)
                || this.ignoredPath3.matches(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String header = request.getHeader("Authorization");

        String jwtToken = null;
        String userName = null;

        if (header != null && header.startsWith("Bearer ")) {
            jwtToken = header.substring(7);

            try {
                userName = jwtUtil.getUserNameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get Jwt Token");
            } catch (ExpiredJwtException e) {
                System.out.println("Jwt token is expired");
            }
        } else {
            System.out.println("Jwt token does not start with Bearer!");
        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = jwtService.loadUserByUsername(userName);

            if (jwtUtil.validateToken(jwtToken, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }
        }

        filterChain.doFilter(request, response);
    }
}
