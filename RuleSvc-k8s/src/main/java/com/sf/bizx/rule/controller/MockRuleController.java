package com.sf.bizx.rule.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sf.bizx.rule.model.Rule;
import com.sf.bizx.user.service.UserServiceProxy;

@RestController
public class MockRuleController {
    
    private static final Logger LOG = LoggerFactory.getLogger(MockRuleController.class);
    
    @Autowired
    UserServiceProxy userServiceProxy;
    
    
    @GetMapping(value = "/mock_rule/{ruleCode}")
    public Rule getRule(@PathVariable(value="ruleCode") String ruleCode) {
        Rule rule = new Rule();
        rule.setCode(ruleCode);
        rule.setName(ruleCode + "_Name");
        rule.setLastModifiedBy(ruleCode + "_User1");
        
        LOG.info(String.format("-----------getRule:%s", rule.toString()));
        String displayName = userServiceProxy.getMockUserDisplayName(rule.getLastModifiedBy());
        rule.setLastModifiedBy(displayName);
        
        return rule;
    }
}