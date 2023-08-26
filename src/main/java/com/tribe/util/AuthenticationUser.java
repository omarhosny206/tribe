package com.tribe.util;

import com.tribe.entity.User;
import org.springframework.security.core.Authentication;

public class AuthenticationUser {
    private static Authentication authentication;

    public static User get(Authentication authentication) {
        return ((CustomUser) authentication.getPrincipal()).getUser();
    }
}
