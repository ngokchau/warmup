package edu.info498d.warmup;

import java.beans.*;
import java.lang.IllegalArgumentException;
import java.util.*;
import java.util.Comparator;

public class Person implements Comparable<Person> {
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired;

  public static void main(String[] args) {
    Person p = new Person("Ja", 13, 2100.00);
    Person q = new Person("Jb", 13, 2100.00);

    int x = p.compareTo(q);

    SalaryComparator sc = new SalaryComparator();
    int y = sc.compare(p, q);

    System.out.println(y);
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
    ssn = null;
    propertyChangeFired = false;
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
    propertyChangeFired = true;
  }

  /**
   * @param other
   * @return
   */
  public int compareTo(Person other) {
    if(this.age > other.age) {
      return 1;
    }
    else if(this.age < other.age) {
      return -1;
    }
    else if(this.name.compareTo(other.name) > 0) {
      return -1;
    }
    else if(this.name.compareTo(other.name) < 0 ) {
      return 1;
    }
    else {
      return 0;
    }
  }

  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable" + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }

  /**
   * Checks if two Person instances have the same name and age
   * @param other
   * @return true if name and age of both instances are the same
   */
  public boolean equals(Person other) {
    return this.name.equals(other.name) && this.age == other.age;
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
