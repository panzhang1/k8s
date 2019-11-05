package com.sf.hop.user.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sf.hop.user.controller.MockUserController;
import com.sf.hop.user.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private MockUserController userService;

    @Test
    public void testGetUser() {
        User user = userService.getUser("admin");
        Assert.assertNotNull("Can get User from DB.", user);
    }
}
