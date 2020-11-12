/*
 * $Id$
 */
package com.zp.example.jdk8;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TestLambda {

  public static void printPersonsWithPredicate(List<Person> roster, Predicate<Person> tester) {
    for (Person p : roster) {
        if (tester.test(p)) {
            p.printPerson();
        }
    }
  }
  
  public static void processPersons(List<Person> roster,Predicate<Person> tester,Consumer<Person> block) {
      for (Person p : roster) {
          if (tester.test(p)) {
              block.accept(p);
          }
      }
  }
  
  public static <X, Y> void processElements(
      Iterable<X> source,
      Predicate<X> tester,
      Function <X, Y> mapper,
      Consumer<Y> block) {
      for (X p : source) {
          if (tester.test(p)) {
              Y data = mapper.apply(p);
              block.accept(data);
          }
      }
  }
  
  private List<Person> getData() {
    List<Person> roster = new ArrayList<>();
    Person person = new Person("P1",Person.Sex.FEMALE,LocalDate.parse("1990-12-03"));
    roster.add(person);
    
    person = new Person("P2",Person.Sex.MALE,LocalDate.parse("1995-12-03"));
    roster.add(person);
    
    person = new Person("P3",Person.Sex.FEMALE,LocalDate.parse("1995-12-03"));
    roster.add(person);
    
    person = new Person("P4",Person.Sex.MALE,LocalDate.parse("1978-12-03"));
    roster.add(person);
    
    person = new Person("P5",Person.Sex.FEMALE,LocalDate.parse("2000-12-03"));
    roster.add(person);
    
    return roster;
  }
  
  public void test1() {
    List<Person> roster = getData();
    printPersonsWithPredicate(
        roster,
        p -> p.getGender() == Person.Sex.FEMALE
            && p.getAge() >= 18
            && p.getAge() <= 25
    );
    
    System.out.println("-----");
    
    printPersonsWithPredicate(roster, new Predicate<Person> (){
      @Override
      public boolean test(Person p) {
        boolean result = p.getGender() == Person.Sex.MALE
            && p.getAge() >= 18
            && p.getAge() <= 25;
        return result;
      }
    });

    System.out.println("-----");
    processPersons(
        roster,
        p -> p.getGender() == Person.Sex.MALE
            && p.getAge() >= 18
            && p.getAge() <= 25,
        p -> p.printPerson()
   );
    
    System.out.println("-----");
    processElements(
        roster,
        p -> p.getGender() == Person.Sex.MALE
            && p.getAge() >= 18
            && p.getAge() <= 25,
        p -> p.getEmailAddress(),
        email -> System.out.println(email)
    );
  }
  
  class ComparisonProvider {
    public int compareByName(Person a, Person b) {
        return a.getName().compareTo(b.getName());
    }
        
    public int compareByAge(Person a, Person b) {
        return a.getBirthday().compareTo(b.getBirthday());
    }
}
  
  public void test2() {
    List<Person> roster = getData();

    ComparisonProvider myComparisonProvider = new ComparisonProvider();
    Collections.sort(roster, myComparisonProvider::compareByName);
    
    Collections.sort(roster,
        (Person a, Person b) -> {
            return a.getBirthday().compareTo(b.getBirthday());
        }
    );
    
    Collections.sort(roster,
        (a, b) -> Person.compareByAge(a, b)
    );
    
    Collections.sort(roster, Person::compareByAge);
  }
  
  public void test3() {
    List<Person> roster = getData();
    roster
    .stream()
    .filter(e -> e.getGender() == Person.Sex.MALE)
    .forEach(e -> System.out.println(e));
    
    double average = roster
        .stream()
        .filter(p -> p.getGender() == Person.Sex.MALE)
        .mapToInt(Person::getAge)
        .average()
        .getAsDouble();
    System.out.println("average age:" + average);
  }
  
  public static void main(String[] args) {
    TestLambda test = new TestLambda();
    test.test3(); 
  }
  
  public void testTraversingCollection() {
    List<Shape> myShapesCollection = new ArrayList<>();
    myShapesCollection.add(new Shape("Shape1", Color.RED, 5));
    myShapesCollection.add(new Shape("Shape2", Color.YELLOW, 8));
    myShapesCollection.add(new Shape("Shape3", Color.GREEN, 3));
    myShapesCollection.add(new Shape("Shape4", Color.RED, 1));
    myShapesCollection.add(new Shape("Shape5", Color.RED, 10));
    myShapesCollection.add(new Shape("Shape6", Color.GREEN, 9));
    myShapesCollection.add(new Shape("Shape7", Color.RED, 6));
    myShapesCollection.add(new Shape("Shape8", Color.YELLOW, 2));
    myShapesCollection.add(new Shape("Shape9", Color.RED, 13));
    
    myShapesCollection.stream()
    .filter(e -> e.getColor() == Color.RED)
    .forEach(e -> System.out.println(e.getName()));
    System.out.println("\nparallelStream\n");
    myShapesCollection.parallelStream()
    .filter(e -> e.getColor() == Color.RED)
    .forEach(e -> System.out.println(e.getName()));
    
    String joined = myShapesCollection.stream()
        .map(Object::toString)
        .collect(Collectors.joining(", "));
    System.out.println("Joined:" + joined);
    
    int total = myShapesCollection.stream()
        .collect(Collectors.summingInt(Shape::getPrice));
    System.out.println("total:" + total);
  }
  
  class Shape {
    private String name;
    private Color color;
    private int price;
    
    public Shape(String name, Color color, int price) {
      this.name = name;
      this.color = color;
      this.price = price;
    } 
    
    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }
    public Color getColor() {
      return color;
    }
    public void setColor(Color color) {
      this.color = color;
    }

    public int getPrice() {
      return price;
    }

    public void setPrice(int price) {
      this.price = price;
    }

    @Override
    public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("Shape [name=");
      builder.append(name);
      builder.append(", color=");
      builder.append(color);
      builder.append(", price=");
      builder.append(price);
      builder.append("]");
      return builder.toString();
    }
  }

  enum Color {
    RED,YELLOW,GREEN
  }

}
