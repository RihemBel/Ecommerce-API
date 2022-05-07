package com.example.ecommerce.Web.rest.VM;

import java.util.ArrayList;

public class Token {

    private String sub;

    private String auth;

    private ArrayList<String> roles;

    private String userId;


    private String login;

    private String userName;

    private Long exp;

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public Long getExp() {
        return exp;
    }

    public void setExp(Long exp) {
        this.exp = exp;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    @Override
    public String toString() {
        return "Token{" +
                "sub='" + sub + '\'' +
                ", auth='" + auth + '\'' +
                ", roles=" + roles +
                ", userId='" + userId + '\'' +
                ", login='" + login + '\'' +
                ", userName='" + userName + '\'' +
                ", exp=" + exp +
                '}';
    }}
