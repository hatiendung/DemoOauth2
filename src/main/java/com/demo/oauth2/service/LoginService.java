package com.demo.oauth2.service;

import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private String appId = "395754711359142";
    private String appSecret = "6faff585af2595fe6d7e98fe9d374ab7";

    private FacebookConnectionFactory createConnectionFactory() {

        return new FacebookConnectionFactory(appId, appSecret);
    }

    public String loginFacebook() {
        OAuth2Parameters parameters = new OAuth2Parameters();
        parameters.setRedirectUri("localhost:8080/api/login/facebook");
        parameters.setScope("public_profile, email");
        return createConnectionFactory().getOAuthOperations().buildAuthenticateUrl(parameters);
    }

    public String getAccessFacebookToken(String code) {

        return createConnectionFactory().getOAuthOperations().exchangeForAccess(code, "localhost:8080/api/login//facebook", null).getAccessToken();
    }
}
