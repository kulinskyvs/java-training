package com.training.tiv.param;

public class SimpleContainer<T> implements GenericContainer<T> {

  private T sourceObject;
  private String type;
  
  public SimpleContainer(String type) {
    this.type = type;
  }


  public T get() {
     return sourceObject;
  }
  
  public void set(T someObject) {
    this.sourceObject = someObject;
    System.out.println("   "+someObject+" added to "+getType());
  }
  
  public String getType() {
    return type;
  }
  
  public String toString() {
    return getType();
  }
  
}
