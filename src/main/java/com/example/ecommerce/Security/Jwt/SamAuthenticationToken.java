package com.example.ecommerce.Security.Jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.UUID;

public class SamAuthenticationToken extends UsernamePasswordAuthenticationToken {
    public UUID getUserId() {
        return userId;
    }


    public String getUserLogin() {
        return login;
    }

    public String getUserName() {
        return userName;
    }



    private final UUID userId;
    private final String login;
    private final String userName;


    public SamAuthenticationToken(Object principal, Object credentials, UUID userId, String login, String userName) {
        super(principal, credentials);
        this.userId = userId;
        this.login = login;
        this.userName = userName;

    }

    public SamAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, UUID userId, String login, String userName) {
        super(principal, credentials, authorities);
        this.userId = userId;
        this.login = login;
        this.userName = userName;

    }
}
