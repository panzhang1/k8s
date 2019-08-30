package com.sf.bizx.user.service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sf.bizx.user.model.User;

@Component
public class UserServiceProxy {
    private static final String MOCK_END_POINT = "http://usersvc:8080/mock_user/";
    
    public String getMockUserDisplayName(String userId) {
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(MOCK_END_POINT + userId, User.class);
        return formatUser(user);
    }
    
    private String formatUser(User user) {
        return String.format("%s %s", user.getFirstName(), user.getLastName());
    }
}
