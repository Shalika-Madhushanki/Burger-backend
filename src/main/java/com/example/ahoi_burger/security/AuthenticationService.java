package com.example.ahoi_burger.security;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public static final String AUTH_TOKEN_HEADER_NAME = "X-API-KEY";



    public static String API_TOKEN;

    @Value("${app.api-key}")
    private String apiToken;

    @PostConstruct
    public void init() {
        setApiToken(apiToken);
    }

    public static Authentication getAuthentication(HttpServletRequest request){
        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);
        if (apiKey == null || !apiKey.equals(getApiToken())) {
            throw new BadCredentialsException("Invalid API Key");
        }

        return new ApiKeyAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES);
    }
    public static String getApiToken() {
        return API_TOKEN;
    }

    public static void setApiToken(String apiToken) {
        API_TOKEN = apiToken;
    }
}
