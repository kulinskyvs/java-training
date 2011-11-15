package com.training.tiv.param;

public class SimpleContainer<T> implements GenericContainer<T> {

  private T sourceObject;

  public T get() {
     return sourceObject;
  }
  
  public void set(T someObject) {
    this.sourceObject = someObject;
  }
  
  public String toString() {
    return sourceObject.toString();
  }
}
