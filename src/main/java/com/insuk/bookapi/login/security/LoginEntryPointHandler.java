package com.insuk.bookapi.login.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class LoginEntryPointHandler extends LoginUrlAuthenticationEntryPoint {
    private final String returnErrMessage = "아이디 또는 비밀번호가 올바르지 않습니다.";

    public LoginEntryPointHandler(String loginFormUrl) {
        super(loginFormUrl);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String redirectUrl = super.determineUrlToUseForThisRequest(request, response, authException);
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        redirectStrategy.sendRedirect(request, response,
                UriComponentsBuilder.fromPath(redirectUrl).queryParam("message", returnErrMessage).toUriString());
    }
}
