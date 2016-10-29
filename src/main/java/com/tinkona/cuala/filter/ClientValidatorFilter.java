package com.tinkona.cuala.filter;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by Nnaji.Arinze on 10/27/2016.
 */
public class ClientValidatorFilter implements Filter {

    private String clientId;

    public ClientValidatorFilter() {
    }

    public ClientValidatorFilter(@Value("${client.id}") String clientId) {
        this.clientId = clientId;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String remoteHost = request.getRemoteHost();
        Enumeration<String> headerNames = ((HttpServletRequest) request).getHeaderNames();
        String headerClientId = ((HttpServletRequest) request).getHeader("clientId");
        if(headerClientId.equals(clientId)) {
            chain.doFilter(request, response);
        }else{
            throw new ServletException();
        }
    }

    @Override
    public void destroy() {

    }
}
