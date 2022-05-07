package com.example.ecommerce.Web.rest.VM;

import java.util.ArrayList;
import java.util.List;

public class Notification {
    private String id;

    private String message;

    private String date;

    private Boolean isReadIt;

    private List<String> roles;

    private String username;

    private String userId;

    private String notificationUrl;

    private String icon;

    private boolean isConfig;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getReadIt() {
        return isReadIt;
    }

    public void setReadIt(Boolean readIt) {
        isReadIt = readIt;
    }


    public Notification(){
        roles = new ArrayList<>();
    }

    public List<String> getRoles() {
        return roles;
    }

    public void addRole(String role){
        this.roles.add(role);
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNotificationUrl() {
        return notificationUrl;
    }

    public void setNotificationUrl(String notificationUrl) {
        this.notificationUrl = notificationUrl;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isIsConfig() {
        return isConfig;
    }

    public void setIsConfig(boolean config) {
        isConfig = config;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                ", date='" + date + '\'' +
                ", isReadIt=" + isReadIt +
                ", roles=" + roles +
                ", username='" + username + '\'' +
                ", userId='" + userId + '\'' +
                ", notificationUrl='" + notificationUrl + '\'' +
                ", icon='" + icon + '\'' +
                ", isConfig=" + isConfig +
                '}';
    }
}
