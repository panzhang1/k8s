package com.sf.bizx.rule.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sf.bizx.rule.model.Rule;
import com.sf.bizx.user.service.UserServiceProxy;

@RestController
public class MockRuleController {
    
    private static final Logger LOG = LoggerFactory.getLogger(MockRuleController.class);
    
    @Autowired
    UserServiceProxy userServiceProxy;
    
    
    @GetMapping(value = "/mock_rule")
    public Rule getRule(@RequestParam(value="code") String code) {
        Rule rule = new Rule();
        rule.setCode("Mock_Rule1");
        rule.setName("Mock_RuleName");
        rule.setLastModifiedBy("Mock_User1");
        
        LOG.info(String.format("-----------getRule:%s", rule.toString()));
        String displayName = userServiceProxy.getMockUserDisplayName(rule.getLastModifiedBy());
        rule.setLastModifiedBy(displayName);
        
        return rule;
    }
}