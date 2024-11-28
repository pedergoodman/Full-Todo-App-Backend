package com.example.Full_Todo_App_Backend.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class SpaWebFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();

        // Allow API requests, static resources, and other asset requests to pass through
        if (path.startsWith("/api") ||
            path.startsWith("/static") ||
            path.contains(".") ||  // Static resources like .css, .js, .png, etc.
            path.equals("/favicon.ico")) {
            filterChain.doFilter(request, response);
            return;
        }

        // If the request does not match any of the above, forward it to index.html for React routing
        request.getRequestDispatcher("/index.html").forward(request, response);
    }
}
