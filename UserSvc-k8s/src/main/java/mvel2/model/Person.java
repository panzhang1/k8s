package mvel2.model;

import java.math.BigDecimal;
import java.util.List;

public class Person {
    private String name;
    private int age;
    private BigDecimal tution;
    private List<Person> children;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Person> getChildren() {
        return children;
    }
    public void setChildren(List<Person> children) {
        this.children = children;
    }

    public BigDecimal getTution() {
        return tution;
    }

    public void setTution(BigDecimal tution) {
        this.tution = tution;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
