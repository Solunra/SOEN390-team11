package com.soen390.team11.security;

public class SecurityConstant {
    public static final String SIGN_UP_URLS = "/account/signup";
    public static final String SIGN_IN_URLS = "/account/signin";
    public static final String SECRET = "SecretKeyToGenJWTS";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 86400000; // in millisecond one day
}
