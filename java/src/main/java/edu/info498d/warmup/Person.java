package edu.info498d.warmup;

import java.beans.*;
import java.lang.IllegalArgumentException;
import java.util.*;
import java.util.Collection;
import java.util.Comparator;

public class Person implements Comparable<Person> {
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired;

  public static void main(String[] args) {
    List<Person> people = Person.createFamily();
    Collections.sort(people);

    System.out.println(new Person("Padme", 46, 1000000));
    System.out.println(people.get(0));
  }

  /**
   * Constructor
   */
  public Person() {
    this("", 0, 0.0);
  }

  /**
   * Constructor
   * @param n
   * @param a
   * @param s
   */
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    ssn = "";
    this.propertyChangeFired = false;
  }

  /**
   * Get the age of a Person instance
   * @return
   */
  public int getAge() {
    return this.age;
  }

  /**
   * Set the age of a Person instance
   * @param a
   */
  public void setAge(int a) {
    if(a < 0) {
      throw new IllegalArgumentException("Age cannot be less than zero.");
    }

    this.age = a;
    this.propertyChangeFired = true;
  }

  /**
   * Get the name of a Person instance
   * @return
   */
  public String getName() {
    return name;
  }

  /**
   * Set the name of a Person instance
   * @param n
   */
  public void setName(String n) {
    if(null == n) {
      throw new IllegalArgumentException("Name cannot be empty");
    }

    this.name = n;
    this.propertyChangeFired = true;
  }

  /**
   * Get the salary of a Person instance
   * @return
   */
  public double getSalary() {
    return this.salary;
  }

  /**
   * Set the salary of a Person instance
   * @param s
   */
  public void setSalary(double s) {
    if(s < 0) {
      throw new IllegalArgumentException("Salary cannot be negative");
    }

    this.salary = s;
    this.propertyChangeFired = true;
  }

  /**
   * Get the SSN of a Person instance
   * @return
   */
  public String getSSN() {
    return ssn;
  }

  /**
   * Set the SSN of a Person instance
   * @param s
   */
  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    this.propertyChangeFired = true;
  }

  /**
   * @param other
   * @return
   */
  public int compareTo(Person other) {
    if(this.age > other.getAge()) {
      return -1;
    }
    else if(this.age < other.getAge()) {
      return 1;
    }
    else if(other.getName().compareTo(this.name) > 0) {
      return -1;
    }
    else if(other.getName().compareTo(this.name) < 0 ) {
      return 1;
    }
    else {
      return 0;
    }
  }

  /**
   * @return a set of people in an ArrayList
   */
  public static List<Person> createFamily() {
    List<Person> members = new ArrayList<Person>();

    members.add(new Person("Padme", 46, 1000000));
    members.add(new Person("Anakin", 41, 75000));
    members.add(new Person("Leia", 19, 10000));
    members.add(new Person("Luke", 19, 0));

    return members;
  }

  public String toString() {
    return "Person[name:" + this.name + ";age:" + this.age + ";salary:" + this.salary + "]";
  }

  public boolean getPropertyChangeFired() {
    return this.propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    this.age += 10;
    this.propertyChangeFired = true;
    return this.age;
  }

  /**
   * Checks if two Person instances have the same name and age
   * @param other
   * @return true if name and age of both instances are the same
   */
  public boolean equals(Object other) {
    if(null == other || other instanceof Integer) {
      return false;
    }

    Person p = (Person) other;
    return this.name.equals(p.name) && this.age == p.age;
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }

  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }

  public static class SalaryComparator implements Comparator<Person> {
    public void SalaryComparator() { }

    public int compare(Person p, Person q) {
      if(p.getSalary() > q.getSalary()) {
        return 1;
      }
      else if(p.getSalary() < q.getSalary()) {
        return -1;
      }
      else {
        return 0;
      }
    }
  }
}
