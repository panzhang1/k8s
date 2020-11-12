package com.zp.example.mvel2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mvel2.MVEL;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.zp.example.mvel2.model.Person;
public class MVELBehaviorTest {
    
    @Test
    public void testProperty() {
        String name = (String)MVEL.eval("person.name", getContext());
        int age = (int)MVEL.eval("person.age", getContext());
        
        Assert.assertEquals(name, "Jack");
        Assert.assertEquals(age, 40);
    }
    
    @Test
    public void testIfCondition() {
        Object result = MVEL.eval("person.name == \"Jack\"", getContext());
        Assert.assertEquals(result, true);
    }
    
    @Test
    public void testProjections() {
        List<Integer> ages = (List<Integer>)MVEL.eval("(age in person.children)", getContext());
        Assert.assertEquals(ages.size(), 2);
        Assert.assertEquals(ages.get(0), new Integer(4));
        Assert.assertEquals(ages.get(1), new Integer(10));
        
        Object firstAge = MVEL.eval("(age in person.children)[0]", getContext());
        Assert.assertEquals(firstAge, new Integer(4));
        
        Object ifConditionResult = MVEL.eval("(age in person.children)[0] == 4", getContext());
        Assert.assertEquals(ifConditionResult, true);
    }
    
    @Test
    public void testMathExpression() {
        Object result = MVEL.eval("(person.age + 4) / person.children[0].age", getContext());
        Assert.assertEquals(result, 11);
        
        
        Object result2 = MVEL.eval("(person.age + 4) / person.children[0].age == 10", getContext());
        Assert.assertEquals(result2, false);
    }
    
    private Map<String, Object> getContext() {
        Map<String, Object> context = new HashMap<>();
        context.put("person", createPerson());
        
        return context;
    }
    
    private Person createPerson() {
        Person person = new Person("Jack",40);
        List<Person> children = new ArrayList<>();
        Person child1 = new Person("Tom",4);
        Person child2 = new Person("Jerry",10);
        
        children.add(child1);
        children.add(child2);
        
        person.setChildren(children);
        
        return person;
    }
}
