package com.training.tiv.fruits;

public abstract class Fruit {
  
  private String name;
  
  protected abstract String getType();
  
  public Fruit(String fruitName) {
     name = fruitName;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getName() {
    return name;
  }
  
  public String toString() {
    return getType()+":"+name;
  }
}
