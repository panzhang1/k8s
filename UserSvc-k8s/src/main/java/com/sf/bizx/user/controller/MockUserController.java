package com.sf.bizx.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sf.bizx.user.model.User;

@RestController
public class MockUserController {
    private static final Logger LOG = LoggerFactory.getLogger(MockUserController.class);
    
    @GetMapping(value = "/mock_user/{userId}")
    public User getUser(@PathVariable(value="userId") String userId) {
        User user = new User(userId,userId + "_Name");
        user.setFirstName(userId + "_FirstName");
        user.setLastName(userId + "_LastName");
        LOG.info(String.format("-----------getUser:%s", user.toString()));
        return user;
    }
}