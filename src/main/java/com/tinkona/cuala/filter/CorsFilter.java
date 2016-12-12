package com.tinkona.cuala.filter;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Abdul-Hafiz.Ibrahim on 7/14/2016.
 */
public class CorsFilter implements Filter {
    private final String allowedOrigins;

    public CorsFilter(String allowedOrigins){
        this.allowedOrigins = allowedOrigins;
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String method = request.getMethod();
        // this origin value could just as easily have come from a database
        if (StringUtils.hasText(allowedOrigins)) {
            response.setHeader("Access-Control-Allow-Origin", allowedOrigins);
        }
        response.setHeader("Access-Control-Allow-Methods",
                "POST,GET,OPTIONS,DELETE,PUT");
        response.setHeader("Access-Control-Max-Age", Long.toString(60 * 60));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader(
                "Access-Control-Allow-Headers",
                "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
        if ("OPTIONS".equals(method)) {
            response.setStatus(HttpStatus.OK.value());
        } else {
            chain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
