package com.example.ecommerce.Config;

import com.example.ecommerce.Security.SecurityUtils;
import com.example.ecommerce.Web.rest.VM.Notification;
import com.google.gson.Gson;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.core.token.Token;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ConfigToken {

    public static List<String> setAuthorities(Notification notificationBillOperation){
        Optional<String> currentUserJWTOptional = SecurityUtils.getCurrentUserJWT();
        String jwtToken = currentUserJWTOptional.toString();
        String[] splitToken = jwtToken.split("\\.");
        String encodedBody= splitToken[1];
        Base64 base64Url = new Base64(true);
        String body = new String(base64Url.decode(encodedBody));
        Gson gson = new Gson();
        com.example.ecommerce.Web.rest.VM.Token token = (com.example.ecommerce.Web.rest.VM.Token) gson.fromJson(body, Token.class);
        String authorities = token.getAuth();
        List<String> authoritieslist = Arrays.asList(authorities.split(","));
        if(notificationBillOperation.isIsConfig() == false) {
            for (int i = 0; i < authoritieslist.size(); i++) {
                System.out.println(authoritieslist.get(i));
                if (authoritieslist.get(i).equals("ROLE_ADMIN")) {
                    notificationBillOperation.addRole("ROLE_ADMIN");
                    notificationBillOperation.addRole("ROLE_CLIENT");
                    notificationBillOperation.addRole("ROLE_INTERNAUTE");
                } else if (authoritieslist.get(i).equals("ROLE_COMPANY_OWNER")) {
                    notificationBillOperation.addRole("ROLE_ADMIN");
                    //notificationBillOperation.setRoles("ROLE_COMPANY_OWNER");
                }

            }
        }else if(notificationBillOperation.isIsConfig() == true) {
            notificationBillOperation.addRole("ROLE_CONFIG");
        }
        return authoritieslist;

    }

}
