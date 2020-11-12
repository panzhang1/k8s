/*
 * $Id$
 */
package com.zp.example.jdk8;

import java.time.LocalDate;

public class Person {
  String name;
  LocalDate birthday;
  Sex gender;
  String emailAddress;

  public Person(String name, Sex gender, LocalDate birthday) {
    this.name = name;
    this.gender = gender;
    this.birthday = birthday;
  }
  
  public int getAge() {
    LocalDate now = LocalDate.now();
    return now.getYear() - birthday.getYear();
  }

  public void printPerson() {
      System.out.println(toString());
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  public Sex getGender() {
    return gender;
  }

  public void setGender(Sex gender) {
    this.gender = gender;
  }

  public String getEmailAddress() {
    return name + "@sap.com";
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }
  
  public static int compareByAge(Person a, Person b) {
    return a.birthday.compareTo(b.birthday);
  }

  public enum Sex {
      MALE, FEMALE
  }


  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Person [name=");
    builder.append(name);
    builder.append(", birthday=");
    builder.append(birthday);
    builder.append(", gender=");
    builder.append(gender);
    builder.append(", getAge=");
    builder.append(getAge());
    builder.append("]");
    return builder.toString();
  }
}
