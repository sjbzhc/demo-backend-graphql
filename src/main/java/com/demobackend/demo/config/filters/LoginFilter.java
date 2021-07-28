package com.demobackend.demo.config.filters;

import com.demobackend.demo.config.AppTokenProvider;
import com.demobackend.demo.config.GoogleTokenVerifier;
import com.demobackend.demo.exceptions.InvalidTokenException;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;

@Component
@RequiredArgsConstructor
public class LoginFilter implements Filter {

    @Autowired
    private GoogleTokenVerifier googleTokenVerifier;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        String idToken = ((HttpServletRequest) servletRequest).getHeader("X-ID-TOKEN");
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (idToken != null) {
            final Payload payload;
            try {
                payload = googleTokenVerifier.verify(idToken);
                if (payload != null) {
                    String username = payload.getSubject();
                    AppTokenProvider.addAuthentication(response, username);
                    filterChain.doFilter(servletRequest, response);
                    return;
                }
            } catch (GeneralSecurityException | InvalidTokenException e) {
                // This is not a valid token, we will send HTTP 401 back
            }
        }
        ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Override
    public void destroy() {
    }
}
