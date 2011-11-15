package com.training.tiv.param;

public interface GenericContainer<T> {

  T get();
  
  void set(T someObject);
}
