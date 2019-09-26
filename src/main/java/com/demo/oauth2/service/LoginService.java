package com.demo.oauth2.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Value("${facebook.client.clientId}")
    private String appId;

    @Value("${facebook.client.clientSecret}")
    private String appSecret;

    private FacebookConnectionFactory createConnectionFactory() {

        return new FacebookConnectionFactory(appId, appSecret);
    }

    public String loginFacebook() {
        OAuth2Parameters parameters = new OAuth2Parameters();
        parameters.setRedirectUri("https://localhost:8080/api/login/facebook");
        parameters.setScope("public_profile, email");
        return createConnectionFactory().getOAuthOperations().buildAuthenticateUrl(parameters);
    }

    public String getAccessFacebookToken(String code) {

        return createConnectionFactory().getOAuthOperations().exchangeForAccess(code, "https://localhost:8080/api/login/facebook", null).getAccessToken();
    }
}
