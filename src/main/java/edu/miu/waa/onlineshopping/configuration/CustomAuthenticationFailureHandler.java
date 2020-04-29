package edu.miu.waa.onlineshopping.configuration;

import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private static final String BAD_CREDENTIALS = "Bad credentials";
    private static final String USER_IS_DISABLED = "User is disabled";

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, org.springframework.security.core.AuthenticationException e) throws IOException, ServletException {
        if (USER_IS_DISABLED.equals(e.getMessage())) {
            httpServletResponse.sendRedirect("/not-approved-yet");
        } else {
            httpServletResponse.sendRedirect("/login?error=true");
        }
    }
}
